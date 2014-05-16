package resonantinduction.em.laser

import net.minecraft.world.World
import net.minecraft.client.particle.EntityFX
import net.minecraft.client.renderer.Tessellator
import org.lwjgl.opengl.GL11
import cpw.mods.fml.client.FMLClientHandler
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.util.ResourceLocation
import net.minecraft.client.renderer.texture.TextureMap

/**
 * @author Calclavia
 */
class EntityLaserFx(par1World: World, start: Vector3, end: Vector3, life: Int) extends EntityFX(par1World, start.x, start.y, start.z, 0.0D, 0.0D, 0.0D)
{
  val laserMiddleTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserMiddle.png")

  val length = start.distance(end)
  this.particleMaxAge = life

  println(end)

  override def onUpdate
  {
    prevPosX = posX
    prevPosY = posY
    prevPosZ = posZ

    if (particleAge >= particleMaxAge)
    {
      setDead
    }

    particleAge += 1

    moveEntity(motionX, motionY, motionZ)
  }

  override def renderParticle(tessellator: Tessellator, par2: Float, par3: Float, par4: Float, par5: Float, par6: Float, par7: Float)
  {
    tessellator.draw()

    GL11.glPushMatrix()

    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE)

    FMLClientHandler.instance.getClient.renderEngine.bindTexture(laserMiddleTexture)

    /**
     * Translation
     */
    GL11.glTranslated(posX, posY, posZ)

    GL11.glColor4f(1, 1, 1, 1)

    val size = 0.5
    val var9 = 0.9

    val var11 = worldObj.getTotalWorldTime() + par2
    val var12 = -var11 * 0.2 - (-var11 * 0.1)

    /**
     * Tesselate laser
     */
    val var44 = -0.15D * size
    val var17 = 0.15D * size
    val var44b = -0.15D * size
    val var17b = 0.15D * size

    val var29 = length * size * var9
    val var31 = 0.0D
    val var33 = 1.0D
    val var35 = -1.0F + var12
    val var37 = length * size * var9 + var35

    tessellator.startDrawingQuads()
    tessellator.setBrightness(200)
    tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1)
    tessellator.addVertexWithUV(var44b, var29, 0.0D, var33, var37)
    tessellator.addVertexWithUV(var44, 0.0D, 0.0D, var33, var35)
    tessellator.addVertexWithUV(var17, 0.0D, 0.0D, var31, var35)
    tessellator.addVertexWithUV(var17b, var29, 0.0D, var31, var37)
    tessellator.draw()

    GL11.glPopMatrix()

    FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture)
    tessellator.startDrawingQuads()
  }

}
