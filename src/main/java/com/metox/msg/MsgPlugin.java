package com.metox.msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MsgPlugin extends JavaPlugin implements TabExecutor {

    private final Map<UUID, UUID> lastTarget = new HashMap<UUID, UUID>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String[] cmds = {"msg", "reply"};
        for (String cmd : cmds) {
            if (getCommand(cmd) != null) {
                getCommand(cmd).setExecutor(this);
                getCommand(cmd).setTabCompleter(this);
            }
        }
        getLogger().info("Msg aktif - algilanan surum 1." + Compat.MINOR);
        // chizzar-dev
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = command.getName().toLowerCase();
        if (name.equals("msg")) return doMsg(sender, args);
        if (name.equals("reply")) return doReply(sender, args);
        return true;
    }

    // chizzar-dev
    private boolean doMsg(CommandSender sender, String[] args) {
        if (!sender.hasPermission("msg.use")) {
            send(sender, "no-permission");
            return true;
        }
        if (args.length < 2) {
            send(sender, "usage-msg");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            send(sender, "player-not-found");
            return true;
        }
        if (sender instanceof Player && ((Player) sender).getUniqueId().equals(target.getUniqueId())) {
            send(sender, "self");
            return true;
        }

        deliver(sender, target, join(args, 1));
        return true;
    }

    private boolean doReply(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            send(sender, "players-only");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("msg.use")) {
            send(p, "no-permission");
            return true;
        }
        if (args.length < 1) {
            send(p, "usage-reply");
            return true;
        }

        UUID tid = lastTarget.get(p.getUniqueId());
        Player target = tid != null ? Bukkit.getPlayer(tid) : null;
        if (target == null) {
            send(p, "no-reply");
            return true;
        }

        deliver(p, target, join(args, 0));
        return true;
    }

    // chizzar-dev
    private void deliver(CommandSender from, Player target, String message) {
        String fromName = (from instanceof Player) ? ((Player) from).getName()
                : getConfig().getString("console-name", "Konsol");

        String sfmt = getConfig().getString("format.sender", "&7Ben &8» &e%target%&8: &f%message%");
        from.sendMessage(Compat.color(sfmt
                .replace("%target%", target.getName())
                .replace("%sender%", fromName)
                .replace("%message%", message)));

        String rfmt = getConfig().getString("format.receiver", "&e%sender% &8» &7Ben&8: &f%message%");
        target.sendMessage(Compat.color(rfmt
                .replace("%sender%", fromName)
                .replace("%target%", target.getName())
                .replace("%message%", message)));

        if (getConfig().getBoolean("actionbar.enabled", true)) {
            String ab = getConfig().getString("actionbar.message", "&e» &f%sender% &7sana bir mesaj gonderdi &e«");
            Compat.actionBar(target, ab.replace("%sender%", fromName));
        }

        if (getConfig().getBoolean("sound.enabled", true)) {
            Compat.playSound(target, getConfig().getString("sound.name", ""));
        }

        if (from instanceof Player) {
            UUID fromId = ((Player) from).getUniqueId();
            lastTarget.put(fromId, target.getUniqueId());
            lastTarget.put(target.getUniqueId(), fromId);
        }
    }

    private String join(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i > start) sb.append(' ');
            sb.append(args[i]);
        }
        return sb.toString();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> out = new ArrayList<String>();
        if (command.getName().equalsIgnoreCase("msg") && args.length == 1) {
            String pref = args[0].toLowerCase();
            for (Player p : Compat.online()) {
                if (p.getName().toLowerCase().startsWith(pref)) out.add(p.getName());
            }
        }
        return out;
    }

    public void send(CommandSender sender, String key, String... repl) {
        String m = getConfig().getString("messages." + key, "");
        if (m == null || m.isEmpty()) return;
        for (int i = 0; i + 1 < repl.length; i += 2) {
            m = m.replace(repl[i], repl[i + 1]);
        }
        sender.sendMessage(Compat.color(m));
    }
}
