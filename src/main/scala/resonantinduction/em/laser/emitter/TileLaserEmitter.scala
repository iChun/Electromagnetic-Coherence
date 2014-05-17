package resonantinduction.em.laser.emitter

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.Vector3
import net.minecraftforge.common.util.ForgeDirection
import resonantinduction.em.laser.{TileBase, Laser}

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
      energy += 500
    }

    if (energy > 0)
    {
      Laser.spawn(worldObj, position + new Vector3(direction) * 0.6 + 0.5, new Vector3(direction), energy)
      energy = 0;
    }
  }
}
