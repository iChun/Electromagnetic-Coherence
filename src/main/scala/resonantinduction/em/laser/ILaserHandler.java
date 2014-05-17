package resonantinduction.em.laser;

import resonantinduction.em.Vector3;

/**
 * @author Calclavia
 */
public interface ILaserHandler
{
	public boolean onLaserHit(Vector3 renderStart, Vector3 incident, double energy);
}
