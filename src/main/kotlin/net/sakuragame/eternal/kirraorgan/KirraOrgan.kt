package net.sakuragame.eternal.kirraorgan

import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraOrgan : Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    lateinit var organs: Configuration

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    override fun onEnable() {
        KirraOrganAPI.reload()
    }
}