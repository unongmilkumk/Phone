package io.github.plus.io.github.plus.stat

import org.bukkit.entity.Player

class StatAtt {
    fun Player.setStatHealth(p: Player, level: Double) {
        p.maxHealth = 20.0 + level * 2
    }
}