package net.sakuragame.eternal.kirraorgan

import net.sakuragame.eternal.kirradungeon.server.zone.ZoneLocation
import net.sakuragame.eternal.kirraorgan.organ.InteractType
import net.sakuragame.eternal.kirraorgan.organ.function.FunctionOrgan
import net.sakuragame.eternal.kirraorgan.organ.impl.DungeonOrgan
import net.sakuragame.eternal.kirraorgan.organ.impl.NormalOrgan
import org.bukkit.Bukkit
import org.bukkit.Effect
import org.bukkit.block.Block
import org.bukkit.entity.Player
import taboolib.common.platform.function.info

object Loader {

    fun load() {
        FunctionOrgan.organs.clear()
        KirraOrgan.organs.getKeys(false).forEach {
            val type = KirraOrgan.organs.getString("$it.type") ?: return@forEach
            val organ = when (type) {
                "NORMAL" -> loadNormalOrgan(it)
                "DUNGEON" -> loadDungeonOrgan(it)
                else -> null
            } ?: return@forEach
            FunctionOrgan.organs += organ
        }
    }

    private fun loadNormalOrgan(id: String): NormalOrgan? {
        val loc = KirraOrgan.organs.getString("$id.location")?.toLocation() ?: return null
        val interactType = InteractType.values().find { it.name == KirraOrgan.organs.getString("$it.interact-type") } ?: return null
        val delay = KirraOrgan.organs.getInt("$id.delay").toLong()
        val links = KirraOrgan.organs.getStringList("$id.links")
            .map { it.toLocation() ?: return null }
            .toMutableList()
        return NormalOrgan(loc, id, interactType, delay, links)
    }

    private fun loadDungeonOrgan(id: String): DungeonOrgan? {
        val rawLoc = KirraOrgan.organs.getString("$id.location") ?: return null
        val loc = ZoneLocation.parseToZoneLocation(rawLoc) ?: return null
        val interactType = InteractType.values().find { KirraOrgan.organs.getString("$id.interact-type") == it.name } ?: return null
        val delay = KirraOrgan.organs.getInt("$id.delay").toLong()
        val links = KirraOrgan.organs.getStringList("$id.links")
            .map { ZoneLocation.parseToZoneLocation(it) ?: return null }
            .toMutableList()
        val dungeonId = KirraOrgan.organs.getString("$id.dungeon-id") ?: return null
        return DungeonOrgan(loc, id, interactType, delay, links, dungeonId)
    }

    private fun save() {
        KirraOrgan.organs.saveToFile()
    }

    fun set(player: Player, block: Block, id: String) {
        val dungeonId = player.getEditingDungeon()
        when (dungeonId == null) {
            true -> {
                KirraOrgan.organs["$id.type"] = "NORMAL"
                KirraOrgan.organs["$id.location"] = block.location.parseToString()
                player.sendCMessage("&c[System] &7已创建普通脚本模板.")
            }
            false -> {
                KirraOrgan.organs["$id.type"] = "DUNGEON"
                KirraOrgan.organs["$id.location"] = ZoneLocation.parseToZoneLocation(block.location).toString()
                KirraOrgan.organs["$id.dungeon-id"] = dungeonId
                player.sendCMessage("&c[System] &7已创建副本脚本模板.")
            }
        }
        KirraOrgan.organs["$id.interact-type"] = InteractType.INTERACT.name
        KirraOrgan.organs["$id.delay"] = 50
        KirraOrgan.organs["$id.links"] = mutableListOf<String>()
        block.world.playEffect(block.location, Effect.MOBSPAWNER_FLAMES, 3)
        save()
        load()
    }
}