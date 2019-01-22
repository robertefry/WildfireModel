package model;

public interface Sequencable<T> {
	
	public void next();
	
	public T getNext();
	
	public T getSequence( int n );

}
