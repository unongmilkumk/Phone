package io.github.plus.command

import io.github.plus.Main
import io.github.plus.tools.GUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class Gui(main: Main) : CommandExecutor {
    private val main: Main
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("stat", true)) {
            if(sender is Player) {

                sender.closeInventory()

                val gui = GUI(main)

                val inv: Inventory = gui.createGUI("${sender.name}님의 스탯", sender)

                sender.openInventory(inv)

            }
        }

        return true
    }
    init {
        this.main = main
    }
}