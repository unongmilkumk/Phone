package io.github.plus.stat

import io.github.plus.Main
import org.bukkit.entity.*
import org.bukkit.scheduler.BukkitRunnable
import java.security.SecureRandom
import kotlin.math.roundToInt

class StatAtt(private val main: Main) {
    private val random: SecureRandom = SecureRandom()
    fun displayDamage(damage: Double, entity: LivingEntity) {

        var location = entity.eyeLocation
        location = location.add(random.nextDouble()*2.0-1.0, random.nextDouble()*2.0+9.5, random.nextDouble()*2.0-1.0)

        val armorStand: ArmorStand = entity.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand

//        armorStand.isInvisible = true
        armorStand.isInvisible = true
        armorStand.customName = "${(damage*10.0).roundToInt()/10.0}"
        armorStand.isCustomNameVisible = true
        armorStand.setGravity(false)
        armorStand.isMarker = true

        location.y=location.y-10.0
        println(location.y)
        armorStand.teleport(location)

        class Run: BukkitRunnable() {
            override fun run() {
                armorStand.remove()
            }

        }

        Run().runTaskLater(main, 30L)

    }
}