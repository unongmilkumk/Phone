package io.github.plus.event

import io.github.plus.Main
import io.github.plus.tools.Config
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import java.lang.Math.PI
import java.lang.Math.atan
import java.util.*

class Stat(main: Main): Listener {

    val main: Main
    val config = Config(main)

    @EventHandler
    fun onDamage(e: EntityDamageByEntityEvent) {

        if(e.damager is Player) {

            val config: Config = Config(main)

            val uuid: UUID = e.damager.uniqueId

            var exist: Boolean = true

            for(key: String in config.getconfig()?.getKeys(true)!!) {
                if(key=="players." + uuid) {
                    exist=false
                    break
                }
            }

            if(exist) {
                config.getconfig()!!.set("players." + uuid + ".strength", 0)
                config.getconfig()!!.set("players." + uuid + ".defense", 0)
                config.getconfig()!!.set("players." + uuid + ".luck", 0)
                config.getconfig()!!.set("players." + uuid + ".health", 0)
                config.saveconfig()
            }

            val strength = config.getconfig()!!.getInt("players." + uuid + ".strength")

            e.damage=3.0 * ((2.0/PI) * atan(strength / 33.0)) + 1.0

        }

    }

    init {
        this.main=main
    }

}