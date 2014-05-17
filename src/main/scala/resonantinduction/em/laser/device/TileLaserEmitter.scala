package resonantinduction.em.laser.device

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.Vector3
import net.minecraftforge.common.util.ForgeDirection
import resonantinduction.em.laser.Laser

/**
 * @author Calclavia
 */
class TileLaserEmitter extends TileEntity
{
  var energy = 0D

  override def updateEntity()
  {
    if (isPowered())
    {
      energy += 100
    }

    if (energy > 0)
    {
      Laser.spawn(worldObj, position + new Vector3(direction) * 0.6 + 0.5, new Vector3(direction), energy)
      energy = 0;
    }
  }

  def isPowered(): Boolean = getWorldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)

  def direction(): ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

  def position(): Vector3 = new Vector3(this)
}
