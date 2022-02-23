package io.github.plus

import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class Main : JavaPlugin(), Listener {

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")
        R().runTaskTimer(this, 1, 1)
    }
    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화 되었습니다.")
    }
}

class R: BukkitRunnable() {


    override fun run() {
//        실행할 구문
    }

}