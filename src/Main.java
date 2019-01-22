import model.Grid;
import model.type.HeightMap;
import model.type.TerrainMap;
import model.type.WindMap;

public class Main {
	
	public static void main(String[] args) {
		
		// maps
		TerrainMap terrainmap = new TerrainMap();
		WindMap windmap = new WindMap();
		HeightMap heightmap = new HeightMap();
		
		// grid
		Grid grid = new Grid(10,10,terrainmap,windmap,heightmap);
		
		float f = grid.getCell(1,4).getFire().getIntensity();
		System.out.println(f);
		
	}

}
