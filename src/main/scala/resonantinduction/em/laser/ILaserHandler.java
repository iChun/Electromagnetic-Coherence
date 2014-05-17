package resonantinduction.em.laser;

import net.minecraft.util.MovingObjectPosition;
import resonantinduction.em.Vector3;

/**
 * @author Calclavia
 */
public interface ILaserHandler
{
	public boolean onLaserHit(Vector3 renderStart, Vector3 incident, MovingObjectPosition hit, double energy);
}
