package io.github.plus.command

import io.github.plus.io.github.plus.Main
import io.github.plus.Tools.GUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class Gui(main: Main) : CommandExecutor {
    val main: Main
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("stat", true)) {
            if(sender is Player) {

                val gui = GUI(main)

                val inv: Inventory = gui.createGUI("스탯", sender)

                sender.openInventory(inv)
            }
        }

        return true
    }
    init {
        this.main=main
    }
}