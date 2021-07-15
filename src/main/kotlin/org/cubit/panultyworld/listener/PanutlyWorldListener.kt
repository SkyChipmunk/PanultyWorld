package org.cubit.panultyworld.listener

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.cubit.panultyworld.util.PanultyWorldUtil
import org.cubit.panultyworld.util.PanultyWorldUtil.updatePanultyWorld

class PanutlyWorldListener : Listener {

    private val world = PanultyWorldUtil.getPanultyWorld()

    @EventHandler
    fun EntityExplodeEvent.onEntityExplodeEvent() {
        if (location.world == world) {
            isCancelled = true
        }
    }

    @EventHandler
    fun PlayerDeathEvent.onPlayerDeathEvent() {
        if (entity.world == world) {
            world.time = 18000
        }
    }


    @EventHandler
    fun PlayerMoveEvent.onPlayerMoveEvent() {
        if (player.world == world) {
            world.entities.forEach {
                val min = player.location.clone().add(-45.0, 0.0, -45.0)
                val max = player.location.clone().add(45.0, 0.0, 45.0)
                if (it is Zombie) {
                    if (min.x > it.location.x || min.z > it.location.z || max.x < it.location.x || max.z < it.location.z) {
                        it.remove()
                    }
                }
            }
        }
    }
}