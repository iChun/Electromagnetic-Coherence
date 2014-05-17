package resonantinduction.em

import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.world.World
import cpw.mods.fml.client.registry.{RenderingRegistry, ClientRegistry}
import resonantinduction.em.laser.device.{TileLaserEmitter, RenderLaserEmitter, EntityLaserFx}
import resonantinduction.em.laser.mirror.{RenderMirror, TileMirror}
import resonantinduction.em.laser.BlockRenderingHandler

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
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileMirror], RenderMirror)
  }

  override def renderLaser(world: World, start: Vector3, end: Vector3, life: Int)
  {
    FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityLaserFx(world, start, end, life))
  }
}
