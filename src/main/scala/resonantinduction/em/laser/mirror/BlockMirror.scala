package resonantinduction.em.laser.mirror

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.laser.BlockRenderingHandler

/**
 * @author Calclavia
 */
class BlockMirror extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "mirror")
  setCreativeTab(CreativeTabs.tabRedstone)

  /**
   * Called upon block activation (right click on the block.)
   */
  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean =
  {
    val mirror = world.getTileEntity(x, y, z).asInstanceOf[TileMirror]
    mirror.normal = (new Vector3(hitX, hitY, hitZ) - 0.5).normalize
    return true
  }

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileMirror()
  }

  override def getRenderType = BlockRenderingHandler.getRenderId

  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}

