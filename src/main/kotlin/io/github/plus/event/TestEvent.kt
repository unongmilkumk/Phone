package io.github.plus.event

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class TestEvent : Listener {
    @EventHandler
    fun testEvent(e: PlayerBedEnterEvent){
        val p : Player = e.player
        p.sendMessage("You entered to the bed")
    }
}