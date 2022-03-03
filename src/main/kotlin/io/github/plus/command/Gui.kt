package io.github.plus.command

import io.github.plus.Main
import io.github.plus.getCustomXp
import io.github.plus.setCustomXp
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

class Gui(main: Main) : CommandExecutor {
    private val main: Main
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("stat", true)) {
            val p : Player = sender as Player
            if(sender.type == EntityType.PLAYER) {
                if (args.isEmpty()) {
                    sender.closeInventory()

                    val gui = GUI(main)

                    val inv: Inventory = gui.createGUI("${sender.name}님의 스탯", sender)

                    sender.openInventory(inv)
                }
                else if (args[0] == "reset") {
                    val config = Config(main)
                    val uuid: UUID = p.uniqueId

                    val levels = config.getconfig()!!.getInt("players.$uuid.strength") + config.getconfig()!!.getInt("players.$uuid.defense") + config.getconfig()!!.getInt("players.$uuid.critical") + config.getconfig()!!.getInt("players.$uuid.dodging") + config.getconfig()!!.getInt("players.$uuid.health")

                    config.getconfig()!!.set("players.$uuid.strength", 0)
                    config.getconfig()!!.set("players.$uuid.defense", 0)
                    config.getconfig()!!.set("players.$uuid.critical", 0)
                    config.getconfig()!!.set("players.$uuid.dodging", 0)
                    config.getconfig()!!.set("players.$uuid.health", 0)
                    config.saveconfig()
                    p.setCustomXp(p.getCustomXp() + levels*35)
                    p.maxHealth = 20.0
                    p.sendMessage("스탯을 초기화 했습니다")
                }
            }
        }

        return true
    }
    init {
        this.main = main
    }
}