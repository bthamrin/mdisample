package com.appspot.mdisample.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;


public class NewTabEvent extends GwtEvent<NewTabEvent.NewTabEventHandler> {
	public interface NewTabEventHandler extends EventHandler {
		void onNewTab(NewTabEvent event);
	}

	public interface HasCloseTabHandlers extends HasHandlers {
		HandlerRegistration addCloseTabHandler(NewTabEventHandler handler);
	}

	private static Type<NewTabEventHandler> TYPE = new Type<NewTabEventHandler>();

	public static void fire(HasHandlers source, String token, String parameter) {
		if (TYPE != null) {
			source.fireEvent(new NewTabEvent(token, parameter));
		}
	}

	public static Type<NewTabEventHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<NewTabEventHandler> getAssociatedType() {
		return TYPE;
	}
	
	
	private String token;
	private String parameter;
	
	
	public NewTabEvent(String token, String parameter) {
		this.token = token;
		this.parameter = parameter;
	}
	

	@Override
	protected void dispatch(NewTabEventHandler handler) {
		handler.onNewTab(this);
	}
	
	public String getToken() {
		return token;
	}
	
	public String getParameter() {
		return parameter;
	}
	
}