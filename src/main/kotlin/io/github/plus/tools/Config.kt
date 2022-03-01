package io.github.plus.tools

import io.github.plus.Main
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class Config(mainplugin: Main) {
    private val mainplugin: Main
    private var dataconfig: FileConfiguration? = null
    private var configfile: File? = null

    fun reloadconfig() {
        if (configfile == null) {
            configfile = File(mainplugin.dataFolder, "stat.yml")
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
            configfile = File(mainplugin.dataFolder, "stat.yml")
        }
        if (!configfile!!.exists()) {
            mainplugin.saveResource("stat.yml", false)
        }
    }

    fun loadConfig(player : Player) : Config{
        val config = Config(mainplugin)

        val uuid: UUID = player.uniqueId

        var exist = true

        for(key: String in config.getconfig()?.getKeys(true)!!) {
            if(key == "players.$uuid") {
                exist = false
                break
            }
        }

        if(exist) {
            config.getconfig()!!.set("players.$uuid.strength", 0)
            config.getconfig()!!.set("players.$uuid.defense", 0)
            config.getconfig()!!.set("players.$uuid.critical", 0)
            config.getconfig()!!.set("players.$uuid.health", 0)
            config.getconfig()!!.set("players.$uuid.dodging", 0)
            config.saveconfig()
        }
        return config
    }

    init {
        this.mainplugin = mainplugin
        savedefaultconfig()
    }
}
