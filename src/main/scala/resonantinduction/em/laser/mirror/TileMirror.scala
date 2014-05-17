package resonantinduction.em.laser.mirror

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.Vector3
import net.minecraft.nbt.NBTTagCompound

/**
 * @author Calclavia
 */
class TileMirror extends TileEntity
{
  var normal = new Vector3(0, 1, 0)
  var energy = 0D

  override def canUpdate = false

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
