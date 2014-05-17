package resonantinduction.em.laser

import net.minecraft.world.World
import net.minecraft.client.particle.EntityFX
import net.minecraft.client.renderer.Tessellator
import org.lwjgl.opengl.GL11
import cpw.mods.fml.client.FMLClientHandler
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.util.ResourceLocation
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.entity.RenderManager

/**
 * @author Calclavia
 */
class EntityLaserFx(par1World: World, start: Vector3, end: Vector3, life: Int) extends EntityFX(par1World, start.x, start.y, start.z, 0.0D, 0.0D, 0.0D)
{
  val midPoint = (end + start) / 2

  val laserMiddleTexture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, "textures/fx/laserMiddle.png")

  val length = start.distance(end)
  this.particleMaxAge = life

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

    //moveEntity(motionX, motionY, motionZ)
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
    GL11.glEnable(3042);
    GL11.glColor4f(1, 1, 1, 1)

    val f11 = this.prevPosX + (this.posX - this.prevPosX) * par2 - EntityFX.interpPosX
    val f12 = this.prevPosY + (this.posY - this.prevPosY) * par2 - EntityFX.interpPosY
    val f13 = this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - EntityFX.interpPosZ

    GL11.glTranslated(f11, f12, f13)

    /**
     * Rotate to face player
     */
    GL11.glRotated(-RenderManager.instance.playerViewY, 0, 1, 0)
    GL11.glRotated(RenderManager.instance.playerViewX, 1, 0, 0)

    GL11.glRotated(90, 0, 0, 1)

    val thickness = 0.15
    val time = worldObj.getTotalWorldTime()

    /**
     * Tessellate laser
     */
    tessellator.startDrawingQuads()
    tessellator.setBrightness(200)
    tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha)
    tessellator.addVertexWithUV(-thickness, -length, 0, 0, 0)
    tessellator.addVertexWithUV(-thickness, length , 0, 0, 1)
    tessellator.addVertexWithUV(thickness, length , 0, 1, 1)
    tessellator.addVertexWithUV(thickness, -length , 0, 1, 0)
    tessellator.draw()

    GL11.glPopMatrix()

    FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationBlocksTexture)
    tessellator.startDrawingQuads()
  }

}
