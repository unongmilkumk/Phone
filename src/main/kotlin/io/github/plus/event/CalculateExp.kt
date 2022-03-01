package io.github.plus.event

import io.github.plus.Main
import io.github.plus.getCustomXp
import io.github.plus.sendActionbar
import io.github.plus.setCustomXp
import io.github.plus.tools.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerLevelChangeEvent
import kotlin.math.floor

class CalculateExp(main : Main) : Listener {
    private val main: Main
    @EventHandler
    fun onGetExp(e : PlayerExpChangeEvent){
        val p = e.player
        val amount = e.amount
        val totalXp = p.getCustomXp()
        e.amount = 0
        p.setCustomXp(totalXp + amount)
        p.sendActionbar("§f[§e알림§f] $amount §cEXP")
    }
    @EventHandler
    fun onLevelUp(e : PlayerLevelChangeEvent){
        if (e.oldLevel < e.newLevel) {
            e.player.sendActionbar("§f[§e알림§f] §c레벨 업§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")

        }
        if (e.oldLevel > e.newLevel) {
            e.player.sendActionbar("§f[§e알림§f] §a레벨 다운§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
        }
    }
    init {
        this.main = main
    }
}