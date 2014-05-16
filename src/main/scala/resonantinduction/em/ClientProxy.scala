package resonantinduction.em

import cpw.mods.fml.client.FMLClientHandler
import resonantinduction.em.laser.EntityLaserFx
import net.minecraft.world.World

/**
 * @author Calclavia
 */
class ClientProxy extends CommonProxy
{
  override def renderLaser(world: World, start: Vector3, end: Vector3, life: Int)
  {
    FMLClientHandler.instance().getClient.effectRenderer.addEffect(new EntityLaserFx(world, start, end, life))
  }
}
