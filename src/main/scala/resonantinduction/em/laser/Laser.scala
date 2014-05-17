package resonantinduction.em.laser

import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.world.World
import net.minecraft.util.MovingObjectPosition
import scala.collection.mutable
import net.minecraft.block.Block

/**
 * @author Calclavia
 */
object Laser
{
  val maxDistance = 100

  val minEnergyToMine = 800D
  val maxEnergyToMine = 100000D

  var lastUpdateTime = 0L
  val currentBlockEnergy = new mutable.HashMap[Vector3, Double]()
  val accumilatedBlockEnergy = new mutable.HashMap[Vector3, Double]()

  def spawn(world: World, start: Vector3, direction: Vector3, energy: Double)
  {
    spawn(world, start, start, direction, energy)
  }

  def spawn(world: World, start: Vector3, renderStart: Vector3, direction: Vector3, energy: Double)
  {
    if (energy > 10)
    {
      val maxPos = start + (direction * maxDistance)
      val hit = start.rayTrace(world, maxPos)

      if (hit != null)
      {
        if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
          val hitVec = new Vector3(hit.hitVec)
          val hitBlockPos = new Vector3(hit.blockX, hit.blockY, hit.blockZ)
          /**
           * Handle Mirror Reflection
           */
          val hitTile = world.getTileEntity(hit.blockX, hit.blockY, hit.blockZ)

          if (hitTile.isInstanceOf[ILaserHandler])
          {
            hitTile.asInstanceOf[ILaserHandler].onLaserHit(renderStart, direction, energy)
          }
          else
          {
            /**
             * Attempt to burn block
             */
            if (lastUpdateTime != world.getWorldTime)
            {
              currentBlockEnergy.clear
              lastUpdateTime = world.getWorldTime
            }

            val energyOnBlock = (if (currentBlockEnergy.contains(hitBlockPos)) currentBlockEnergy(hitBlockPos) else 0) + energy
            currentBlockEnergy.put(hitBlockPos, energyOnBlock)

            if (energyOnBlock > minEnergyToMine)
            {
              /**
               * The laser can mine the block!
               */
              val accumulatedEnergy = (if (accumilatedBlockEnergy.contains(hitBlockPos)) accumilatedBlockEnergy(hitBlockPos) else 0) + energy
              accumilatedBlockEnergy.put(hitBlockPos, accumulatedEnergy)

              val block = world.getBlock(hitBlockPos.x.toInt, hitBlockPos.y.toInt, hitBlockPos.z.toInt)
              world.destroyBlockInWorldPartially(Block.blockRegistry.getIDForObject(block), hitBlockPos.x.toInt, hitBlockPos.y.toInt, hitBlockPos.z.toInt, (accumulatedEnergy / maxEnergyToMine * 10).toInt)

              if (accumulatedEnergy > maxEnergyToMine)
              {
                block.dropBlockAsItem(world, hitBlockPos.x.toInt, hitBlockPos.y.toInt, hitBlockPos.z.toInt, world.getBlockMetadata(hitBlockPos.x.toInt, hitBlockPos.y.toInt, hitBlockPos.z.toInt), 0)
                world.setBlockToAir(hitBlockPos.x.toInt, hitBlockPos.y.toInt, hitBlockPos.z.toInt)
                accumilatedBlockEnergy.remove(hitBlockPos)
              }
            }
            else
            {
              //accumilatedBlockEnergy.remove(hitBlockPos)
            }

            ElectromagneticCoherence.proxy.renderLaser(world, renderStart, hitVec)
          }
        }
      }
    }
  }
}
