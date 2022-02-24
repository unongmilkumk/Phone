package io.github.plus.command

import io.github.plus.Tools.Item
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.Inventory

class gui : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("stat", true)) {

            val inv: Inventory = Bukkit.createInventory(null, 54, "Stat")
            val item = Item()

            val item2 = item.createItemStack(Material.ACACIA_BOAT, "", "")
            inv.setItem(12, item2)
        }

        return true
    }
}