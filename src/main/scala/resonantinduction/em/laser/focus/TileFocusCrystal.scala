package resonantinduction.em.laser.mirror

import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import resonantinduction.em.laser.{Laser, TileBase, ILaserHandler}
import net.minecraft.util.MovingObjectPosition

/**
 * @author Calclavia
 */
class TileFocusCrystal extends TileBase with ILaserHandler
{
  var energy = 0D
  var color = new Vector3(1, 1, 1)

  override def updateEntity()
  {
    if (energy > 0)
    {
      Laser.spawn(worldObj, position + new Vector3(direction) * 0.6 + 0.5, position + 0.5, new Vector3(direction), color, energy)
      color = new Vector3(1, 1, 1)
      energy = 0;
    }
  }

  override def onLaserHit(renderStart: Vector3, incidentDirection: Vector3, hit: MovingObjectPosition, color: Vector3, energy: Double): Boolean =
  {
    ElectromagneticCoherence.proxy.renderLaser(worldObj, renderStart, position + 0.5, color, energy)
    this.energy += energy
    this.color = (this.color + color) / 2
    return true
  }
}
