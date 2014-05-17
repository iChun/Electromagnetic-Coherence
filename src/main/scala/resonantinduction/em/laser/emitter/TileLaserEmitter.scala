package resonantinduction.em.laser.emitter

import resonantinduction.em.laser.{Laser, TileBase}
import resonantinduction.em.Vector3

/**
 * @author Calclavia
 */
class TileLaserEmitter extends TileBase
{
  var energy = 0D

  override def updateEntity()
  {
    if (isPowered())
    {
      energy += world.getStrongestIndirectPower(x, y, z) * (Laser.maxEnergy / 15)
    }

    if (energy > 0)
    {
      Laser.spawn(worldObj, position + new Vector3(direction) * 0.6 + 0.5, new Vector3(direction), energy)
      energy = 0;
    }
  }
}
