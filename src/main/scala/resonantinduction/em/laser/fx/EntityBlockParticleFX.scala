package resonantinduction.em.laser.fx

import net.minecraft.client.particle.EntityDiggingFX
import net.minecraft.world.World
import net.minecraft.block.Block

/**
 * @author Calclavia
 */
class EntityBlockParticleFX(world: World, x: Double, y: Double, z: Double, vX: Double, vY: Double, vZ: Double, block: Block, side: Int) extends EntityDiggingFX(world, x, y, z, vX, vY, vZ, block, side)
{
}
