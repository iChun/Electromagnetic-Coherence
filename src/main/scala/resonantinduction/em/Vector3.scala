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

  def normalize = this / magnitude

  override def clone = new Vector3(x, y, z)

  override def toString = "Vector3[" + x + "," + y + "," + z + "]"

  def distance(other: Vector3) = (other - this).magnitude

  def rotate(angle: Double, axis: Vector3): Vector3 =
  {
    return translateMatrix(getRotationMatrix(angle, axis), this)
  }

  def getRotationMatrix(angle: Double, axis: Vector3): Seq[Double] = axis.getRotationMatrix(angle)

  def getRotationMatrix(newAngle: Double): Seq[Double] =
  {
    var angle = newAngle
    val matrix = new Array[Double](16)
    val axis = this.clone().normalize
    val x = axis.x
    val y = axis.y
    val z = axis.z
    angle *= 0.0174532925D
    val cos = Math.cos(angle)
    val ocos = 1.0F - cos
    val sin = Math.sin(angle)
    matrix(0) = (x * x * ocos + cos)
    matrix(1) = (y * x * ocos + z * sin)
    matrix(2) = (x * z * ocos - y * sin)
    matrix(4) = (x * y * ocos - z * sin)
    matrix(5) = (y * y * ocos + cos)
    matrix(6) = (y * z * ocos + x * sin)
    matrix(8) = (x * z * ocos + y * sin)
    matrix(9) = (y * z * ocos - x * sin)
    matrix(10) = (z * z * ocos + cos)
    matrix(15) = 1.0F
    return matrix
  }

  def translateMatrix(matrix: Seq[Double], translation: Vector3): Vector3 =
  {
    val x = translation.x * matrix(0) + translation.y * matrix(1) + translation.z * matrix(2) + matrix(3)
    val y = translation.x * matrix(4) + translation.y * matrix(5) + translation.z * matrix(6) + matrix(7)
    val z = translation.x * matrix(8) + translation.y * matrix(9) + translation.z * matrix(10) + matrix(11)
    translation.x = x
    translation.y = y
    translation.z = z
    return translation
  }

  def eulerAngles = new Vector3(Math.toDegrees(Math.atan2(x, z)), Math.toDegrees(-Math.atan2(y, Math.hypot(z, x))), 0)
}