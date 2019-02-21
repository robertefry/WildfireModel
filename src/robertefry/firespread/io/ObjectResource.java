
package robertefry.firespread.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.logging.LogFactory;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class ObjectResource {

	public static void save( Object obj, File file ) {
		try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( file ) ) ) {
			oos.writeObject( obj );
		} catch ( FileNotFoundException e ) {
			LogFactory.getLog( ObjectResource.class ).warn( e );
		} catch ( IOException e ) {
			LogFactory.getLog( ObjectResource.class ).warn( e );
		}
	}

	public static Object load( File file ) {
		Object obj = null;
		try ( ObjectInputStream ois = new ObjectInputStream( new FileInputStream( file ) ) ) {
			obj = ois.readObject();
		} catch ( FileNotFoundException e ) {
			LogFactory.getLog( ObjectResource.class ).warn( e );
		} catch ( IOException e ) {
			LogFactory.getLog( ObjectResource.class ).warn( e );
		} catch ( ClassNotFoundException e ) {
			LogFactory.getLog( ObjectResource.class ).warn( e );
		}
		return obj;
	}

}
