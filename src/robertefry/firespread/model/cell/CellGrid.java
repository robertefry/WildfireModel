
package robertefry.firespread.model.cell;

import java.awt.Point;
import java.util.Iterator;

/**
 * @author Robert E Fry
 * @date 14 Mar 2019
 */
public class CellGrid implements Iterable< Cell > {
	
	private Cell[][] cells;
	
	public CellGrid( Cell[][] cells ) {
		this.cells = cells;
	}
	
	public CellGrid( CellGrid grid ) {
		cells = new Cell[grid.rows()][grid.cols()];
		for ( int i = 0; i < grid.rows(); i++ ) for ( int j = 0; j < grid.cols(); j++ ) {
			cells[i][j] = new Cell( grid.get( i, j ) );
		}
	}
	
	public CellGrid() {
		cells = new Cell[0][0];
	}
	
	public int rows() {
		return cells.length;
	}
	
	public int cols() {
		return cells.length == 0 ? 0 : cells[0].length;
	}
	
	public boolean contains( Point p ) {
		return p.x < rows() && p.y < cols();
	}
	
	public boolean contains( Cell cell ) {
		for ( int i = 0; i < rows(); i++ ) for ( int j = 0; j < cols(); j++ ) {
			if ( cells[i][j] == cell ) return true;
		}
		return false;
	}
	
	public Cell get( int i, int j ) {
		if ( ( i | j ) < 0 || i >= rows() || j >= cols() ) return null;
		return cells[i][j];
	}
	
	public void set( int i, int j, Cell c ) {
		if ( i < rows() && j < cols() ) cells[i][j] = c;
	}
	
	@Override
	public Iterator< Cell > iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator< Cell > {
		
		private int local = 0;
		
		@Override
		public boolean hasNext() {
			return local < rows() * cols();
		}
		
		@Override
		public Cell next() {
			Cell cell = get( local / rows(), local % rows() );
			local++;
			return cell;
		}
		
	}
	
}
