package robertefry.firespread.io;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.Function;

import org.joml.Vector2i;

import robertefry.firespread.model.map.DataMap;

public class DataMaps {

	// TODO implement colour conversion function
	
	public static final DataMap<Double> convertTopologicalMap(BufferedImage image,
			Function<Color, Double> colorConverter) {
		DataMap<Double> colormap = new DataMap<>();
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Vector2i position = new Vector2i(x, y);
				int color = image.getRGB(x, y);
				int r = (color & 0x00ff0000) >> 16;
				int g = (color & 0x0000ff00) >> 8;
				int b = (color & 0x000000ff);
				double converted = colorConverter.apply(new Color(r, g, b));
				colormap.put(position, converted);
			}
		}
		return colormap;
	}

}
