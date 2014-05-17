package resonantinduction.em.laser

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.util.MovingObjectPosition
import net.minecraftforge.common.util.ForgeDirection

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
      val startPos = position + new Vector3(direction) + 0.5;
      val maxPos = position + (new Vector3(direction) * 100) + 0.5;
      val hit = worldObj.rayTraceBlocks(startPos.toVec3, maxPos.toVec3)

      if (hit != null)
      {
        if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
          val hitVec = new Vector3(hit.hitVec)
          val hitBlock = new Vector3(hit.blockX, hit.blockY, hit.blockZ)

          ElectromagneticCoherence.proxy.renderLaser(worldObj, startPos, hitVec + 0.5)
        }

        energy -= 10;
      }
    }
  }

  def isPowered(): Boolean = getWorldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)

  def direction(): ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

  def position(): Vector3 = new Vector3(this)
}
