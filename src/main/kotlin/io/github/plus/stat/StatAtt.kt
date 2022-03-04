package io.github.plus.stat

import io.github.plus.Main
import io.github.plus.runDelay
import io.github.plus.runTaskTimer
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.*
import org.bukkit.scheduler.BukkitRunnable
import java.awt.Color
import java.security.SecureRandom
import kotlin.math.roundToInt

class StatAtt(private val main: Main) {
    private val random: SecureRandom = SecureRandom()
    fun displayDamage(damage: Double, entity: LivingEntity, critical: Boolean) {

        var location = entity.eyeLocation
        location = location.add(random.nextDouble()*2.0-1.0, random.nextDouble()*2.0+99.2, random.nextDouble()*2.0-1.0)

        val armorStand: ArmorStand = entity.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand

        if(critical) armorStand.customName = "${ChatColor.of(Color(255, 252, 19))}"
        else armorStand.customName = "${ChatColor.of(Color(143, 140, 140))}"

        armorStand.isInvisible = true
        armorStand.isInvisible = true
        armorStand.customName += "${(damage*10.0).roundToInt()/10.0}"
        armorStand.isCustomNameVisible = true
        armorStand.setGravity(false)
        armorStand.isMarker = true

        class Run: BukkitRunnable() {
            var boolean = false
            override fun run() {
                if(boolean) {
                    armorStand.remove()
                    cancel()
                }
                else {
                    location.y-=100.0
                    armorStand.teleport(location)
                    boolean = true
                }
            }

        }

        Run().runTaskTimer(main, 2L, 28L)

    }
}