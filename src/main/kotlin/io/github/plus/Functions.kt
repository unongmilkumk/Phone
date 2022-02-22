package io.github.plus

import net.md_5.bungee.api.ChatMessageType
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class Functions {
    fun runTaskTimer(plugin : Plugin?, delay : Long, period : Long, task : () -> Unit) : Int?{
        val tasks = plugin?.let{ Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(it, task, delay, period)}
        return tasks

    }
    fun runDelay(plugin : Plugin?, delay : Long, task : () -> Unit){
        plugin?.let{ Bukkit.getServer().scheduler.scheduleSyncDelayedTask(it, task, delay)}

    }
    fun Player.sendActionBar(text : String) = this.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent(text))
}