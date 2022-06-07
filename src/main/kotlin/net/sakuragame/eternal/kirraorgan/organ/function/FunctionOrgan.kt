package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirradungeon.server.zone.ZoneLocation
import net.sakuragame.eternal.kirraorgan.closeTo
import net.sakuragame.eternal.kirraorgan.organ.IOrgan
import org.bukkit.Location

object FunctionOrgan {

    val organs = mutableListOf<IOrgan<*>>()

    inline fun <reified T : IOrgan<*>> getOrganByLocation(loc: Location): T? {
        return FunctionOrgan.organs.filterIsInstance<T>()
            .find {
                when (val organBlock = it.block) {
                    is Location -> organBlock.closeTo(loc.block.location)
                    is ZoneLocation -> organBlock.toBukkitLocation(loc.world).closeTo(loc.block.location)
                    else -> return null
                }
            }
    }

    fun add(organ: IOrgan<*>) {
        organs += organ
    }
}