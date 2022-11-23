package net.sakuragame.eternal.kirraorgan.organ.impl

import net.sakuragame.eternal.kirradungeon.server.zone.ZoneLocation
import net.sakuragame.eternal.kirraorgan.event.OrganEvalEvent
import net.sakuragame.eternal.kirraorgan.organ.IOrgan
import net.sakuragame.eternal.kirraorgan.organ.InteractType
import org.bukkit.entity.Player
import taboolib.common5.Baffle
import java.util.concurrent.TimeUnit

@Suppress("ConvertSecondaryConstructorToPrimary")
class DungeonOrgan : IOrgan<ZoneLocation> {

    override val block: ZoneLocation
    override val id: String
    override val interactType: InteractType
    override val baffle: Baffle

    val dungeonId: String

    constructor(block: ZoneLocation, id: String, interactType: InteractType, delay: Long, dungeonId: String) {
        this.block = block
        this.id = id
        this.interactType = interactType
        this.baffle = Baffle.of(delay, TimeUnit.MILLISECONDS)
        this.dungeonId = dungeonId
    }

    override fun eval(player: Player) {
        OrganEvalEvent(player, id, block.toBukkitLocation(player.world)).call()
    }
}