package io.github.plus

import net.kyori.adventure.text.Component
import net.md_5.bungee.api.ChatMessageType
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.awt.TextComponent

class Functions {
    fun runTaskTimer(plugin : Plugin?, delay : Long, period : Long, task : () -> Unit): BukkitRunnable {

        class r: BukkitRunnable() {
            override fun run() {
                task()
            }
        }

        val rr = r()
        rr.runTaskTimer(plugin!!, delay, period)

        return rr

    }
    fun runDelay(plugin : Plugin?, delay : Long, task : () -> Unit): BukkitRunnable {

        class r: BukkitRunnable() {
            override fun run() {
                task()
            }
        }

        val rr = r()
        rr.runTaskLater(plugin!!, delay)

        return rr

    }
    fun Player.sendActionBar(text : String) {
        val component: Component = Component.text(text)

        this.sendActionBar(component)

    }
}