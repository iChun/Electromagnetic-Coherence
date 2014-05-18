package resonantinduction.em.laser.mirror

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import resonantinduction.em.{Vector3, ElectromagneticCoherence}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.laser.BlockRenderingHandler
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author Calclavia
 */
class BlockMirror extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "mirror")
  setBlockTextureName("stone")
  setCreativeTab(CreativeTabs.tabRedstone)

  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack)
  {
    val mirror = world.getTileEntity(x, y, z).asInstanceOf[TileMirror]
    mirror.normal = new Vector3(-entity.rotationYaw, entity.rotationPitch).normalize
  }

  /**
   * Called upon block activation (right click on the block.)
   */
  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean =
  {
    val mirror = world.getTileEntity(x, y, z).asInstanceOf[TileMirror]

    if (player.isSneaking)
    {
      mirror.normal = new Vector3(ForgeDirection.getOrientation(side))
    }
    else
    {
      mirror.normal = (new Vector3(hitX, hitY, hitZ) - 0.5).normalize
    }

    world.markBlockForUpdate(x, y, z)
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

