package io.github.plus

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable


fun runTaskTimer(plugin : Plugin?, delay : Long, period : Long, task : () -> Unit): BukkitRunnable {

        class Runnable: BukkitRunnable() {
            override fun run() {
                task()
            }
        }


        Runnable().runTaskTimer(plugin!!, delay, period)

        return Runnable()

    }

fun runDelay(plugin : Plugin?, delay : Long, task : () -> Unit): BukkitRunnable {

        class Runnable: BukkitRunnable() {
            override fun run() {
                task()
            }

        }


        Runnable().runTaskLater(plugin!!, delay)

        return Runnable()

}

fun Player.sendActionbar(text : String) {
    val component: Component = Component.text(text)

    this.sendActionBar(component)

}
fun Player.getCustomXp() : Int{
    val level = this.level
    val progress = this.exp
    var totalXp = 0
    if(level > 0) {
        for (i in 0 until level) {
            totalXp += 50 + (2 * (i + 1 * i + 1))
        }
    }
    totalXp += ((50 + (2 *(level + 1 * level + 1))) * progress).toInt()
    return totalXp
}
fun Player.setCustomXp(Amount : Int){
    var xp = 0f
    var i = 0
    val amount = Amount.toFloat()
    while (xp < amount){
        xp += 50 + (2 * (i + 1 * i + 1))
        i += 1
    }
    i -= 1
    val finalXp = 50 + (2 * (i + 1 * i + 1))
    this.level = i
    this.exp = 1.0f - ((xp - amount)/(finalXp.toFloat()))





}