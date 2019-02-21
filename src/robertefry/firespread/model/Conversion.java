
package robertefry.firespread.model;

import java.util.function.Function;
import robertefry.firespread.model.terrain.Terrain;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Conversion {

	public static final Conversion.Type TYPE_INT_NULL = new Conversion.Type( 0x00000000, 0 );
	public static final Conversion.Type TYPE_INT_RGB = new Conversion.Type( 0x00FFFFFF, 0 );
	public static final Conversion.Type TYPE_INT_ARGB = new Conversion.Type( 0xFFFFFFFF, 0 );
	public static final Conversion.Type TYPE_INT_RGB_MONOCHROME_A = new Conversion.Type( 0xFF000000, 16 );
	public static final Conversion.Type TYPE_INT_ARGB_MONOCHROME_A = new Conversion.Type( 0xFF000000, 16 );
	public static final Conversion.Type TYPE_INT_RGB_MONOCHROME_R = new Conversion.Type( 0x00FF0000, 8 );
	public static final Conversion.Type TYPE_INT_ARGB_MONOCHROME_R = new Conversion.Type( 0x00FF00, 8 );
	public static final Conversion.Type TYPE_INT_RGB_MONOCHROME_G = new Conversion.Type( 0x0000FF00, 4 );
	public static final Conversion.Type TYPE_INT_ARGB_MONOCHROME_G = new Conversion.Type( 0x0000FF00, 4 );
	public static final Conversion.Type TYPE_INT_RGB_MONOCHROME_B = new Conversion.Type( 0x000000FF, 0 );
	public static final Conversion.Type TYPE_INT_ARGB_MONOCHROME_B = new Conversion.Type( 0x000000FF, 0 );
	
	// TODO fix conversion functions & image map loading

	public static Function< Number, Float > getElevationConversion( Conversion.Type type ) {
		return ( color ) -> {
			return 1.0f;
		};
		//		return ( color ) -> {
		//			return (float)( ( color.intValue() & type.mask ) >>> type.postshift );
		//		};
	}

	public static Function< Number, Terrain > getTerrainConversion( Conversion.Type type ) {
		return ( color ) -> {
			return new Terrain( 1.0f, false );
		};
		//		return ( color ) -> {
		//			return new Terrain( ( color.intValue() & type.mask ) >>> type.postshift, false );
		//		};
	}

	private static final class Type {

		public final int mask, postshift;

		public Type( int mask, int postshift ) {
			this.mask = mask;
			this.postshift = postshift;
		}

	}

}
