package com.appspot.mdisample.client.place;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;


public class AppPlaceManager extends PlaceManagerImpl {
	@Inject
	public AppPlaceManager(EventBus eventBus, final TokenFormatter tokenFormatter) {
		super(eventBus, tokenFormatter);
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(new PlaceRequest(NameTokens.home));
	}
	
	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		Window.alert("revealErrorPlace()");
	}
}
