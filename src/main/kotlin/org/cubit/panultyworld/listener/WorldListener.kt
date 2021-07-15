package org.cubit.panultyworld.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.cubit.panultyworld.util.PanultyWorldUtil
import org.cubit.panultyworld.util.PanultyWorldUtil.initPanultyWorld
import org.cubit.panultyworld.util.PanultyWorldUtil.setWorldLocation

class WorldListener : Listener{

    private val world = PanultyWorldUtil.getPanultyWorld()

    @EventHandler
    fun PlayerBedEnterEvent.onPlayerBedEnterEvent() {
        player.setWorldLocation()
        player.initPanultyWorld()
        player.teleport(world.spawnLocation)
        isCancelled = true
    }

   @EventHandler
   fun PlayerRespawnEvent.onPlayerRespawnEvent() {
        player.setWorldLocation()
        player.initPanultyWorld()
        respawnLocation = world.spawnLocation

    }

    @EventHandler
    fun PlayerJoinEvent.onPlayerJoinEvent() {
        if (player.world == world) {
            player.initPanultyWorld()
        }
    }



}