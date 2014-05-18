package resonantinduction.em.laser.mirror

import net.minecraft.block.{BlockPistonBase, BlockContainer}
import net.minecraft.block.material.Material
import resonantinduction.em.{TabEC, Vector3, ElectromagneticCoherence}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.laser.BlockRenderingHandler
import net.minecraftforge.common.util.ForgeDirection
import cpw.mods.fml.relauncher.{Side, SideOnly}

/**
 * @author Calclavia
 */
class BlockFocusCrystal extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "focusCrystal")
  setBlockTextureName("glass")
  setCreativeTab(TabEC)

  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack)
  {
    val l = BlockPistonBase.determineOrientation(world, x, y, z, entity)
    world.setBlockMetadataWithNotify(x, y, z, l, 2)
  }

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileFocusCrystal()
  }
/*
  @SideOnly(Side.CLIENT)
  override def getRenderType = BlockRenderingHandler.getRenderId
*/
  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}

