package io.github.plus

import event.aa
import io.github.plus.command.gui
import io.github.plus.event.TestEvent
import io.github.plus.io.github.plus.command.CommandSample
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")

        //커맨드 setExecutor
        server.getPluginCommand("stat")?.setExecutor(gui())
        server.getPluginCommand("sample")?.setExecutor(CommandSample())

        //커맨드 setTabCompleter
        server.getPluginCommand("sample")?.setTabCompleter(CommandSample())

        //이벤트
        server.pluginManager.registerEvents(TestEvent(), this)
        server.pluginManager.registerEvents(aa(), this)
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 비활성화 되었습니다.")
    }
}