package io.github.plus.Tools

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Item {
    fun createItemStack(type: Material, name: String, vararg lore: String): ItemStack {
        val item = ItemStack(type)
        val itemmeta = item.itemMeta
        itemmeta.displayName(Component.text(name))

        val list = ArrayList<Component>()

        for (a in lore) {
            list.add(Component.text(a))
        }

        itemmeta.lore(list)

        item.itemMeta=itemmeta

        return item


    }

}