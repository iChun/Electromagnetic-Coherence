package resonantinduction.em.laser

import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.world.World
import net.minecraft.util.MovingObjectPosition
import resonantinduction.em.laser.mirror.TileMirror

/**
 * @author Calclavia
 */
object Laser
{
  val maxDistance = 100

  val energyToMine = 10000

  def spawn(world: World, start: Vector3, direction: Vector3, energy: Double)
  {
    spawn(world, start, start, direction, energy)
  }

  def spawn(world: World, start: Vector3, renderStart: Vector3, direction: Vector3, energy: Double)
  {
    if (energy > 10)
    {
      val maxPos = start + (direction * maxDistance)
      val hit = start.rayTrace(maxPos)

      if (hit != null)
      {
        if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
          val hitVec = new Vector3(hit.hitVec)

          /**
           * Handle Mirror Reflection
           */
          val hitTile = world.getTileEntity(hit.blockX, hit.blockY, hit.blockZ)

          if (hitTile.isInstanceOf[TileMirror])
          {
            val mirror = hitTile.asInstanceOf[TileMirror]

            ElectromagneticCoherence.proxy.renderLaser(world, renderStart, mirror.position + 0.5)

            /**
             * Calculate Reflection
             */
            val angle = Math.acos(direction $ mirror.normal)
            val axisOfReflection = direction x mirror.normal
            val rotateAngle = 180 - Math.toDegrees(2 * angle)

            if (Math.toDegrees(rotateAngle) < 180)
            {
              val newDirection = (direction.clone.rotate(rotateAngle, axisOfReflection)).normalize
              spawn(world, mirror.position + 0.5 + newDirection, mirror.position + 0.5, newDirection, energy / 2)
            }
          }
          else
          {
            ElectromagneticCoherence.proxy.renderLaser(world, renderStart, hitVec)
          }
        }
      }
    }
  }
}
