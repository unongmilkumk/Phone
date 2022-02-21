package io.github.jesecci

import io.github.monun.kommand.*
import io.github.monun.kommand.Kommand.Companion.register
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.meta.Damageable

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.min

class Main : JavaPlugin(), Listener {

    override fun onEnable() {
        server.consoleSender.sendMessage("림하")
        server.pluginManager.registerEvents(Test(), this@Main)
        setUpCommands()
    }
    override fun onDisable() {
        server.consoleSender.sendMessage("림바")
    }

    @Name("커맨드 세팅")
   fun setUpCommands(){
        kommand{
            register("health"){
                then("value" to int()){
                    executes {
                        val value : Int by it
                        player.maxHealth = value.toDouble()
                    }
                }
            }
        }
    }


}