package io.github.plus.tools

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Item {
    fun createItemStack(type: Material, name: String, vararg lore: String): ItemStack {
        val item = ItemStack(type)
        val itemMeta = item.itemMeta
        itemMeta.displayName(Component.text(name))

        val list = ArrayList<Component>()

        for (a in lore) {
            list.add(Component.text(a))
        }

        itemMeta.lore(list)

        item.itemMeta = itemMeta

        return item

    }

}