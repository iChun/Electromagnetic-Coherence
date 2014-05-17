package resonantinduction.em.laser.mirror

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntity

/**
 * @author Calclavia
 */
class BlockMirror extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "mirror")
  setCreativeTab(CreativeTabs.tabRedstone)

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileMirror()
  }

  override def getRenderType = -1

  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}

