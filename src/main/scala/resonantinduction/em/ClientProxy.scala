package resonantinduction.em

import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.world.World
import cpw.mods.fml.client.registry.{RenderingRegistry, ClientRegistry}
import resonantinduction.em.laser.emitter.{RenderLaserReceiver, TileLaserReceiver, TileLaserEmitter, RenderLaserEmitter}
import resonantinduction.em.laser.mirror.{RenderMirror, TileMirror}
import resonantinduction.em.laser.{EntityLaserFx, BlockRenderingHandler}

/**
 * @author Calclavia
 */
class ClientProxy extends CommonProxy
{
  override def init()
  {
    super.init()
    RenderingRegistry.registerBlockHandler(BlockRenderingHandler)

    ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileLaserEmitter], RenderLaserEmitter)
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileLaserReceiver], RenderLaserReceiver)
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileMirror], RenderMirror)
  }

  override def renderLaser(world: World, start: Vector3, end: Vector3, color: Vector3, enegy: Double)
  {
    FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityLaserFx(world, start, end, color, enegy))
  }
}
