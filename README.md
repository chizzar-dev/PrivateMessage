<div align="center">

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:0b1220,100:0e7490&height=110&section=header&text=Msg&fontSize=42&fontColor=22d3ee&fontAlignY=54&desc=Private%20messaging%20with%20action-bar%20alerts&descSize=13&descColor=94a3b8&descAlignY=80" width="100%" alt="Msg" />

<p>
<img src="https://img.shields.io/github/v/release/chizzar-dev/PrivateMessage?style=flat&label=release&color=06b6d4&labelColor=0b1220" alt="release" />
<img src="https://img.shields.io/badge/Minecraft-1.8%20%E2%80%93%201.21.11-0891b2?style=flat&labelColor=0b1220" alt="Minecraft 1.8 - 1.21.11" />
<img src="https://img.shields.io/badge/Java-8%2B-155e75?style=flat&labelColor=0b1220&logo=openjdk&logoColor=22d3ee" alt="Java 8+" />
<a href="LICENSE"><img src="https://img.shields.io/github/license/chizzar-dev/PrivateMessage?style=flat&label=license&color=0e7490&labelColor=0b1220" alt="license" /></a>
</p>

</div>

Msg adds private messaging with /msg and /r, plus an action-bar alert and optional sound when a message arrives.

*Msg, /msg ve /r ile ozel mesajlasma ekler; mesaj gelince action bar bildirimi ve istege bagli ses gonderir.*

## Features · Özellikler
- `/msg <oyuncu> <mesaj>` özel mesaj
- `/r <mesaj>` son mesajlaştığın kişiye hızlı cevap
- Mesaj gelince **action bar bildirimi** (1.8 dahil tüm sürümlerde)
- İsteğe bağlı bildirim sesi
- Gönderen/alıcı mesaj formatları tamamen configden ayarlanabilir

## Installation · Kurulum
1. [Releases](https://github.com/chizzar-dev/PrivateMessage/releases/latest) sayfasından `Msg.jar` dosyasını indir ve sunucunun `plugins/` klasörüne at.
2. Sunucuyu yeniden başlat.
3. `plugins/Msg/config.yml` dosyasından format, bildirim ve sesi düzenle.

## Commands · Komutlar
| Komut | Açıklama | Yetki |
|-------|----------|-------|
| `/msg <oyuncu> <mesaj>` | Özel mesaj gönderir | `msg.use` |
| `/reply <mesaj>` | Son mesajlaştığın kişiye cevap verir | `msg.use` |

**Alias:** `/w` `/tell` `/pm` `/mesaj` · `/r` `/cevapla`

## Permissions · Yetkiler
| Yetki | Açıklama | Varsayılan |
|-------|----------|------------|
| `msg.use` | Özel mesaj gönderir | herkes |

## Configuration · Ayarlar
| Anahtar | Açıklama |
|---------|----------|
| `console-name` | Konsoldan gönderilen mesajlarda görünecek ad |
| `format.sender` | Gönderenin gördüğü satır (`%target%`, `%message%`) |
| `format.receiver` | Alıcının gördüğü satır (`%sender%`, `%message%`) |
| `actionbar.enabled` | Action bar bildirimi aç/kapa |
| `actionbar.message` | Bildirim metni (`%sender%`) |
| `sound.enabled` / `sound.name` | Bildirim sesi |
| `messages.*` | Tüm mesajlar |

## Building · Derleme
```bash
mvn clean package
```
Çıktı · Output: `target/Msg.jar`

Her push [GitHub Actions](https://github.com/chizzar-dev/PrivateMessage/actions/workflows/build.yml) ile derlenir; `v*` etiketli sürümler jar'la birlikte [Releases](https://github.com/chizzar-dev/PrivateMessage/releases) sayfasına eklenir.
<br><sub>Every push is built by GitHub Actions; tagged `v*` releases attach the jar.</sub>

## License · Lisans
[MIT](LICENSE) — istediğin gibi kullan, değiştir, dağıt · use, modify and distribute freely

<div align="center"><sub>chizzar-dev · Minecraft plugins for 1.8 – 1.21.11 · <a href="https://discord.gg/forges">Discord</a></sub></div>
