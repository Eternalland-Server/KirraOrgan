package net.sakuragame.eternal.kirraorgan.organ

import org.bukkit.entity.Player
import taboolib.common5.Baffle

@JvmDefaultWithoutCompatibility
interface IOrgan<T> {

    val block: T
    val id: String
    val interactType: InteractType
    val baffle: Baffle

    val links: MutableList<T>

    fun eval(player: Player)

    fun baffle(player: Player): Boolean {
        if (!baffle.hasNext(player.name)) {
            return false
        }
        baffle.next(player.name)
        return true
    }
}