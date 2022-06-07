package net.sakuragame.eternal.kirraorgan.event

import org.bukkit.Location
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class OrganEvalEvent(val player: Player, val id: String, val loc: Location) : BukkitProxyEvent()