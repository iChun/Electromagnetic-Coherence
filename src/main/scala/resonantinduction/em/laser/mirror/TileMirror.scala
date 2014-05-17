package resonantinduction.em.laser.mirror

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.nbt.NBTTagCompound
import resonantinduction.em.laser.{Laser, ILaserHandler}

/**
 * @author Calclavia
 */
class TileMirror extends TileEntity with ILaserHandler
{
  var normal = new Vector3(0, 1, 0)

  override def canUpdate = false

  override def onLaserHit(renderStart: Vector3, incidentDirection: Vector3, energy: Double): Boolean =
  {
    ElectromagneticCoherence.proxy.renderLaser(worldObj, renderStart, position + 0.5)

    /**
     * Calculate Reflection
     */
    val angle = Math.acos(incidentDirection $ normal)
    val axisOfReflection = incidentDirection x normal
    val rotateAngle = 180 - Math.toDegrees(2 * angle)

    if (Math.toDegrees(rotateAngle) < 180)
    {
      val newDirection = (incidentDirection.clone.rotate(rotateAngle, axisOfReflection)).normalize
      Laser.spawn(worldObj, position + 0.5 + newDirection, position + 0.5, newDirection, energy / 2)
    }

    return true
  }

  override def readFromNBT(nbt: NBTTagCompound)
  {
    super.readFromNBT(nbt)
    normal = new Vector3(nbt.getCompoundTag("normal"))
  }

  override def writeToNBT(nbt: NBTTagCompound)
  {
    super.writeToNBT(nbt)
    val normalNBT = new NBTTagCompound()
    normal.writeToNBT(normalNBT)
    nbt.setTag("normal", normalNBT)
  }

  def position(): Vector3 = new Vector3(this)
}
