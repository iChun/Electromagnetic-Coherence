package resonantinduction.em

import cpw.mods.fml.common.{FMLLog, SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.{LanguageRegistry, GameRegistry}
import net.minecraft.util.Vec3
import net.minecraftforge.client.model.AdvancedModelLoader
import resonantinduction.em.laser.device.BlockLaserEmitter

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
  val MODEL_PATH_NAME =  "models/"
  val MODEL_PATH = DIRECTORY + MODEL_PATH_NAME

  @SidedProxy(clientSide = "resonantinduction.em.ClientProxy", serverSide = "resonantinduction.em.CommonProxy")
  var proxy: CommonProxy = null

  var blockLaserEmitter: BlockLaserEmitter = null

  @EventHandler
  def init(event: FMLInitializationEvent)
  {
    AdvancedModelLoader.registerModelHandler(new FixedTechneModelLoader())

    blockLaserEmitter = new BlockLaserEmitter()
    GameRegistry.registerBlock(blockLaserEmitter, "LaserEmitter")

    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "laserEmitter.name", "Laser Emitter")
    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "mirror.name", "Mirror")
    LanguageRegistry.instance.addStringLocalization("tile." + PREFIX + "laserReceiver.name", "Laser Receiver")

    proxy.init()
  }
}
