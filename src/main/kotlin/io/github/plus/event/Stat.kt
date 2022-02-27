package io.github.plus.event

import io.github.plus.Main
import io.github.plus.tools.Config
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import java.lang.Math.PI
import java.util.*
import kotlin.random.Random.Default.nextDouble

class Stat(main: Main): Listener {

    private val main: Main

    @EventHandler
    fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {

        if(e.damager is Player) {

            val config = Config(main).loadConfig(e.damager as Player)
            val uuid: UUID = e.damager.uniqueId

            val strength = config.getconfig()!!.getInt("players.$uuid.strength")

            e.damage *= 3.0 * ((2.0 / PI) * kotlin.math.atan(strength / 33.0)) + 1.0
            if(e.isCritical){
                val critical = config.getconfig()!!.getInt("players.$uuid.critical")
                e.damage *= 1.0 * ((2.0 / PI) * kotlin.math.atan(critical / 33.0)) + 1.0
            }
            if(e.entity is Player) {
                val victimConfig = Config(main).loadConfig(e.entity as Player)
                val defense = victimConfig.getconfig()!!.getInt("players.${e.entity.uniqueId}.defense")
                e.damage *= (1.0 - ((1.6 / PI) * kotlin.math.atan(defense / 26.4)))
                val dodging = victimConfig.getconfig()!!.getInt("players.${e.entity.uniqueId}.dodging")
                val chance = 0.33 * ((2.0 / PI) * kotlin.math.atan(dodging / 10.89))
                if(nextDouble() <= chance){
                    e.isCancelled = true
                    e.entity.sendMessage("§e회피§f!")
                }
            }

        }
        else {
            if(e.damager is Snowball || e.damager is EnderPearl || e.damager is Egg || e.damager is Arrow){
                if((e.damager as Projectile).shooter is Player){
                    val config = Config(main).loadConfig((e.damager as Projectile).shooter as Player)
                    val uuid: UUID = ((e.damager as Projectile).shooter as Player).uniqueId

                    val strength = config.getconfig()!!.getInt("players.$uuid.strength")

                    e.damage *= 3.0 * ((2.0 / PI) * kotlin.math.atan(strength / 33.0)) + 1.0
                    if(e.isCritical){
                        val critical = config.getconfig()!!.getInt("players.$uuid.critical")
                        e.damage *= 1.0 * ((2.0 / PI) * kotlin.math.atan(critical / 33.0)) + 1.0
                    }
                }

            }
            if(e.entity is Player) {
                val victimConfig = Config(main).loadConfig(e.entity as Player)
                val defense = victimConfig.getconfig()!!.getInt("players.${e.entity.uniqueId}.defense")
                e.damage *= (1.0 - ((1.6 / PI) * kotlin.math.atan(defense / 26.4)))
                val dodging = victimConfig.getconfig()!!.getInt("players.${e.entity.uniqueId}.dodging")
                val chance = 0.33 * ((2.0 / PI) * kotlin.math.atan(dodging / 10.89))
                if(nextDouble() <= chance){
                    e.isCancelled = true
                    e.entity.sendMessage("§e회피§f!")
                }
            }
        }

    }
     fun onEntityDamage(e : EntityDamageEvent){
         if(e.entity is Player) {
             val victimConfig = Config(main).loadConfig(e.entity as Player)
             val defense = victimConfig.getconfig()!!.getInt("players.${e.entity.uniqueId}.defense")
             e.damage *= (1.0 - ((1.6 / PI) * kotlin.math.atan(defense / 26.4)))
         }

     }


    init {
        this.main = main
    }

}