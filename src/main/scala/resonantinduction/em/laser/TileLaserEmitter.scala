package resonantinduction.em.laser

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import resonantinduction.em.Vector3

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
      energy += 10
    }

    if (energy > 0)
    {
      worldObj.rayTraceBlocks(new Vector3(this))
    }
  }

  def isPowered(): Boolean = getWorldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)

  def direction(): ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

}
