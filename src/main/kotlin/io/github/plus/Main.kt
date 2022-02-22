package io.github.jesecci

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

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