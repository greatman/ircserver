package com.greatmancode.javaserver.event;

public abstract class Event {

	private boolean cancelled = false;

	protected String getEventName() {
		return getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return getEventName() + " (" + this.getClass().getName() + ")";
	}

	/**
	 * Set cancelled status. Events which wish to be cancellable should implement Cancellable and implement setCancelled as:
	 * <p/>
	 * 
	 * <pre>
	 * public void setCancelled(boolean cancelled) {
	 * 	super.setCancelled(cancelled);
	 * }
	 * </pre>
	 * 
	 * @param cancelled True to cancel event
	 */
	protected void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Returning true will prevent calling any even {@link Order}ed slots.
	 * 
	 * @return false if the event is propogating; events which do not implement Cancellable should never return true here.
	 * @see Order
	 */
	public boolean isCancelled() {
		return cancelled;
	}
}
