package io.github.jesecci

import com.comphenix.protocol.events.PacketContainer
import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.md_5.bungee.api.ChatMessageType
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity
import net.minecraft.server.level.EntityPlayer
import net.minecraft.world.entity.item.EntityTNTPrimed
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R1.CraftServer
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftZombie
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.meta.Damageable
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scoreboard.Team
import kotlin.math.min

class Test : Listener {
    @EventHandler
    fun onFall(event : EntityDamageEvent){
        if (event.cause != EntityDamageEvent.DamageCause.FALL) return
        val entity = event.entity; if (entity !is LivingEntity) return
        val boots = entity.equipment?.boots

        var bootsDamage = event.damage * 6.0

        if (boots != null) {
            val bootsMeta = boots.itemMeta

            if (bootsMeta is Damageable) {
                val maxDurability = boots.type.maxDurability
                val currentDurability = bootsMeta.damage
                val remainDurability = maxDurability - currentDurability
                val reduce = min(remainDurability, bootsDamage.toInt())

                bootsDamage -= reduce
                bootsMeta.damage += reduce
                boots.itemMeta = bootsMeta

                if (bootsMeta.damage >= maxDurability) {
                    boots.amount = 0
                }
            }
        }
        if (bootsDamage > 0) {
            entity.addPotionEffect(
                PotionEffect(
                    PotionEffectType.SLOW,
                    (bootsDamage / 6).toInt() * 20,
                    4, true, true, true
                )
            )
        }

        event.damage = 0.0
    }
    @EventHandler
    fun onDamage(event : EntityDamageByEntityEvent){
        if(event.damager !is Player) return;
        event.damage *= 2.0

    }
    @EventHandler
    fun onPoision(event : EntityDamageEvent){
        if(event.cause != EntityDamageEvent.DamageCause.POISON && event.cause != EntityDamageEvent.DamageCause.FREEZE && event.cause != EntityDamageEvent.DamageCause.WITHER) return;
        event.isCancelled = true
    }
    @EventHandler
    fun Join(event : PlayerJoinEvent){
        event.player.addPotionEffect(PotionEffect(PotionEffectType. WITHER, 30000000, 0, true, false, false))
        event.player.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 30000000, 0, true, false, false))


        event.player.sendActionBar("ㅎㅇ")
        val plugin = Bukkit.getServer().pluginManager.getPlugin("Colorful")
        val ID = runTaskTimer(plugin, 0, 2){
            //event.player.walkSpeed = 0.36f
            //event.player.freezeTicks = 200
            event.player.walkSpeed = 0.3f
            //event.player.addPotionEffect(PotionEffect(PotionEffectType.POISON, 2, 0, true, false, false))
            var color = NamedTextColor.NAMES.values().toList().random()
            val team = io.github.jesecci.team(color)
            team.addEntry(event.player.name)
        }




    }
    @EventHandler
    fun onListPing(event : PaperServerListPingEvent){
        event.motd = "짜리밍~"
    }


}
private fun team(color: NamedTextColor): Team {
    val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
    val teamName = "rainbow-${color.asHexString()}"
    return scoreboard.getTeam(teamName) ?: scoreboard.registerNewTeam(teamName).apply {
        color(color)
        setCanSeeFriendlyInvisibles(true)
        setAllowFriendlyFire(false)
    }
}
fun runTaskTimer(plugin : Plugin?, delay : Long, period : Long, task : () -> Unit) : Int?{
    val tasks = plugin?.let{Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(it, task, delay, period)}
    return tasks

}
fun runDelay(plugin : Plugin?, delay : Long, task : () -> Unit){
    plugin?.let{Bukkit.getServer().scheduler.scheduleSyncDelayedTask(it, task, delay)}

}
fun Player.sendActionBar(text : String) = this.spigot().sendMessage(ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent(text))

