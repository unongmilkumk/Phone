package io.github.plus

import io.github.plus.Tools.Item
import net.kyori.adventure.text.Component
import net.md_5.bungee.api.ChatMessageType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.awt.TextComponent

class Functions {
    fun runTaskTimer(plugin : Plugin?, delay : Long, period : Long, task : () -> Unit): BukkitRunnable {

        class Runnable: BukkitRunnable() {
            override fun run() {
                task()
            }
        }


        Runnable().runTaskTimer(plugin!!, delay, period)

        return Runnable()

    }
    fun runDelay(plugin : Plugin?, delay : Long, task : () -> Unit): BukkitRunnable {

        class Runnable: BukkitRunnable() {
            override fun run() {
                task()
            }

        }


        Runnable().runTaskLater(plugin!!, delay)

        return Runnable()

    }
    fun Player.sendActionBar(text : String) {
        val component: Component = Component.text(text)

        this.sendActionBar(component)

    }
}