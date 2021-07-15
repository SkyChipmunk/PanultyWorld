package org.cubit.panultyworld

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.cubit.panultyworld.listener.PanutlyWorldListener
import org.cubit.panultyworld.listener.WorldListener
import org.cubit.panultyworld.scheduler.PanutlyWorldScheduler
import org.cubit.panultyworld.scheduler.PlayerPanutlyWorldScheduler

class PanultyWorld : JavaPlugin() {

    companion object {
        lateinit var inst: JavaPlugin
    }

    override fun onEnable() {
        inst = this
        Bukkit.getPluginManager().registerEvents(WorldListener() , this)
        Bukkit.getPluginManager().registerEvents(PanutlyWorldListener() , this)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this , PanutlyWorldScheduler() , 1L , 1L)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this , PlayerPanutlyWorldScheduler() , 1L , 20L)
    }

}