package resonantinduction.em

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.util.Vec3

/**
 * @author Calclavia
 */
class Vector3
{
  var x = 0D
  var y = 0D
  var z = 0D

  def this(newX: Double, newY: Double, newZ: Double)
  {
    this()
    this.x = newX
    this.y = newY
    this.z = newZ
  }

  def this(tile: TileEntity)
  {
    this(tile.xCoord, tile.yCoord, tile.zCoord)
  }

  def this(vec: Vec3)
  {
    this(vec.xCoord, vec.yCoord, vec.zCoord)
  }

  def this(dir: ForgeDirection)
  {
    this(dir.offsetX, dir.offsetY, dir.offsetZ)
  }

  def toVec3 = Vec3.createVectorHelper(x, y, z)

  /**
   * Operations
   */
  def +=(amount: Double): Vector3 =
  {
    x += amount
    y += amount
    z += amount
    return this
  }

  def -=(amount: Double): Vector3 =
  {
    this += -amount
    return this
  }

  def /=(amount: Double): Vector3 =
  {
    x /= amount
    y /= amount
    z /= amount
    return this
  }

  def *=(amount: Double): Vector3 =
  {
    x *= amount
    y *= amount
    z *= amount
    return this
  }

  def +(amount: Double): Vector3 =
  {
    return new Vector3(x + amount, y + amount, z + amount)
  }

  def +(amount: Vector3): Vector3 =
  {
    return new Vector3(x + amount.x, y + amount.y, z + amount.z)
  }

  def -(amount: Double): Vector3 =
  {
    return (this + -amount)
  }

  def -(amount: Vector3): Vector3 =
  {
    return (this + (amount * -1))
  }

  def /(amount: Double): Vector3 =
  {
    return new Vector3(x / amount, y / amount, z / amount)
  }

  def *(amount: Double): Vector3 =
  {
    return new Vector3(x * amount, y * amount, z * amount)
  }

  def $(other: Vector3) = x * other.x + y * other.y + z * other.z

  def x(other: Vector3): Vector3 = new Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

  def magnitudeSquared = this $ this;

  def magnitude = Math.sqrt(magnitudeSquared)

  def normalized = this / magnitude

  override def clone = new Vector3(x, y, z)

  override def toString = "Vector3[" + x + "," + y + "," + z + "]"

  def distance(other: Vector3) = (other - this).magnitude
}