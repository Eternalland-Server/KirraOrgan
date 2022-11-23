package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirradungeon.server.Profile.Companion.profile
import net.sakuragame.eternal.kirraorgan.KirraOrganAPI
import net.sakuragame.eternal.kirraorgan.organ.InteractType
import net.sakuragame.eternal.kirraorgan.organ.impl.DungeonOrgan
import net.sakuragame.eternal.kirraorgan.organ.impl.NormalOrgan
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit
import taboolib.platform.util.groundBlock

object FunctionOrganListener {

    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        val player = e.player
        submit(async = true, delay = 3L) {
            FunctionOrgan.organs.forEach {
                it.baffle.reset(player.name)
            }
        }
    }

    @SubscribeEvent
    fun e(e: PlayerToggleSneakEvent) {
        val player = e.player
        val block = player.groundBlock
        if (!player.isSneaking || block.type == Material.AIR) {
            return
        }
        if (!FunctionOrgan.getOrganExistsByLocation(block.location)) {
            doEval(player = player, block = block, type = InteractType.SNEAK)
        }
    }

    @SubscribeEvent
    fun e(e: PlayerInteractEvent) {
        val player = e.player
        val block = e.clickedBlock ?: return
        val action = e.action
        if (block.type == Material.AIR) {
            return
        }
        if (!FunctionOrgan.getOrganExistsByLocation(block.location)) {
            return
        }
        when (action) {
            Action.LEFT_CLICK_BLOCK -> doEval(player, block, InteractType.INTERACT)
            Action.RIGHT_CLICK_BLOCK -> doEval(player, block, InteractType.INTERACT)
            else -> return
        }
    }

    @SubscribeEvent
    fun e(e: PlayerMoveEvent) {
        val player = e.player
        val to = e.to ?: return
        if (e.from.block == to.block) {
            return
        }
        val block = to.block.getRelative(BlockFace.DOWN)
        if (!FunctionOrgan.getOrganExistsByLocation(block.location)) {
            return
        }
        doEval(player, block, InteractType.WALK)
    }

    private fun doEval(player: Player, block: Block, type: InteractType) {
        val result = doEvalDungeon(player = player, block = block, type = type)
        if (!result) {
            doEvalNormal(player = player, block = block, type = type)
        }
    }

    private fun doEvalDungeon(player: Player, block: Block, type: InteractType): Boolean {
        if (KirraOrganAPI.dungeonPluginSupported) {
            val profile = player.profile() ?: return false
            val dungeon = profile.getIDungeon() ?: return false
            val dungeonOrgan = FunctionOrgan.getOrganByLocation<DungeonOrgan>(block.location) ?: return false
            if (!dungeonOrgan.check(player, type) || dungeonOrgan.dungeonId != dungeon.zone.id) {
                return false
            }
            dungeonOrgan.eval(player)
            return true
        }
        return false
    }

    private fun doEvalNormal(player: Player, block: Block, type: InteractType): Boolean {
        val normalOrgan = FunctionOrgan.getOrganByLocation<NormalOrgan>(block.location) ?: return false
        if (!normalOrgan.check(player, type)) {
            return false
        }
        normalOrgan.eval(player)
        return true
    }
}