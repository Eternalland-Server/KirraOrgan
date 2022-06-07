package net.sakuragame.eternal.kirraorgan

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
        Loader.load()
    }
}