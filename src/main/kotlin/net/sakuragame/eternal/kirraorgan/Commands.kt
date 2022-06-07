package net.sakuragame.eternal.kirraorgan

import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand

@Suppress("SpellCheckingInspection")
@CommandHeader(name = "KirraOrgan", aliases = ["ko"])
object Commands {

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { sender, _, _ ->
            KirraOrganAPI.reload()
            sender.sendCMessage("&c[System] &7已重载.")
        }
    }
}