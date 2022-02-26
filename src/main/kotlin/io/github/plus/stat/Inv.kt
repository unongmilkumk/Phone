package io.github.plus.stat

import io.github.plus.Main
import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import io.github.plus.tools.Config
import io.github.plus.tools.GUI

val inv: ArrayList<Inventory> = ArrayList()

class Inv(main: Main) : Listener {


    val gui = GUI(main)
    val config = Config(main)

    @EventHandler
    fun clickGUI(e: InventoryClickEvent) {
        val p: Player = e.whoClicked as Player
        if(inv.contains(e.inventory)) {

            e.isCancelled=true

            val item: ItemStack? = e.currentItem

            if(item != null) {

                if(p.exp>=70) {
                    p.exp=p.exp-70

                    val multiply = config.getconfig()!!.getInt("multiply")

                    if((item.displayName() as TextComponent).content().equals("힘", true)) {

                        config.getconfig()!!.set("players." + p.uniqueId + ".strength", config.getconfig()!!.getInt("players." + p.uniqueId + ".strength")+multiply)
                        p.sendMessage("힘 스탯을 찍었습니다")

                    }

                    if((item.displayName() as TextComponent).content().equals("방어력", true)) {

                        config.getconfig()!!.set("players." + p.uniqueId + ".defense", config.getconfig()!!.getInt("players." + p.uniqueId + ".defense")+multiply)
                        p.sendMessage("방어력 스탯을 찍었습니다")

                    }

                    if((item.displayName() as TextComponent).content().equals("체력", true)) {

                        config.getconfig()!!.set("players." + p.uniqueId + ".health", config.getconfig()!!.getInt("players." + p.uniqueId + ".health")+multiply)
                        p.sendMessage("체력 스탯을 찍었습니다")

                    }

                    if((item.displayName() as TextComponent).content().equals("운", true)) {

                        config.getconfig()!!.set("players." + p.uniqueId + ".luck", config.getconfig()!!.getInt("players." + p.uniqueId + ".luck")+multiply)
                        p.sendMessage("운 스탯을 찍었습니다")

                    }

                    p.closeInventory()

                    p.openInventory(gui.createGUI("스탯", p))

                }
                else {
                    p.sendMessage("당신은 " + (70-p.exp).toString() + "만큼의 xp가 더 필요합니다")
                }


            }

        }
    }

    @EventHandler
    fun closeInventory(e: InventoryCloseEvent) {

        if(inv.contains(e.inventory)) {
            inv.remove(e.inventory)
        }

    }

}