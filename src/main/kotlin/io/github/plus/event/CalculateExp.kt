package io.github.plus.event

import io.github.plus.Main
import io.github.plus.sendActionbar
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
        val config = Config(main).loadConfig(p)
        var totalXp = config.getconfig()!!.getInt("players.${p.uniqueId}.exp")
        val amount = e.amount
        totalXp += amount
        config.getconfig()!!.set("players.${p.uniqueId}.exp", totalXp)
        config.saveconfig()
        e.amount = 0
        val lv = e.player.level
        val needxp = 50 + (2 *(lv + 1 * lv + 1))
        val rate = amount.toDouble() / needxp.toDouble()
        p.sendActionbar("§f[§e알림§f] $amount §cEXP")
        if(p.exp + rate.toFloat() < 1.0f) { p.exp += rate.toFloat() }
        else{
            val addedlv = floor(p.exp + rate.toFloat()).toInt()
            p.giveExpLevels(addedlv)
            p.exp = (p.exp + rate.toFloat()) - addedlv.toFloat()
        }
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