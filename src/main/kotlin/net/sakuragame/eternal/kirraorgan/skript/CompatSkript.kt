package net.sakuragame.eternal.kirraorgan.skript

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.sakuragame.eternal.kirraorgan.event.OrganEvalEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info

@Suppress("SpellCheckingInspection")
object CompatSkript {

    @Awake(LifeCycle.ENABLE)
    fun e() {
        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            return
        }
        info("与 Skript 进行挂钩.")
        registerEvents()
    }

    private fun registerEvents() {
        Skript.registerEvent("organ interact", SimpleEvent::class.java, OrganEvalEvent::class.java, "organ interact")
        EventValues.registerEventValue(
            OrganEvalEvent::class.java, Player::class.java,
            object : Getter<Player, OrganEvalEvent>() {

                override fun get(arg: OrganEvalEvent): Player {
                    return arg.player
                }
            }, 0
        )
        EventValues.registerEventValue(
            OrganEvalEvent::class.java, String::class.java,
            object : Getter<String, OrganEvalEvent>() {

                override fun get(arg: OrganEvalEvent): String {
                    return arg.id
                }
            }, 0
        )
        EventValues.registerEventValue(
            OrganEvalEvent::class.java, Location::class.java,
            object : Getter<Location, OrganEvalEvent>() {

                override fun get(arg: OrganEvalEvent): Location {
                    return arg.loc
                }
            }, 0
        )
    }
}