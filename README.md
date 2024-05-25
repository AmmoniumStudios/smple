# smple

SMPle (pronounced: simple) is a suite of plugins aimed to enhance the vanilla experience. 
We believe in providing a simple-to-use one-stop no-bs solution for all your server needs.
We hope to create a plugin that has all the features you need, without any of the bloat you don't.


## Commands

Here are a list of commands and what they do organised per-plugin. 
The command is structured 

```
/commandname <requiredArgs> [optionalArgs] [list|of|possible|args] [args:typeOfArg] [(a)rgThatNeedAMinimumAmmtToTrigger]
```

### Misc

- [`/help [commandName]`](#cmd-help)
- [`/whois [displayname:string]`](#cmd-whois)
- [`/nickname set <displayname:string>`](#cmd-nickname)
- [`/nickname remove [username:string]`](#cmd-nickname)
- [`/nickname history <username:string`](#cmd-nickname)
- [`/pweather [weather|type]`](#cmd-pweather)
- [`/ptime [tick:int]`](#cmd-ptime)
- [`/sudo <command:string> [args... ]`](#cmd-sudo)
- [`/doas <user:string> <command:string> [args... ]`](#cmd-doas)
- [`/cmdlogs`](#cmd-logs)
- [`/playtime`](#cmd-playtime)

### Chat

- [`/msg <username:string> <msg:string>`](#cmd-msg)
- [`/(r)eply <msg:string>`](#cmd-reply)
- [`/spy [enable|disable]`](#cmd-spy)
- [`/sc <msg:string>`](#cmd-staff-chat)
- [`/block <user:string>`](#cmd-block)
- [`/unblock <user:string>`](#cmd-unblock)
 
### Workbench

- [`/anvil`](#cmd-anvil)
- [`/cartography`](#cmd-cartography-table)
- [`/craft`](#cmd-crafting-table)
- [`/etable`](#cmd-enchantment-table)
- [`/echest`](#cmd-ender-chest)
- [`/grind`](#cmd-grinder)
- [`/loom`](#cmd-loom)
- [`/smith`](#cmd-smithing-table)
- [`/smelt`](#cmd-smelt)

### Warps

- [`/tp <username:string>`](#cmd-teleport)
- [`/tphere <username:string>`](#cmd-teleport)
- [`/tpaccept`](#cmd-teleport)
- [`/tpdeny`](#cmd-teleport)
- [`/pwarp <name:string>`](#cmd-pwarp)
- [`/pwarp list`](#cmd-pwarp)
- [`/pwarp set <name:string>`](#cmd-pwarp)
- [`/pwarp del <name:string>`](#cmd-pwarp)
- [`/warp <name:string>`](#cmd-warp)
- [`/warp list`](#cmd-warp)
- [`/warp set <name:string>`](#cmd-warp)
- [`/warp del <name:string>`](#cmd-warp)
- [`/setspawn`](#cmd-spawn)
- [`/spawn`](#cmd-spawn)
- [`/sethome`](#cmd-home)
- [`/home`](#cmd-home)
- [`/rtp`](#cmd-rtp)
- [`/back`](#cmd-back)

### Moderation

- [`/warn <name:string> [reason:string]`](#cmd-warn)
- [`/mute <name:string> [reason:string]`](#cmd-mute)
- [`/unmute <name:string> [reason:string]`](#cmd-mute)
- [`/kick <name:string> [reason:string]`](#cmd-kick)
- [`/ban <name:string> [reason:string]`](#cmd-ban)
- [`/unban <name:string> [reason:string]`](#cmd-ban)
- [`/spectate <name:string>`](#cmd-spectate)
- [`/unspectate`](#cmd-spectate)
- [`/rules`](#cmd-rules)
- [`/rules <ruleid:string>`](#cmd-rules)
- [`/punish <name:string> <rule-violation... >`](#cmd-punish)
- [`/punishments list <name:string>`](#cmd-punishments)
- [`/punishments remove <name:string> <punishment:int>`](#cmd-punishments)

## Docs

### CMD: Help

- `smple.misc.help.run`

Redirects user to the help page
If a command is specified, it will redirect the user to the help page of that command

### CMD: Whois

- `smple.misc.whois.run`

Allows user to run the command
Whois allows a user to see the last time a user has logged in and if they are online or not
As well as their playtime.
Staff in spectate are showed to be offline.

### CMD: Nickname

- `smple.misc.nickname.self`
- `smple.misc.nickname.history`
- `smple.misc.nickname.manage`

Self allows user to manage their own nickname
History shows a list of all the nicknames a user has had
Manage allows user to manage the nickname of others

### CMD: PWeather

- `smple.misc.pweather.run`

Allows user to manage their own weather

### CMD: PTime

- `smple.misc.ptime.run`

Allows user to set their own time

### CMD: Sudo

- `smple.misc.sudo.run`

Allows a user to run a command as root

### CMD: Doas

- `smple.misc.doas.run`

Allows a user to run a command as another player

### CMD: Logs

- `smple.misc.cmdlogs.run`

Allows a user to see the command logs
If a name is specified, it will show the logs of that user

### CMD: Playtime

- `smple.misc.playtime.run`

Allows a user to see their own playtime
If a name is specified, it will show the playtime of that user

### CMD: Msg

- `smple.chat.msg.send`

Send allows a user to privately message another user

### CMD: Reply

- `smple.chat.msg.reply`

Reply allows a user to reply to a users private msg

### CMD: Block  

- `smple.chat.msg.block`

Block allows a user to block another user.
The other user then cannot be pmed and
the other user cant pm the original user.
It also hides the chat messages of that user

### CMD: Unblock

- `smple.chat.msg.block`

Unblock allows a user to be unblocked by another user.

### CMD: Spy

- `smple.chat.spy.run`

Toggle command to allow a user to see private messages

### CMD: Staff Chat

- `smple.chat.sc.read`
- `smple.chat.sc.send`

A private chatroom for server staff to talk on
Read allows a user to read the chat
Send allows a user to send a message to the chat

### CMD: Anvil

- `smple.workbench.anvil`

Allows a user to open an anvil without having one

### CMD: Cartography Table

- `smple.workbench.cartography`

Allows a user to open a cartography table without having one

### CMD: Crafting Table

- `smple.workbench.crafting`

Allows a user to open a cartography tbale without having one.

### CMD: Enchantment Table

- `smple.workbench.crafting`

Allows a user to open a max level enchanting table wihtout having one

### CMD: Ender Chest

- `smple.workbench.echest.run`
- `smple.workbench.echest.others`   

Allows a user to open their ender chest without having one.
If they have the others perm, they can open other ppls echests

### CMD: Grinder

- `smple.workbench.grinder`

Allows a user to open a grindstone without having one

### CMD: Loom

- `smple.workbench.loom`

Allows a user to open a loom without ahving one

### CMD: Smithing Table

- `smple.workbench.smithing`

Allows a user to open a smithing table without having one

### CMD: Smelt

- `smple.workbench.smelt`

Allows a user to smelt the item they currently have in their hand

### CMD: Teleport

- `smple.warps.tp.run`
- `smple.warps.tp.here`
- `smple.warps.noaccept`
- `smple.warps.nodelay`
- `smple.warps.nocooldown`

run allows a user to teleport/request to another user
here allows a user to teleport a user to them
noaccept means the reciever does not have to accept the tp
nodelay means the tp is instant
nocooldown means the tp has no cooldown

### CMD: Warp

- `smple.warps.warp.run`
- `smple.warps.warp.set`
- `smple.warps.warp.del`
- `smple.warps.warp.list`

run allows a user to warp to a warp
set allows a user to set a warp
del allows a user to delete a warp
list allows a user to see all the warps

### CMD: Pwarp

- `smple.warps.pwarp.run`
- `smple.warps.pwarp.set`
- `smple.warps.pwarp.del`
- `smple.warps.pwarp.list`

run allows a user to warp to a pwarp
set allows a user to set a pwarp
del allows a user to delete a pwarp
list allows a user to see all the pwarp

### CMD: Spawn

- `smple.warps.spawn.set`
- `smple.warps.spawn.run`

set allows a user to set the spawn
run allows a user to warp to the spawn

### CMD: Home

- `smple.warps.home.set`
- `smple.warps.home.run`

set allows a user to set their home
run allows a user to warp to their home
If set on sleep is enabled, the users home will be set to their bed location

### CMD: Rtp

- `smple.warps.rtp.run`

Allows a user to randomly teleport within the world
SEttings for the rtp can be set in the config

### CMD: Back

- `smple.warps.back.run`

Allows a user to teleport back to their last location

### CMD: Warn

- `smple.moderation.warn.run`
- `smple.moderation.warn.nowarn`
- `smple.moderation.warn.silent`

run allows a user to warn another user
nowarn allows a user to not be warned
silent allows a user to warn another user silently

### CMD: Mute

- `smple.moderation.mute.run`
- `smple.moderation.mute.nomute`
- `smple.moderation.mute.silent`
- `smple.moderation.mute.perm`
- `smple.moderation.mute.unmute`

run allows a user to mute another user
nomute allows a user to not be muted
silent allows a user to mute another user silently
perm allows a user to mute another user permanently
unmute allows a user to unmute another user

### CMD: Kick

- `smple.moderation.kick.run`
- `smple.moderation.kick.nokick`
- `smple.moderation.kick.silent`

run allows a user to kick another user
nokick allows a user to not be kicked
silent allows a user to kick another user silently

### CMD: Ban

- `smple.moderation.ban.run`
- `smple.moderation.ban.noban`
- `smple.moderation.ban.silent`
- `smple.moderation.ban.perm`
- `smple.moderation.ban.unban`

run allows a user to ban another user
noban allows a user to not be banned
silent allows a user to ban another user silently
perm allows a user to ban another user permanently
unban allows a user to unban another user

### CMD: Spectate

- `smple.moderation.spectate`

allows an admin to spectate another user

### CMD: Rules

- `smple.moderation.rules`

Allows a user to see the rules
If a rule id is specified, it will show the rule

### CMD: Punish

- `smple.moderation.punish.run`
- `smple.moderation.punish.nopunish`
- `smple.moderation.punish.silent`

run Allows a user to be punished for breaking a rule. 
nopunish allows a user to not be punished
silent allows a user to be punished silently
Punishments are set in the config. 

### CMD: Punishments

- `smple.moderation.punishments.list`
- `smple.moderation.punishments.remove`
- `smple.moderation.punishments.clear`

List allows a user to see all the punishments of a user
Remove allows a user to remove a punishment of a user
Clear allows a user to clear all the punishments of a user