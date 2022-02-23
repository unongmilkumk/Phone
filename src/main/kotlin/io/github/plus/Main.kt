package io.github.plus

import aa
import io.github.plus.command.Test
import io.github.plus.event.TestEvent
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")
        server.pluginManager.registerEvents(aa(), this@Main)
        //커맨드 setExecutor + setTabCompleter
        server.getPluginCommand("test")?.setExecutor(Test())

        //이벤트
        server.pluginManager.registerEvents(TestEvent(), this)

    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 비활성화 되었습니다.")
    }
}