package resonantinduction.em.laser.emitter

import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import resonantinduction.em.laser.{TileBase, ILaserHandler}
import net.minecraft.util.MovingObjectPosition

/**
 * @author Calclavia
 */
class TileLaserReceiver extends TileBase with ILaserHandler
{
  var prevEnergy = 0D
  var energy = 0D

  override def updateEntity()
  {
    if (prevEnergy != energy)
    {
      world.notifyBlocksOfNeighborChange(x, y, z, getBlockType)
      prevEnergy = energy
    }

    if (energy > 0)
    {
      energy = 0
    }
  }

  def onLaserHit(renderStart: Vector3, incident: Vector3, hit: MovingObjectPosition, color: Vector3, energy: Double): Boolean =
  {
    if (hit.sideHit == direction.ordinal)
    {
      ElectromagneticCoherence.proxy.renderLaser(world, renderStart, position + 0.5 + new Vector3(direction) * 0.3, color, energy)
      //TODO: Change this
      this.energy = energy
      return true
    }

    return false
  }


}
