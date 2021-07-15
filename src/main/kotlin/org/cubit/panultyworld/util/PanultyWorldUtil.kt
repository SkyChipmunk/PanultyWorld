package org.cubit.panultyworld.util

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.cubit.panultyworld.PanultyWorld
import org.cubit.panultyworld.constants.*

object PanultyWorldUtil {

    private val list = mutableListOf<Location>()

    fun Location.erectionPanultyWorld() {
        list.add(this)
    }

    fun Entity.isEntityPanultyWorldField(): Boolean {
        list.forEach {
            val min = it.clone().add(-10.0, 0.0, -10.0)
            val max = it.clone().add(10.0, 0.0, 10.0)

            if (min.x < location.x && min.z < location.z && max.x > location.x && max.z > location.z) {
                return false
            }
        }
        return true
    }

    fun Player.initPanultyWorld() {
        updatePanultyWorldField()
        updatePanultyWorld()
        updatePanultyRecoveryTime()
    }

    fun Player.updatePanultyRecoveryTime() {
        setMetadata(PANULTY_WORLD_RECOVERY_TIME, FixedMetadataValue(PanultyWorld.inst, System.currentTimeMillis()))
    }

    fun Player.getPanultyRecoveryTime(): Long {
        return getMetadata(PANULTY_WORLD_RECOVERY_TIME)[0].asLong()
    }

    fun Player.setWorldLocation() {
        setMetadata(WORLD_LOC, FixedMetadataValue(PanultyWorld.inst, location))
    }

    fun Player.getWorldLocation(): Location {
        return getMetadata(WORLD_LOC)[0].value() as Location
    }

    fun getPanultyWorld(): World {
        return Bukkit.getWorld(PANULTY_WORLD) ?: WorldCreator(PANULTY_WORLD).createWorld()!!
    }

    fun Player.updatePanultyWorld() {
        setMetadata(PANULTY_WORLD_PLAYER_TIME, FixedMetadataValue(PanultyWorld.inst, System.currentTimeMillis()))
    }

    fun Player.updatePanultyWorldField() {
        setMetadata(PANULTY_WORLD_FIELD, FixedMetadataValue(PanultyWorld.inst, System.currentTimeMillis()))
    }

    fun Player.getPanultyWorldField(): Long {
        return getMetadata(PANULTY_WORLD_FIELD)[0].asLong()
    }

    fun Player.getPanultyWorldTime(): Long {
        return getMetadata(PANULTY_WORLD_PLAYER_TIME)[0].asLong()
    }

    fun Player.isPanultyWorldField(): Boolean {
        val diff = System.currentTimeMillis() - getPanultyWorldField()
        if (diff > 45000) {
            updatePanultyWorldField()
            return true
        }
        return false
    }

    fun Player.isPanultyWorldTime(): Boolean {
        val diff = System.currentTimeMillis() - getPanultyWorldTime()
        if (diff > 2000) {
            updatePanultyWorld()
            return true
        }
        return false
    }

    fun Player.isPanultyWorldRecoveryTime(): Boolean {
        val diff = System.currentTimeMillis() - getPanultyRecoveryTime()
        if (diff > 3000) {
            updatePanultyRecoveryTime()
            return true
        }
        return false
    }

}