package eventlib;

/**
 * Provides methods for getting information about current time
 * @author abc
 *
 */
public interface ITimeProvider {
	
	/**
	 * 
	 * @return time in miliseconds since epoch
	 */
	long getTime();
}
