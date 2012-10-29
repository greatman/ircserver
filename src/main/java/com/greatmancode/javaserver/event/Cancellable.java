package com.greatmancode.javaserver.event;

/**
 * Interface for events that can be cancelled, to prevent them from propagating to downstream handlers.
 */
public interface Cancellable {
	
	/**
	 * If an event stops propagating (ie, is cancelled) partway through an even
	 * slot, that slot will not cease execution, but future even slots will not
	 * be called.
	 * @param cancelled True to set event canceled, False to uncancel event.
	 */
	public void setCancelled(boolean cancelled);

	/**
	 * Get event canceled state.
	 * @return whether event is cancelled
	 */
	public boolean isCancelled();
}