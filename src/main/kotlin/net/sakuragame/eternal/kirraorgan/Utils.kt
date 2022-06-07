package net.sakuragame.eternal.kirraorgan

import org.bukkit.Location
import org.bukkit.command.CommandSender
import taboolib.module.chat.colored

fun CommandSender.sendCMessage(str: String) = sendMessage(str.colored())

fun Location.closeTo(loc: Location) = distanceSquared(loc) < 0.1