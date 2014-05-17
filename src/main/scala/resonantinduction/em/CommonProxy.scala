package resonantinduction.em

import net.minecraft.world.World
import cpw.mods.fml.common.registry.GameRegistry
import resonantinduction.em.laser.emitter.{TileLaserReceiver, TileLaserEmitter}
import resonantinduction.em.laser.mirror.TileMirror

/**
 * @author Calclavia
 */
class CommonProxy
{
  def init()
  {
    GameRegistry.registerTileEntity(classOf[TileLaserEmitter], "RILaserEmitter")
    GameRegistry.registerTileEntity(classOf[TileLaserReceiver], "RILaserReceiver")
    GameRegistry.registerTileEntity(classOf[TileMirror], "RIMirror")
  }

  def renderLaser(world: World, start: Vector3, end: Vector3, color: Vector3, energy: Double)
  {

  }
}
