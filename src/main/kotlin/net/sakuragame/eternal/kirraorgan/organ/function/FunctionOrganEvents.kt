package net.sakuragame.eternal.kirraorgan.organ.function

import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit

object FunctionOrganEvents {

    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        val player = e.player
        submit(async = true, delay = 3L) {
            FunctionOrgan.organs.forEach {
                it.baffle.reset(player.name)
            }
        }
    }
}