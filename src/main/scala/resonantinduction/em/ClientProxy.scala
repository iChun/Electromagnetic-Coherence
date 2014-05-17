package resonantinduction.em

import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.world.World
import cpw.mods.fml.client.registry.ClientRegistry
import resonantinduction.em.laser.device.{TileLaserEmitter, RenderLaserEmitter, EntityLaserFx}

/**
 * @author Calclavia
 */
class ClientProxy extends CommonProxy
{
  override def init()
  {
    super.init()
    ClientRegistry.bindTileEntitySpecialRenderer(classOf[TileLaserEmitter], new RenderLaserEmitter())
  }

  override def renderLaser(world: World, start: Vector3, end: Vector3, life: Int)
  {
    FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityLaserFx(world, start, end, life))
  }
}
