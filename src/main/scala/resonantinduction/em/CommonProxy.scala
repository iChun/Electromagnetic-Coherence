package resonantinduction.em

import net.minecraft.world.World
import cpw.mods.fml.common.registry.GameRegistry
import resonantinduction.em.laser.emitter.{TileLaserReceiver, TileLaserEmitter}
import resonantinduction.em.laser.mirror.TileMirror
import net.minecraft.block.Block

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

  def renderBlockParticle(world: World, position: Vector3, block: Block, side: Int)
  {

  }

  def renderLaser(world: World, start: Vector3, end: Vector3, color: Vector3, energy: Double)
  {

  }

  def renderScorch(world: World, position: Vector3, side: Int)
  {

  }
}
