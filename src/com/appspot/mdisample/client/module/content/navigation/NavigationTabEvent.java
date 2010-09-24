package com.appspot.mdisample.client.module.content.navigation;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.gwtplatform.mvp.client.HasEventBus;


/**
 * @author Olivier Monaco
 * @author Christian Goudreau
 */
public class NavigationTabEvent extends GwtEvent<NavigationTabEvent.NavigationTabHandler> {
	
	public interface NavigationTabHandler extends EventHandler {
		public void onCloseTab(NavigationTabElement element);

		public void onRevealTab(NavigationTabElement element);
	}

	private static enum Action {
		REVEAL, CLOSE;
	}

	private static Type<NavigationTabHandler> type;

	public static void fireClose(HasEventBus source, NavigationTabElement element) {
		if (type != null) {
			source.fireEvent(new NavigationTabEvent(Action.CLOSE, element));
		}
	}

	public static void fireReveal(HasEventBus source, NavigationTabElement element) {
		if (type != null) {
			source.fireEvent(new NavigationTabEvent(Action.REVEAL, element));
		}
	}

	public static Type<NavigationTabHandler> getType() {
		
		// FIXME: Why isn't this set in the constructor instead?
		
		if (type == null) {
			type = new Type<NavigationTabHandler>();
		}
		return type;
	}

	private NavigationTabElement element;
	private Action action;

	public NavigationTabEvent(Action action, NavigationTabElement element) {
		this.element = element;
		this.action = action;
	}

	@Override
	protected void dispatch(NavigationTabHandler handler) {
		switch (action) {
			case REVEAL:
				handler.onRevealTab(element);
				break;
			case CLOSE:
				handler.onCloseTab(element);
				break;
		}
	}

	@Override
	public Type<NavigationTabHandler> getAssociatedType() {
		return getType();
	}

	public NavigationTabElement getElement() {
		return element;
	}
}
