
package robertefry.firespread.ui.dialog;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.swing.JOptionPane;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public abstract class UIDialog< T > extends UIMessage {
	private static final long serialVersionUID = -3013368826748650387L;
	
	private volatile boolean fetched = false;
	private final FutureTask< T > future = new FutureTask<>( () -> {
		return getReturn();
	} );
	
	protected abstract boolean canReturn();
	
	protected abstract T getReturn();
	
	public T fetch() throws CancellationException, InterruptedException, ExecutionException {
		T fetch = future.get();
		fetched = true;
		return fetch;
	}
	
	public boolean isFetched() {
		return fetched;
	}
	
	@Override
	protected final void confirm() {
		if ( canReturn() ) {
			future.run();
			dispose();
		} else {
			JOptionPane.showMessageDialog(
				contentPane, "Inoperable inputs.\nPlease make sure all required fields are filled and correct.",
				"Warning",
				JOptionPane.ERROR_MESSAGE
			);
		}
	}
	
	@Override
	protected final void cancel() {
		future.cancel( false );
		dispose();
	}
	
}
