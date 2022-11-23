package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirraorgan.Loader
import net.sakuragame.eternal.kirraorgan.sendCMessage
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import taboolib.module.nms.inputSign
import taboolib.platform.util.buildItem

object FunctionOrganCreate {

    val organWand by lazy {
        buildItem(Material.STICK) {
            name = "&e&l脚本魔杖".colored()
            lore += ""
            lore += "&7左键创建脚本方块".colored()
            lore += "&7右键编辑脚本方块".colored()
            lore += ""
            shiny()
        }
    }

    @SubscribeEvent
    fun e(e: PlayerInteractEvent) {
        val item = e.item ?: return
        val block = e.clickedBlock ?: return
        val player = e.player
        if (!item.isSimilar(organWand) || block.type == Material.AIR) {
            return
        }
        e.isCancelled = true
        when (e.action) {
            Action.LEFT_CLICK_BLOCK -> runCreate(player, block)
            Action.RIGHT_CLICK_BLOCK -> runEdit(player, block)
            else -> return
        }
    }

    private fun runCreate(player: Player, block: Block) {
        if (FunctionOrgan.getOrganExistsByLocation(block.location)) {
            player.sendCMessage("&c[System] &7该地已存在一个脚本方块.")
            return
        }
        player.inputSign(arrayOf("", "", "请在第一行输入内容")) { arr ->
            val id = arr[0]
            if (id.isEmpty()) {
                player.sendCMessage("&c[System] &7格式错误.")
                return@inputSign
            }
            Loader.set(player, block, id)
            player.sendCMessage("&c[System] &7正在创建.")
        }
    }

    private fun runEdit(player: Player, block: Block) {
        if (!FunctionOrgan.getOrganExistsByLocation(block.location)) {
            player.sendCMessage("&c[System] &7该地不存在脚本方块.")
            return
        }
    }
}