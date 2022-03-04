package io.github.plus

import event.JavaEvent
import io.github.plus.command.Gui
import io.github.plus.event.TestEvent
import io.github.plus.command.CommandSample
//import io.github.plus.command.GuiTabCompleter
import io.github.plus.event.CalculateExp
import io.github.plus.event.Stat
import io.github.plus.tools.Config
import io.github.plus.stat.Inv
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    private val config = Config(this)

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")

        //커맨드 setExecutor
        server.getPluginCommand("stat")?.setExecutor(Gui(this@Main))
        server.getPluginCommand("sample")?.setExecutor(CommandSample())

        //커맨드 setTabCompleter
        server.getPluginCommand("sample")?.tabCompleter = CommandSample()
//        server.getPluginCommand("stat")?.tabCompleter = GuiTabCompleter()

        //이벤트
        server.pluginManager.registerEvents(TestEvent(), this@Main)
        server.pluginManager.registerEvents(JavaEvent(), this@Main)
        server.pluginManager.registerEvents(CalculateExp(this@Main), this@Main)
        server.pluginManager.registerEvents(Inv(this@Main), this@Main)
        server.pluginManager.registerEvents(Stat(this@Main), this@Main)

    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 비활성화 되었습니다.")

    }


}