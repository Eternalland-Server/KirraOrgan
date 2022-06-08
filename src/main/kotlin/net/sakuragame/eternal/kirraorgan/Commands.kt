package net.sakuragame.eternal.kirraorgan

import net.sakuragame.eternal.kirraorgan.organ.function.FunctionOrganCreate
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.giveItem

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

    @CommandBody
    val wand = subCommand {
        execute<Player> { player, _, _ ->
            player.giveItem(FunctionOrganCreate.organWand)
            player.sendCMessage("&c[System] &7完成.")
        }
    }
}