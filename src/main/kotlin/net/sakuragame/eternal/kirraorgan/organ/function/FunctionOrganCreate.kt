package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirraorgan.KirraOrganAPI
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import taboolib.platform.util.buildItem

object FunctionOrganCreate {

    val organWand by lazy {
        buildItem(Material.STICK) {
            name = "&e&l脚本魔杖".colored()
            lore += ""
            lore += "&7左键创建脚本方块".colored()
            lore += "&7右键删除脚本方块".colored()
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
        when (e.action) {
            Action.LEFT_CLICK_BLOCK -> runCreate(player, block)
            Action.RIGHT_CLICK_BLOCK -> runDelete(player, block)
            else -> return
        }
    }

    private fun runCreate(player: Player, block: Block) {

    }

    private fun runDelete(player: Player, block: Block) {

    }
}