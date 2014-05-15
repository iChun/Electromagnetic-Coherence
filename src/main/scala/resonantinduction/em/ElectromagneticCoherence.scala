package resonantinduction.em

import cpw.mods.fml.common.{FMLLog, SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.{LanguageRegistry, GameRegistry}

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

  @SidedProxy(clientSide = "resonantinduction.em.ClientProxy", serverSide = "resonantinduction.em.CommonProxy")
  var proxy: CommonProxy = null

  var blockLaserEmitter: BlockLaserEmitter = null

  //var blockMirror

  @EventHandler
  def init(event: FMLInitializationEvent)
  {
    blockLaserEmitter = new BlockLaserEmitter()
    GameRegistry.registerBlock(blockLaserEmitter, "LaserEmitter")

    loadLanguages(DIRECTORY + "languages/", Array("en_US"))
  }

  def loadLanguages(languagePath: String, languageSupported: Array[String]): Int =
  {
    var languages: Int = 0
    for (language <- languageSupported)
    {
      LanguageRegistry.instance.loadLocalization(languagePath + language + ".properties", language, false)
      if (LanguageRegistry.instance.getStringLocalization("children", language) ne "")
      {
        try
        {
          val children: Array[String] = LanguageRegistry.instance.getStringLocalization("children", language).split(",")
          for (child <- children)
          {
            if (child != "" || child != null)
            {
              LanguageRegistry.instance.loadLocalization(languagePath + language + ".properties", child, false)
              languages += 1
            }
          }
        }
        catch
          {
            case e: Exception =>
            {
              FMLLog.severe("Failed to load a child language file.")
              e.printStackTrace
            }
          }
      }
      languages += 1
    }
    return languages
  }
}
