package io.github.plus.tools

import io.github.plus.Main
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class GUI(main: Main) {

    private val main: Main

    fun createGUI(text: String, player: Player): Inventory {



        val item = Item()

        val config = Config(main).loadConfig(player)
        val uuid = player.uniqueId


        val strength: Int = config.getconfig()!!.getInt("players.$uuid.strength")
        val defense: Int = config.getconfig()!!.getInt("players.$uuid.defense")
        val critical: Int = config.getconfig()!!.getInt("players.$uuid.critical")
        val health: Int = config.getconfig()!!.getInt("players.$uuid.health")
        val dodging: Int = config.getconfig()!!.getInt("players.$uuid.dodging")

        val inv: Inventory = Bukkit.createInventory(null, 27, Component.text(text))



        inv.setItem(9, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "힘", strength.toString()))
        inv.setItem(11, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "방어력", defense.toString()))
        inv.setItem(13, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "크리티컬", critical.toString()))
        inv.setItem(15, item.createItemStack(org.bukkit.Material.ACACIA_BOAT, "체력", health.toString()))
        inv.setItem(17, item.createItemStack(Material.ACACIA_BOAT, "회피", dodging.toString()))

        io.github.plus.stat.inv.add(inv)

        return inv



    }

    init {
        this.main = main
    }

}