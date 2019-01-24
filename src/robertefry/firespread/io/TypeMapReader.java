
package robertefry.firespread.io;

import robertefry.firespread.model.map.TypeMap;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class TypeMapReader {
	
	public static TypeMap populateFromCSV( TypeMap map, String file ) {
		ParserCSV.parse( file ).forEach( record -> {
			map.insert( record );
		});
		return map;
	}

}
