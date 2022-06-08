package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirraorgan.closeTo
import net.sakuragame.eternal.kirraorgan.organ.IOrgan
import net.sakuragame.eternal.kirraorgan.organ.impl.DungeonOrgan
import net.sakuragame.eternal.kirraorgan.organ.impl.NormalOrgan
import org.bukkit.Location

object FunctionOrgan {

    val organs = mutableListOf<IOrgan<*>>()

    inline fun <reified T : IOrgan<*>> getOrganByLocation(loc: Location): T? {
        return organs.filterIsInstance<T>()
            .find {
                when (val instance = it as Any?) {
                    is NormalOrgan -> instance.block.closeTo(loc)
                    is DungeonOrgan -> instance.block.toBukkitLocation(loc.world).closeTo(loc)
                    else -> return null
                }
            }
    }

    fun getOrganExistsByLocation(loc: Location): Boolean {
        val block = loc.block.location
        return when {
            getOrganByLocation<DungeonOrgan>(block) != null -> true
            getOrganByLocation<NormalOrgan>(block) != null -> true
            else -> false
        }
    }

    fun add(organ: IOrgan<*>) {
        organs += organ
    }
}