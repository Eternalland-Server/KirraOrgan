package net.sakuragame.eternal.kirraorgan.skript.event

import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import net.sakuragame.eternal.kirraorgan.event.OrganEvalEvent
import org.bukkit.event.Event

@Name("On Organ Interact")
class OrganEvalEvent : SkriptEvent() {

    override fun toString(e: Event?, debug: Boolean) = null

    override fun init(args: Array<Literal<*>>?, matchedPattern: Int, parseResult: SkriptParser.ParseResult?) = true

    override fun check(e: Event): Boolean {
        if (e is OrganEvalEvent) {
            return true
        }
        return false
    }
}