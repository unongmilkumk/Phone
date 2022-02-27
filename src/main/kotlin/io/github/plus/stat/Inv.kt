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
import org.bukkit.entity.Item
import java.lang.Math.floor

val inv: ArrayList<Inventory> = ArrayList()

class Inv(main: Main) : Listener {


    private val gui = GUI(main)
    private val main : Main


    @EventHandler
    fun onclickGUI(e: InventoryClickEvent) {
        val p: Player = e.whoClicked as Player
        val config = Config(main).loadConfig(p)
        if(e.view.title == "${p.name}님의 스탯") {

            e.isCancelled = true

            val item: ItemStack? = e.currentItem

            if(item != null) {
                var xp = config.getconfig()!!.getInt("players.${p.uniqueId}.exp")
                val strength : Int = config.getconfig()!!.getInt("players.${p.uniqueId}.strength")
                val defense : Int = config.getconfig()!!.getInt("players.${p.uniqueId}.defense")
                val critical : Int = config.getconfig()!!.getInt("players.${p.uniqueId}.critical")
                val dodging : Int = config.getconfig()!!.getInt("players.${p.uniqueId}.dodging")
                val health : Int = config.getconfig()!!.getInt("players.${p.uniqueId}.health")

                if(xp >= 70) {
                    xp -= 70
                    val needxp = 50 + (2 *(p.level + 1 * p.level + 1))
                    if(p.exp - (70.0 / needxp.toDouble()).toFloat() > 0f){
                        p.exp -= (70.0 / needxp.toDouble()).toFloat()
                    }
                    else{
                        p.giveExpLevels(-1)
                        p.exp = p.exp - (70.0 / needxp.toDouble()).toFloat() + 1.0f
                    }

                    config.getconfig()!!.set("players.${p.uniqueId}.exp", xp)
                    config.saveconfig()
                    //val multiply = config.getconfig()!!.getInt("multiply")

                    if(item.itemMeta.displayName == "힘") {

                        config.getconfig()!!.set("players.${p.uniqueId}.strength", strength + 1)
                        p.sendMessage("힘 스탯을 찍었습니다")
                        config.saveconfig()

                    }

                    if(item.itemMeta.displayName == "방어력") {

                        config.getconfig()!!.set("players.${p.uniqueId}.defense", defense + 1)
                        p.sendMessage("방어력 스탯을 찍었습니다")
                        config.saveconfig()

                    }

                    if(item.itemMeta.displayName == "체력") {

                        config.getconfig()!!.set("players.${p.uniqueId}.health", health + 1)
                        p.sendMessage("체력 스탯을 찍었습니다")
                        config.saveconfig()
                        p.maxHealth = 20 + (0.5 * health)

                    }

                    if(item.itemMeta.displayName == "크리티컬") {

                        config.getconfig()!!.set("players.${p.uniqueId}.critical", critical + 1)
                        p.sendMessage("크리티컬 스탯을 찍었습니다")
                        config.saveconfig()

                    }

                    if(item.itemMeta.displayName == "회피") {

                        config.getconfig()!!.set("players.${p.uniqueId}.dodging", dodging + 1)
                        p.sendMessage("회피 스탯을 찍었습니다")
                        config.saveconfig()

                    }

                    //p.closeInventory()

                    //p.openInventory(inven)
                    p.openInventory(gui.createGUI("${p.name}님의 스탯", p))

                }
                else {
                    p.sendMessage("당신은 " + (70 - xp).toString() + "만큼의 xp가 더 필요합니다")
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
    init{
        this.main = main
    }

}