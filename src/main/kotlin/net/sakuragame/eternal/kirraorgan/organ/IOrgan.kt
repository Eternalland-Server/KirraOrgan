package net.sakuragame.eternal.kirraorgan.organ

import org.bukkit.entity.Player
import taboolib.common5.Baffle

@JvmDefaultWithoutCompatibility
interface IOrgan<T> {

    val block: T
    val id: String
    val interactType: InteractType
    val baffle: Baffle

    fun eval(player: Player)

    fun baffle(): Boolean {
        if (!baffle.hasNext()) {
            return false
        }
        baffle.next()
        return true
    }

    fun check(player: Player, type: InteractType): Boolean {
        return when {
            !baffle() -> false
            type != interactType -> false
            else -> true
        }
    }
}