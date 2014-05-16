package resonantinduction.em.laser

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.util.MovingObjectPosition

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
      val hit = worldObj.rayTraceBlocks(position.toVec3, ((new Vector3(direction) * 100).asInstanceOf[Vector3]).toVec3)

      if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
      {
        val hitVec = new Vector3(hit.hitVec)
        val hitBlock = new Vector3(hit.blockX, hit.blockY, hit.blockZ)

        ElectromagneticCoherence.proxy.renderLaser(worldObj, position, hitVec)
      }
    }
  }

  def isPowered(): Boolean = getWorldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)

  def direction(): ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

  def position(): Vector3 = new Vector3(this)
}
