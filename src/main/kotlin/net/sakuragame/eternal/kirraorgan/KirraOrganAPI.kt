package net.sakuragame.eternal.kirraorgan

import org.bukkit.Bukkit
import taboolib.module.configuration.Configuration
import java.io.File

@Suppress("SpellCheckingInspection")
object KirraOrganAPI {

    val dungeonPluginSupported by lazy {
        Bukkit.getPluginManager().getPlugin("KirraDungeonServer") != null
    }

    fun reload() {
        val file = File(KirraOrgan.plugin.dataFolder, "organs.yml")
        if (!file.exists()) {
            file.createNewFile()
        }
        KirraOrgan.organs = Configuration.loadFromFile(file)
        Loader.load()
    }
}