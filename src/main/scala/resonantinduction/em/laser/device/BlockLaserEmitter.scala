package resonantinduction.em.laser.device

import net.minecraft.block.{BlockPistonBase, BlockContainer}
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import resonantinduction.em.laser.BlockRenderingHandler

/**
 * @author Calclavia
 */
class BlockLaserEmitter extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "laserEmitter")
  setCreativeTab(CreativeTabs.tabRedstone)

  /**
   * Called when the block is placed in the world.
   */
  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack)
  {
    val l = BlockPistonBase.determineOrientation(world, x, y, z, entity)
    world.setBlockMetadataWithNotify(x, y, z, l, 2)
  }

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileLaserEmitter()
  }

  override def getRenderType = BlockRenderingHandler.getRenderId

  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}
