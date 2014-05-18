package resonantinduction.em.laser;

import resonantinduction.em.Vector3;

import java.util.List;
import java.util.Set;

/**
 * Devices that can focus on specific angles
 *
 * @author Calclavia
 */
public interface IFocus
{
	/**
	 * Tells the block to look at a specific position
	 * @param position
	 */
	public void focus(Vector3 position);

	public Vector3 getFocus();

	public List<Vector3> getCacheDirections();
}
