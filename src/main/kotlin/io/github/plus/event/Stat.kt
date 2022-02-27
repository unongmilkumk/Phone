package io.github.plus.event

import io.github.plus.Main
import io.github.plus.tools.Config
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import java.lang.Math.PI
import java.util.*

class Stat(main: Main): Listener {

    private val main: Main

    @EventHandler
    fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {

        if(e.damager is Player) {

            val config = Config(main).loadConfig(e.damager as Player)
            val uuid: UUID = e.damager.uniqueId


            val strength = config.getconfig()!!.getInt("players.$uuid.strength")

            e.damage *= 3.0 * ((2.0 / PI) * kotlin.math.atan(strength / 33.0)) + 1.0
            //e.damager.sendMessage("${e.damage}")
        }

    }

    init {
        this.main = main
    }

}