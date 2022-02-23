package io.github.plus

import net.md_5.bungee.api.ChatMessageType
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

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
    fun Player.sendActionBar(text : String) = this.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent(text))
}