package net.sakuragame.eternal.kirraorgan.organ.impl

import net.sakuragame.eternal.kirraorgan.event.OrganEvalEvent
import net.sakuragame.eternal.kirraorgan.organ.IOrgan
import net.sakuragame.eternal.kirraorgan.organ.InteractType
import org.bukkit.Location
import org.bukkit.entity.Player
import taboolib.common5.Baffle
import java.util.concurrent.TimeUnit

@Suppress("ConvertSecondaryConstructorToPrimary")
class NormalOrgan : IOrgan<Location> {

    override val block: Location
    override val id: String
    override val interactType: InteractType
    override val baffle: Baffle

    override val links: MutableList<Location>

    constructor(block: Location, id: String, interactType: InteractType, delay: Long, links: MutableList<Location>) {
        this.block = block
        this.id = id
        this.interactType = interactType
        this.baffle = Baffle.of(delay, TimeUnit.MILLISECONDS)
        this.links = links
    }

    override fun eval(player: Player) {
        OrganEvalEvent(player, id, block).call()
    }
}