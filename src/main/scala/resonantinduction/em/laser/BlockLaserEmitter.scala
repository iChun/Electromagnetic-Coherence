package resonantinduction.em.laser

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.ElectromagneticCoherence

/**
 * @author Calclavia
 */
class BlockLaserEmitter extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "laserEmitter")
  setCreativeTab(CreativeTabs.tabRedstone)

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileLaserEmitter()
  }
}
