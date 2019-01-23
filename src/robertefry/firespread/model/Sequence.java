
package robertefry.firespread.model;

public interface Sequence< T > {

	default void next() {
		throw new RuntimeException( "No definition for next term in sequence" );
	}

	default T getNext() {
		throw new RuntimeException( "No definition for next term in sequence" );
	}

	default T getNthTerm( int n ) {
		throw new RuntimeException( "No definition for nth term in sequence" );
	}

}
