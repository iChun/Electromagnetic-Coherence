package resonantinduction.em.laser.mirror

import resonantinduction.em.{ElectromagneticCoherence, Vector3}
import net.minecraft.nbt.NBTTagCompound
import resonantinduction.em.laser.{TileBase, Laser, ILaserHandler}
import net.minecraft.util.MovingObjectPosition
import net.minecraft.network.{NetworkManager, Packet}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity

/**
 * @author Calclavia
 */
class TileMirror extends TileBase with ILaserHandler
{
  var normal = new Vector3(0, 1, 0)

  override def canUpdate = false

  override def onLaserHit(renderStart: Vector3, incidentDirection: Vector3, hit: MovingObjectPosition, color: Vector3, energy: Double): Boolean =
  {
    ElectromagneticCoherence.proxy.renderLaser(worldObj, renderStart, position + 0.5, color, energy)

    /**
     * Calculate Reflection
     */
    val angle = Math.acos(incidentDirection $ normal)
    val axisOfReflection = incidentDirection x normal
    val rotateAngle = 180 - Math.toDegrees(2 * angle)

    if (Math.toDegrees(rotateAngle) < 180)
    {
      val newDirection = (incidentDirection.clone.rotate(rotateAngle, axisOfReflection)).normalize
      Laser.spawn(worldObj, position + 0.5 + newDirection, position + 0.5, newDirection, color, energy / 1.2)
    }

    return true
  }

  override def getDescriptionPacket: Packet =
  {
    val nbt = new NBTTagCompound()
    writeToNBT(nbt)
    return new S35PacketUpdateTileEntity(x, y, z, 0, nbt)
  }

  override def onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity)
  {
    val receive = pkt.func_148857_g
    readFromNBT(receive)
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
}
