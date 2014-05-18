package resonantinduction.em.laser.mirror

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import resonantinduction.em.{TabEC, Vector3, ElectromagneticCoherence}
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.laser.BlockRenderingHandler
import net.minecraftforge.common.util.ForgeDirection
import cpw.mods.fml.relauncher.{Side, SideOnly}
import resonantinduction.em.laser.focus.ItemFocusingMatrix

/**
 * @author Calclavia
 */
class BlockMirror extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "mirror")
  setBlockTextureName("stone")
  setCreativeTab(TabEC)

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
    if (player.getCurrentEquippedItem == null || !player.getCurrentEquippedItem.getItem.isInstanceOf[ItemFocusingMatrix])
    {
      val mirror = world.getTileEntity(x, y, z).asInstanceOf[TileMirror]

      if (player.isSneaking)
      {
        mirror.focus(new Vector3(ForgeDirection.getOrientation(side)) + new Vector3(x, y, z) + 0.5)
      }
      else
      {
        mirror.focus(new Vector3(hitX, hitY, hitZ) + new Vector3(x, y, z))
      }

      return true
    }

    return false
  }

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileMirror()
  }

  @SideOnly(Side.CLIENT)
  override def getRenderType = BlockRenderingHandler.getRenderId

  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}

