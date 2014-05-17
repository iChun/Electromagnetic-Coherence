package resonantinduction.em.laser.mirror

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraft.util.ResourceLocation
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.tileentity.TileEntity
import org.lwjgl.opengl.GL11._
import cpw.mods.fml.client.FMLClientHandler
import org.lwjgl.opengl.GL11

/**
 * @author Calclavia
 */
class RenderMirror extends TileEntitySpecialRenderer
{
  val model = AdvancedModelLoader.loadModel(new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "mirror.tcn"))
  val texture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "mirror.png")

  def renderTileEntityAt(tileEntity: TileEntity, x: Double, y: Double, z: Double, f: Float)
  {
    glPushMatrix()
    glTranslated(x + 0.5, y + 0.5, z + 0.5)

    FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)

    val tile = tileEntity.asInstanceOf[TileMirror]

    glRotated(180, tile.normal.x, tile.normal.y, tile.normal.z)
    model.renderOnly("mirror", "mirrorBacking", "standConnector")

    GL11.glPopMatrix()
  }
}
