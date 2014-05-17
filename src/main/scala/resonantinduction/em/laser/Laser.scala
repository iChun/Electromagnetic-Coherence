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

  def spawn(world: World, start: Vector3, direction: Vector3, energy: Double)
  {
    if (energy > 10)
    {
      val maxPos = start + (direction * maxDistance)
      val hit = world.rayTraceBlocks(start.toVec3, maxPos.toVec3)

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

            ElectromagneticCoherence.proxy.renderLaser(world, start, mirror.position + 0.5)

            /**
             * Calculate Reflection
             */
            val angle = Math.acos(direction $ mirror.normal)
            val newDirection = (direction.clone.rotate(180, mirror.normal)).normalize
            //direction.clone.rotate(angle, mirror.normal)

            spawn(world, mirror.position + 0.5 + newDirection, mirror.normal, energy / 2)
          }
          else
          {
            ElectromagneticCoherence.proxy.renderLaser(world, start, hitVec)
          }
        }
      }
    }
  }
}
