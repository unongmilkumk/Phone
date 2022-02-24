package io.github.plus.Tools

import io.github.plus.io.github.plus.Main
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class config(mainplugin: Main) {
    private val mainplugin: Main
    private var dataconfig: FileConfiguration? = null
    private var configfile: File? = null
    fun reloadconfig() {
        if (configfile == null) {
            configfile = File(Bukkit.getPluginsFolder(), "stat/stat.yml")
        }
        dataconfig = YamlConfiguration.loadConfiguration(configfile!!)
        val defaultstream: InputStream? = mainplugin.getResource("stat.yml")
        if (defaultstream != null) {
            dataconfig!!.setDefaults(YamlConfiguration.loadConfiguration(InputStreamReader(defaultstream)))
        }
    }

    fun getconfig(): FileConfiguration? {
        if (dataconfig == null) {
            reloadconfig()
        }
        return dataconfig
    }

    fun saveconfig() {
        if (dataconfig == null || configfile == null) {
            return
        }
        try {
            getconfig()!!.save(configfile!!)
        } catch (e: IOException) {

        }
    }

    fun savedefaultconfig() {
        if (configfile == null) {
            configfile = File(Bukkit.getPluginsFolder(), "stat/stat.yml")
        }
        if (!configfile!!.exists()) {
            mainplugin.saveResource("stat.yml", false)
        }
    }

    init {
        this.mainplugin = mainplugin
        savedefaultconfig()
    }
}
