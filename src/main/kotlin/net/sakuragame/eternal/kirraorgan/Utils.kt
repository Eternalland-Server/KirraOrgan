package net.sakuragame.eternal.kirraorgan

import net.sakuragame.eternal.kirradungeon.server.Profile.Companion.profile
import net.sakuragame.eternal.kirradungeon.server.zone.Zone
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.module.chat.colored

fun CommandSender.sendCMessage(str: String) = sendMessage(str.colored())

fun Location.closeTo(loc: Location) = block.location.distanceSquared(loc.block.location) < 0.1

fun Player.getEditingDungeon(): String? {
    if (!KirraOrganAPI.dungeonPluginSupported) {
        return null
    }
    val profile = profile() ?: return null
    if (!profile.isEditing) {
        return null
    }
    val editingDungeonWorld = Zone.editingDungeonWorld ?: return null
    return editingDungeonWorld.worldIdentifier
}

fun Location.parseToString(): String {
    return "${world?.name},${x},$y},${z}"
}

fun String.toLocation(): Location? {
    return split(",").run {
        Location(
            Bukkit.getWorld(get(0)),
            getOrNull(1)?.toDoubleOrNull() ?: return null,
            getOrNull(2)?.toDoubleOrNull() ?: return null,
            getOrNull(3)?.toDoubleOrNull() ?: return null,
        )
    }
}