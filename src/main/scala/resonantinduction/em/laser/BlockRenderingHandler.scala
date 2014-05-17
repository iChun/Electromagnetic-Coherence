package resonantinduction.em.laser

import cpw.mods.fml.client.registry.{RenderingRegistry, ISimpleBlockRenderingHandler}
import net.minecraft.client.renderer.RenderBlocks
import net.minecraft.world.IBlockAccess
import net.minecraft.block.Block
import resonantinduction.em.laser.mirror.{RenderMirror, BlockMirror}
import resonantinduction.em.laser.device.{RenderLaserEmitter, BlockLaserEmitter}

/**
 * @author Calclavia
 */
object BlockRenderingHandler extends ISimpleBlockRenderingHandler
{
  val renderID = RenderingRegistry.getNextAvailableRenderId

  def renderInventoryBlock(block: Block, metadata: Int, modelId: Int, renderer: RenderBlocks)
  {
    if (block.isInstanceOf[BlockLaserEmitter])
    {
      RenderLaserEmitter.renderItem()
    }
    else if (block.isInstanceOf[BlockMirror])
    {
      RenderMirror.renderItem()
    }
  }

  def renderWorldBlock(world: IBlockAccess, x: Int, y: Int, z: Int, block: Block, modelId: Int, renderer: RenderBlocks) = false

  def shouldRender3DInInventory(modelId: Int) = true

  def getRenderId = renderID
}
