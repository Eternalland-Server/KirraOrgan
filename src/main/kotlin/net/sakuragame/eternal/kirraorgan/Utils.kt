package net.sakuragame.eternal.kirraorgan

import org.bukkit.command.CommandSender
import taboolib.module.chat.colored

fun CommandSender.sendCMessage(str: String) = sendMessage(str.colored())