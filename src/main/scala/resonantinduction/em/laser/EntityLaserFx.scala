package resonantinduction.em.laser

import net.minecraft.world.World
import net.minecraft.client.particle.EntityFX
import net.minecraft.client.renderer.Tessellator
import org.lwjgl.opengl.GL11
import cpw.mods.fml.client.FMLClientHandler
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.util.ResourceLocation

/**
 * @author Calclavia
 */
class EntityLaserFx(par1World: World, start: Vector3, end: Vector3, life: Int) extends EntityFX(par1World, start.x, start.y, start.z, 0.0D, 0.0D, 0.0D)
{
  val laserMiddleTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserMiddle.png")

  val length = start.distance(end)
  this.particleMaxAge = life


  override def renderParticle(tessellator: Tessellator, par2: Float, par3: Float, par4: Float, par5: Float, par6: Float, par7: Float)
  {
    GL11.glPushMatrix()

    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE)

    FMLClientHandler.instance.getClient.renderEngine.bindTexture(laserMiddleTexture)

    GL11.glColor4f(1, 1, 1, 1)

    tessellator.setBrightness(200)
    tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1);

    GL11.glPopMatrix()
  }

}
