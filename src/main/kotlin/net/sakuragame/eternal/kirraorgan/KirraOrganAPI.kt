package net.sakuragame.eternal.kirraorgan

import net.sakuragame.eternal.kirradungeon.server.zone.ZoneLocation
import net.sakuragame.eternal.kirraorgan.organ.IOrgan
import net.sakuragame.eternal.kirraorgan.organ.function.FunctionOrgan
import org.bukkit.Location
import taboolib.module.configuration.Configuration
import java.io.File

@Suppress("SpellCheckingInspection")
object KirraOrganAPI {

    fun reload() {
        val file = File(KirraOrgan.plugin.dataFolder, "config.yml")
        if (!file.exists()) {
            file.createNewFile()
        }
        KirraOrgan.organs = Configuration.loadFromFile(file)
    }

    inline fun <reified T : IOrgan<*>> getOrganByLocation(loc: Location): T? {
        return FunctionOrgan.organs.filterIsInstance<T>()
            .find {
                when (val location = it.block) {
                    is Location -> location == location.block.location
                    is ZoneLocation -> location.toBukkitLocation(loc.world) == loc.block.location
                    else -> return null
                }
            }
    }
}