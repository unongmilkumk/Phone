package io.github.plus.stat

import io.github.plus.Main
import org.bukkit.entity.*
import org.bukkit.scheduler.BukkitRunnable
import java.security.SecureRandom

class StatAtt(private val main: Main) {
    private val random: SecureRandom = SecureRandom()
    fun displayDamage(damage: Double, entity: LivingEntity) {
        var location = entity.eyeLocation
        location = location.add(random.nextDouble()*2.0-1.0, random.nextDouble()*2.0-1.0, random.nextDouble()*2.0-1.0)

        val armorStand: ArmorStand = entity.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand

        armorStand.isInvisible = true
        armorStand.isMarker = true
        armorStand.setGravity(false)

        armorStand.customName = "$damage"

        class Run: BukkitRunnable() {
            override fun run() {
                armorStand.remove()
            }

        }

        Run().runTaskLater(main, 30L)

    }
}