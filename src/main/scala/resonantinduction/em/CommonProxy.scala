package resonantinduction.em

import net.minecraft.world.World
import cpw.mods.fml.common.registry.GameRegistry
import resonantinduction.em.laser.device.TileLaserEmitter

/**
 * @author Calclavia
 */
class CommonProxy
{
  def init()
  {
    GameRegistry.registerTileEntity(classOf[TileLaserEmitter], "TileLaserEmitter")
  }

  def renderLaser(world: World, start: Vector3, end: Vector3)
  {
    renderLaser(world, start, end, 1)
  }

  def renderLaser(world: World, start: Vector3, end: Vector3, life: Int)
  {

  }
}
