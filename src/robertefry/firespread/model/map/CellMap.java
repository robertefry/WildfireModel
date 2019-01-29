package robertefry.firespread.model.map;

import java.awt.image.BufferedImage;
import java.util.function.Function;

import org.joml.Vector2i;

import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.type.Terrain;

@SuppressWarnings("serial")
public class CellMap extends TypeMap<Cell> {
	
	public CellMap() {
	}
	
	public CellMap( 
			int pxwidth, int pxheight, 
			BufferedImage heightmap, int hx, int hy, Function<Integer,Float> heightConversion,
			BufferedImage volatilitymap, int vx, int vy, Function<Integer,Float> volatilityConversion
	) {
		for ( int x = 0; x < pxwidth; x++) for (int y = 0; y < pxheight; y++) {
			float height = heightConversion.apply( heightmap.getRGB( x + hx, y + hy ) );
			float volatility = volatilityConversion.apply( volatilitymap.getRGB( x + vx , y + vy ) );
			Vector2i position = new Vector2i( x, y );
			Cell cell = new Cell( position, new Terrain(height, volatility));
			this.put(position, cell);
		}
	}

}
