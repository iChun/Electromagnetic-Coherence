package resonantinduction.em

import net.minecraft.util.Vec3
import net.minecraft.tileentity.TileEntity

/**
 * @author Calclavia
 */
class Vector3 extends Vec3(Vec3.fakePool, 0, 0, 0)
{
  def x = xCoord

  def y = yCoord

  def z = zCoord


  def Vector3(tile: TileEntity)
  {
    super(Vec3.fakePool, tile.xCoord, tile.yCoord, tile.zCoord)
  }
}