package io.github.plus.Tools

import io.github.plus.io.github.plus.Main
import io.github.plus.tools.Item
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

class GUI(main: Main) {

    val main: Main

    fun createGUI(text: String, player: Player): Inventory {

        val item = Item()

        val config: config = config(main)

        val uuid: UUID = player.uniqueId

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
        }

        val stregth: Int = config.getconfig()!!.getInt("players." + uuid + ".strength")
        val defense: Int = config.getconfig()!!.getInt("players." + uuid + ".defense")
        val luck: Int = config.getconfig()!!.getInt("players." + uuid + ".luck")
        val health: Int = config.getconfig()!!.getInt("players." + uuid + ".health")

        val inv: Inventory = Bukkit.createInventory(null, 27, Component.text(text))



        inv.setItem(10, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "힘", stregth.toString()))
        inv.setItem(11, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "방어력", defense.toString()))
        inv.setItem(12, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "운", luck.toString()))
        inv.setItem(13, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "체력", health.toString()))

        return inv

    }

    init {
        this.main=main
    }

}