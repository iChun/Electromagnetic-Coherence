package resonantinduction.em.laser

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.model.AdvancedModelLoader
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import cpw.mods.fml.client.FMLClientHandler

/**
 * @author Calclavia
 */
class RenderLaserEmitter extends TileEntitySpecialRenderer
{
  val model = AdvancedModelLoader.loadModel(new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "laserEmitter.tcn"))
  val texture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "laserEmitter.png")

  def renderTileEntityAt(tileEntity: TileEntity, x: Double, y: Double, z: Double, f: Float)
  {
    GL11.glPushMatrix()
    GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5)
    FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)
    model.renderAll()
    GL11.glPopMatrix()
  }
}
