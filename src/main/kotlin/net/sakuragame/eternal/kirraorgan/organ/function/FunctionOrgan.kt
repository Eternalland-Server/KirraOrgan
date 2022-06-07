package net.sakuragame.eternal.kirraorgan.organ.function

import net.sakuragame.eternal.kirraorgan.organ.IOrgan

object FunctionOrgan {

    val organs = mutableListOf<IOrgan<*>>()

    fun add(organ: IOrgan<*>) {
        organs += organ
    }
}