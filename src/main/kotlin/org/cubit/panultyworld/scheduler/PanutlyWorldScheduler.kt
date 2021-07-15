package org.cubit.panultyworld.scheduler

import org.bukkit.World
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.cubit.panultyworld.util.PanultyWorldUtil
import org.cubit.panultyworld.util.PanultyWorldUtil.getWorldLocation
import org.cubit.panultyworld.util.PanultyWorldUtil.isEntityPanultyWorldField
import java.util.*

class PanutlyWorldScheduler : Runnable {

    private val world = PanultyWorldUtil.getPanultyWorld()
    private val random = Random()

    override fun run() {
        world.entities.forEach {
            if (it is Player && world.time == 0L) {
                it.teleport(it.getWorldLocation())
            }
            if (it is LivingEntity && it !is Player) {
                if (!it.isEntityPanultyWorldField()) {
                    it.velocity = it.location.direction.multiply(-3)
                }
            }
            if (world.entities.filter { entity ->  entity is Zombie }.count() <= 50) {
                world.spawnEntity(it.location.add((random.nextInt(30 + 30) - 30).toDouble(), 0.0, (random.nextInt(30 + 30) - 30).toDouble()), EntityType.ZOMBIE)
            }


        }
    }
}