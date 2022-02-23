package io.github.plus

import io.github.plus.command.gui
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")

        //커맨드 setExecutor + setTabCompleter
        server.getPluginCommand("test")?.setExecutor(gui())

//ㅇㅇㅇ
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 비활성화 되었습니다.")
    }
}