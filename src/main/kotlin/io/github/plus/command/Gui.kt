package io.github.plus.command

import io.github.plus.Main
import io.github.plus.tools.Config
import io.github.plus.tools.GUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

class Gui(main: Main) : CommandExecutor, TabCompleter {
    private val main: Main
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("stat", true)) {
            val p : Player = sender as Player
            if(sender.type == EntityType.PLAYER) {
                if (args.size == 0) {
                    sender.closeInventory()

                    val gui = GUI(main)

                    val inv: Inventory = gui.createGUI("${sender.name}님의 스탯", sender)

                    sender.openInventory(inv)
                }
                else if (args[0] == "reset") {
                    val config = Config(main)
                    val uuid: UUID = p.uniqueId
                    config.getconfig()!!.set("players.$uuid.strength", 0)
                    config.getconfig()!!.set("players.$uuid.defense", 0)
                    config.getconfig()!!.set("players.$uuid.critical", 0)
                    config.getconfig()!!.set("players.$uuid.dodging", 0)
                    config.getconfig()!!.set("players.$uuid.health", 0)
                    config.saveconfig()
                    p.maxHealth = 20.0
                }
            }
        }

        return true
    }
    init {
        this.main = main
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        return if (alias.equals("sample", true)) {
            if (args.size === 1) {
                val returns1 : MutableList<String> = ArrayList()
                returns1.add("sampleTab")
                val returns2 : MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                returns2
            } else if (args.size === 2) {
                val returns1: MutableList<String> = ArrayList()
                if (args[0].equals("set", true)) {
                    returns1.add("sampleTab2")
                } else {
                    return mutableListOf("")
                }
                val returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[1].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                return returns2
            } else {
                return mutableListOf("")
            }
        } else return null
    }
}