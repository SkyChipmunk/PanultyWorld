package org.cubit.panultyworld.scheduler

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.cubit.panultyworld.util.PanultyWorldUtil
import org.cubit.panultyworld.util.PanultyWorldUtil.erectionPanultyWorld
import org.cubit.panultyworld.util.PanultyWorldUtil.isEntityPanultyWorldField
import org.cubit.panultyworld.util.PanultyWorldUtil.isPanultyWorldField
import org.cubit.panultyworld.util.PanultyWorldUtil.isPanultyWorldRecoveryTime
import org.cubit.panultyworld.util.PanultyWorldUtil.isPanultyWorldTime
import java.util.*

class PlayerPanutlyWorldScheduler : Runnable {

    private val world = PanultyWorldUtil.getPanultyWorld()
    private val random = Random()

    override fun run() {

        Bukkit.getOnlinePlayers().filter { it.world == world }.forEach {
            if (it.isPanultyWorldTime() && it.isEntityPanultyWorldField()) {
                it.damage(1.0)

            } else if (it.isPanultyWorldRecoveryTime() && !it.isEntityPanultyWorldField()){
                it.health = (it.health + 0.5).coerceAtMost(it.maxHealth)
                it.sendMessage("§a3초가 지나 ${it.name} 님의 HP가 0.5 회복되었습니다. 현재체력: ${it.health}")
            }
            it.erectionField()
        }
    }

    private fun Location.erectionFieldLower() {
        for (x in -1..1) {
            for (z in -1..1) {
                clone().add(x.toDouble(), 0.0, z.toDouble()).block.type = Material.IRON_BLOCK
            }
        }
    }

    fun Player.erectionField() {
        if (isPanultyWorldField()) {
            val loc = location.add((random.nextInt(100 + 100) - 100).toDouble(), 0.0, (random.nextInt(30 + 30) - 30).toDouble())
            loc.clone().add(0.0 , 1.0 , 0.0).block.type = Material.BEACON
            loc.erectionFieldLower()
            loc.erectionPanultyWorld()
            Bukkit.broadcastMessage("§a안전 필드가 생성 되었습니다. 좌표: ${loc.x} , ${loc.y} , ${loc.z}")
        }
    }
}
