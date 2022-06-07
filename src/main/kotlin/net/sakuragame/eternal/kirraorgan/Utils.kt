package net.sakuragame.eternal.kirraorgan

import org.bukkit.Location
import org.bukkit.command.CommandSender
import taboolib.module.chat.colored

fun CommandSender.sendCMessage(str: String) = sendMessage(str.colored())

fun Location.closeTo(loc: Location) = block.location.distanceSquared(loc.block.location) < 0.1