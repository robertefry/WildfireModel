
package robertefry.firespread.model.grid;

import java.awt.Dimension;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class GridDrawHints {

	private float gridX, gridY;
	private float cellWidth, cellHeight;

	public void update( Dimension size, GridSpace space ) {

		float segWidth = (float)size.width / (float)space.getWidth();
		float segHeight = (float)size.height / (float)space.getHeight();
		this.cellWidth = this.cellHeight = Math.min( segWidth, segHeight );

		float drawWidth = this.cellWidth * space.getWidth();
		float drawHeight = this.cellHeight * space.getHeight();
		this.gridX = (float)( size.width - drawWidth ) / 2;
		this.gridY = (float)( size.height - drawHeight ) / 2;

	}

	public float getGridX() {
		return gridX;
	}

	public float getGridY() {
		return gridY;
	}

	public float getCellWidth() {
		return cellWidth;
	}

	public float getCellHeight() {
		return cellHeight;
	}

}
