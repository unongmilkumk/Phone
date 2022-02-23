package io.github.plus.Tools

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class Item {

    fun createItemStack(type: Material, name: String) {
        val item = ItemStack(type)
        val itemmeta = item.itemMeta
    }

}