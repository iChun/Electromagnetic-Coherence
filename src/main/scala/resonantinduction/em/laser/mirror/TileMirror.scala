package resonantinduction.em.laser.mirror

import net.minecraft.tileentity.TileEntity
import resonantinduction.em.Vector3

/**
 * @author Calclavia
 */
class TileMirror extends TileEntity
{
  var normal = new Vector3(0, 1, 0)
  var energy = 0D

  override def updateEntity()
  {

  }
}
