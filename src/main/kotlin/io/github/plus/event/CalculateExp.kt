package io.github.plus.io.github.plus.event

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerExpChangeEvent
import org.bukkit.event.player.PlayerLevelChangeEvent
import kotlin.math.floor

class CalculateExp : Listener {

    @EventHandler
    fun onGetExp(e : PlayerExpChangeEvent){
        val p = e.player
        var amount = e.amount
        e.amount = 0
        var lv = e.player.level
        val needxp = 50 + (2 *(lv + 1 * lv + 1))
        val rate = amount.toDouble() / needxp.toDouble()
        p.sendActionBar("§f[§e알림§f] $amount §cEXP")
        if(p.exp + rate.toFloat() < 1.0f) { p.exp += rate.toFloat() }
        else{
            var addedlv = floor(p.exp + rate.toFloat()).toInt()
            p.giveExpLevels(addedlv)
            p.exp = (p.exp + rate.toFloat()) - addedlv.toFloat()
        }
    }
    @EventHandler
    fun onLevelUp(e : PlayerLevelChangeEvent){
        e.player.sendActionBar("§f[§e알림§f] §c레벨 업§f! (§b${e.oldLevel} §f-> §c${e.newLevel}§f)")
    }
}