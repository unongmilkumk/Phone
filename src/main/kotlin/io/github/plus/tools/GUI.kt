package io.github.plus.tools

import io.github.plus.Main
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag

class GUI(main: Main) {

    private val main: Main

    fun createGUI(text: String, player: Player): Inventory {



        val item = Item()

        val config = Config(main).loadConfig(player)
        val uuid = player.uniqueId


        val strength: Int = config.getconfig()!!.getInt("players.$uuid.strength")
        val defense: Int = config.getconfig()!!.getInt("players.$uuid.defense")
        val critical: Int = config.getconfig()!!.getInt("players.$uuid.critical")
        val health: Int = config.getconfig()!!.getInt("players.$uuid.health")
        val dodging: Int = config.getconfig()!!.getInt("players.$uuid.dodging")
        val inv: Inventory = Bukkit.createInventory(null, 27, Component.text(text))

        for(i in 0..26) {
            inv.setItem(i, item.createItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", itemFlag = ItemFlag.HIDE_ATTRIBUTES))
        }

        inv.setItem(9, item.createItemStack(Material.IRON_SWORD, "§l§4힘", "§a힘 스탯은 기본 공격력을 상승시켜줍니다!","§e클릭하여 §l§4힘 §r§e스탯을 올리세요.","§4현재 당신의 스탯: $strength","§c실질 데미지: ${(3.0 * ((2.0 / Math.PI) * kotlin.math.atan(strength / 33.0)) + 1.0)}배", itemFlag = ItemFlag.HIDE_ATTRIBUTES))
        inv.setItem(11, item.createItemStack(Material.NETHERITE_CHESTPLATE, "§l§a방어력", "§a방어력 스탯은 데미지를 입었을때 피해를 줄여줍니다!","§e클릭하여 §l§a방어력 §r§e스탯을 올리세요.", "§a현재 당신의 스탯: $defense", "§a실질 방어율: ${((1.6 / Math.PI) * kotlin.math.atan(defense / 26.4))*100.0}% 감소", itemFlag = ItemFlag.HIDE_ATTRIBUTES))
        inv.setItem(13, item.createItemStack(Material.IRON_AXE, "§l§6크리티컬", critical.toString(), itemFlag = ItemFlag.HIDE_ATTRIBUTES))
        inv.setItem(15, item.createItemStack(Material.REDSTONE, "§l§c체력", health.toString(), itemFlag = ItemFlag.HIDE_ATTRIBUTES))
        inv.setItem(17, item.createItemStack(Material.FEATHER, "§l§b회피", dodging.toString(), itemFlag = ItemFlag.HIDE_ATTRIBUTES))

        io.github.plus.stat.inv.add(inv)

        return inv

    }

    init {
        this.main = main
    }

}