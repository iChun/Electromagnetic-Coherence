package resonantinduction.em.laser

import net.minecraft.world.World
import net.minecraft.client.particle.EntityFX
import net.minecraft.client.renderer.Tessellator
import org.lwjgl.opengl.GL11._
import cpw.mods.fml.client.FMLClientHandler
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.util.ResourceLocation
import net.minecraft.client.renderer.texture.TextureMap

/**
 * @author Calclavia
 */
class EntityLaserFx(par1World: World, start: Vector3, end: Vector3, life: Int) extends EntityFX(par1World, start.x, start.y, start.z)
{
  val laserStartTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserStart.png")
  val laserMiddleTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserMiddle.png")
  val laserEndTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserEnd.png")
  val endSize = 0.0999
  val detail = 36
  val rotationSpeed = 15

  val midPoint = (end + start) / 2
  posX = midPoint.x
  posY = midPoint.y
  posZ = midPoint.z
  particleScale = 0.2f
  particleMaxAge = life
  particleAlpha = 1 / detail.asInstanceOf[Float]
  particleRed = 1
  particleGreen = 0.1f
  particleBlue = 0.1f

  val length = start.distance(end)

  val difference = end - start
  val angles = new Vector3(Math.toDegrees(Math.atan2(difference.x, difference.z)), Math.toDegrees(-Math.atan2(difference.y, Math.hypot(difference.z, difference.x))), 0)

  val modifierTranslation = (length / 2) + endSize;

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
  }

  override def renderParticle(tessellator: Tessellator, par2: Float, par3: Float, par4: Float, par5: Float, par6: Float, par7: Float)
  {
    tessellator.draw()

    glPushMatrix()

    glBlendFunc(GL_SRC_ALPHA, GL_ONE)
    glEnable(3042);
    glColor4f(1, 1, 1, 1)

    /**
     * Translation
     */
    val f11 = this.prevPosX + (this.posX - this.prevPosX) * par2 - EntityFX.interpPosX
    val f12 = this.prevPosY + (this.posY - this.prevPosY) * par2 - EntityFX.interpPosY
    val f13 = this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - EntityFX.interpPosZ

    glTranslated(f11, f12, f13)

    /**
     * Rotate the beam
     */
    glRotated(angles.x, 0, 1, 0)
    glRotated(angles.y, 1, 0, 0)

    glRotated(90, 1, 0, 0)

    val time = worldObj.getTotalWorldTime()

    /**
     * Tessellate laser
     */
    glPushMatrix()
    glRotatef(time % (360 / rotationSpeed) * rotationSpeed + rotationSpeed * par2, 0, 1, 0)

    for (a <- 0 to detail)
    {
      glRotatef(a * 360 / detail, 0, 1, 0)

      /**
       * Render Cap
       */
      glPushMatrix()
      glTranslated(0, -modifierTranslation, 0)
      glRotatef(180, 1, 0, 0)

      FMLClientHandler.instance.getClient.renderEngine.bindTexture(laserStartTexture)

      tessellator.startDrawingQuads()
      tessellator.setBrightness(200)
      tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha)
      tessellator.addVertexWithUV(-particleScale, -particleScale, 0, 0, 0)
      tessellator.addVertexWithUV(-particleScale, particleScale, 0, 0, 1)
      tessellator.addVertexWithUV(particleScale, particleScale, 0, 1, 1)
      tessellator.addVertexWithUV(particleScale, -particleScale, 0, 1, 0)
      tessellator.draw()

      glPopMatrix()

      /**
       * Render Middle
       */
      FMLClientHandler.instance.getClient.renderEngine.bindTexture(laserMiddleTexture)

      tessellator.startDrawingQuads()
      tessellator.setBrightness(200)
      tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha)
      tessellator.addVertexWithUV(-particleScale, -length / 2 + endSize, 0, 0, 0)
      tessellator.addVertexWithUV(-particleScale, length / 2 - endSize, 0, 0, 1)
      tessellator.addVertexWithUV(particleScale, length / 2 - endSize, 0, 1, 1)
      tessellator.addVertexWithUV(particleScale, -length / 2 + endSize, 0, 1, 0)
      tessellator.draw()


      /**
       * Render End
       */
      glPushMatrix()
      glTranslated(0, modifierTranslation, 0)
      glRotatef(180, 1, 0, 0)

      FMLClientHandler.instance.getClient.renderEngine.bindTexture(laserEndTexture)

      tessellator.startDrawingQuads()
      tessellator.setBrightness(200)
      tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha)
      tessellator.addVertexWithUV(-particleScale, -particleScale, 0, 0, 0)
      tessellator.addVertexWithUV(-particleScale, particleScale, 0, 0, 1)
      tessellator.addVertexWithUV(particleScale, particleScale, 0, 1, 1)
      tessellator.addVertexWithUV(particleScale, -particleScale, 0, 1, 0)
      tessellator.draw()

      glPopMatrix()
    }

    glPopMatrix()

    glPopMatrix()

    FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture)
    tessellator.startDrawingQuads()
  }

}
