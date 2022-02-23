package io.github.plus.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Test : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.contentEquals("test")) {
            sender.sendMessage("why did you press this command?")
        }
        return true
    }
}