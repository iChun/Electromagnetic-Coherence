package resonantinduction.em

import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.world.World
import cpw.mods.fml.client.registry.{RenderingRegistry, ClientRegistry}
import resonantinduction.em.laser.emitter.{RenderLaserReceiver, TileLaserReceiver, TileLaserEmitter, RenderLaserEmitter}
import resonantinduction.em.laser.mirror.{RenderMirror, TileMirror}
import resonantinduction.em.laser.BlockRenderingHandler
import net.minecraft.block.Block
import resonantinduction.em.laser.fx.{EntityBlockParticleFX, EntityScorchFX, EntityLaserFX}

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

  override def renderScorch(world: World, position: Vector3, side: Int)
  {
    if (FMLClientHandler.instance.getClient.gameSettings.particleSetting != 2)
      FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityScorchFX(world, position, side))
  }

  override def renderBlockParticle(world: World, position: Vector3, block: Block, side: Int)
  {
    if (FMLClientHandler.instance.getClient.gameSettings.particleSetting != 2)
      FMLClientHandler.instance.getClient.effectRenderer.addEffect(new EntityBlockParticleFX(world, position.x, position.y, position.z, 0, 0, 0, block, side))
  }

  override def renderLaser(world: World, start: Vector3, end: Vector3, color: Vector3, enegy: Double)
  {
    FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityLaserFX(world, start, end, color, enegy))
  }
}
