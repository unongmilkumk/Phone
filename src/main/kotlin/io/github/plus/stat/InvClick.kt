package io.github.plus.tools

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InvClick : Listener {
    @EventHandler
    fun clickGUI(e: InventoryClickEvent) {
        val p: Player = e.whoClicked as Player
        if (e.inventory.equals("Stat GUI")) {
            if (e.inventory.getItem(e.slot) == Item().createItemStack(Material.ACACIA_BOAT, "힘", "0")) {
                p.sendMessage("힘 스탯을 클릿했군요")
            }
        }
    }
}