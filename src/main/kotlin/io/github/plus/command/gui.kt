package io.github.plus.command

import io.github.plus.tools.Item
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class gui : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("stat", true)) {
            val item = Item()
            item.createItemStack(Material.ACACIA_BOAT, "", "")
        }

        return true
    }
}