package robertefry.firespread.conversion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.joml.Vector2i;

public class TopologicalConverter {
	
	// TODO implement colour conversion function
	public static final Map<Vector2i,Integer> convertTopologicalMap( BufferedImage image, Function<Color,Integer> colorConverter ) {
		Map<Vector2i,Integer> colormap = new HashMap<>();
		for ( int x = 0; x < image.getWidth(); x++) {
			for ( int y = 0; y < image.getHeight(); y++) {
				Vector2i position = new Vector2i( x, y );
				int color = image.getRGB(x, y);
				int r = (color & 0x00ff0000) >> 16;
				int g = (color & 0x0000ff00) >> 8;
				int b = (color & 0x000000ff);
				int converted = colorConverter.apply( new Color(r,g,b));
				colormap.put(position, converted);
			}
		}
		return colormap;
	}

}
