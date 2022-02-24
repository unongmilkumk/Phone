package io.github.plus

import event.aa
import io.github.plus.command.Gui
import io.github.plus.event.TestEvent
import io.github.plus.command.CommandSample
import io.github.plus.event.CalculateExp
import io.github.plus.tools.Config
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    val config = Config(this)

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")

        //커맨드 setExecutor
        server.getPluginCommand("stat")?.setExecutor(Gui(this@Main))
        server.getPluginCommand("sample")?.setExecutor(CommandSample())

        //커맨드 setTabCompleter
        server.getPluginCommand("sample")?.setTabCompleter(CommandSample())

        //이벤트
        server.pluginManager.registerEvents(TestEvent(), this@Main)
        server.pluginManager.registerEvents(aa(), this@Main)
        server.pluginManager.registerEvents(CalculateExp(), this@Main)

        config.reloadconfig()

    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 비활성화 되었습니다.")
        config.saveconfig()

    }


}