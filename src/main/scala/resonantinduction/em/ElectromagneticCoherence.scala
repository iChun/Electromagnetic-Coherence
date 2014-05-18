package resonantinduction.em

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.{LanguageRegistry, GameRegistry}
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.AdvancedModelLoader
import resonantinduction.em.laser.emitter.BlockLaserEmitter
import resonantinduction.em.laser.mirror.BlockMirror
import resonantinduction.em.laser.receiver.BlockLaserReceiver
import net.minecraft.init.{Items, Blocks}
import net.minecraftforge.oredict.ShapedOreRecipe

/**
 * @author Calclavia
 */
@Mod(name = "Electromagnetic Coherence", modid = "electromagneticcoherence", version = "1.0.0", modLanguage = "scala")
object ElectromagneticCoherence
{
  val NAME = "Electromagnetic Coherence";
  val MOD_ID = "electromagneticcoherence";

  val DOMAIN = MOD_ID;
  val PREFIX = MOD_ID + ":";

  val DIRECTORY = "assets/" + DOMAIN + "/";
  val MODEL_PATH_NAME = "models/"
  val MODEL_PATH = DIRECTORY + MODEL_PATH_NAME
  val FX_DIRECTORY = "textures/fx/"

  val particleTextures = new ResourceLocation("textures/particle/particles.png")

  @SidedProxy(clientSide = "resonantinduction.em.ClientProxy", serverSide = "resonantinduction.em.CommonProxy")
  var proxy: CommonProxy = null

  var blockLaserEmitter: BlockLaserEmitter = null
  var blockLaserReceiver: BlockLaserReceiver = null
  var blockMirror: BlockMirror = null

  @EventHandler
  def init(event: FMLInitializationEvent)
  {
    blockLaserEmitter = new BlockLaserEmitter()
    blockLaserReceiver = new BlockLaserReceiver()
    blockMirror = new BlockMirror()
    GameRegistry.registerBlock(blockLaserEmitter, "LaserEmitter")
    GameRegistry.registerBlock(blockLaserReceiver, "LaserReceiver")
    GameRegistry.registerBlock(blockMirror, "Mirror")

    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "laserEmitter.name", "Laser Emitter")
    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "mirror.name", "Mirror")
    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "laserReceiver.name", "Laser Receiver")

    GameRegistry.addRecipe(new ShapedOreRecipe(blockLaserEmitter, "IGI", "IDI", "III", 'G': Character, Blocks.glass, 'I': Character, Items.iron_ingot, 'D': Character, Items.diamond))
    GameRegistry.addRecipe(new ShapedOreRecipe(blockLaserReceiver, "IGI", "IRI", "III", 'G': Character, Blocks.glass, 'I': Character, Items.iron_ingot, 'R': Character, Blocks.redstone_block))
    GameRegistry.addRecipe(new ShapedOreRecipe(blockMirror, "GGG", "III", "GGG", 'G': Character, Blocks.glass, 'I': Character, Items.iron_ingot))

    proxy.init()
  }
}
