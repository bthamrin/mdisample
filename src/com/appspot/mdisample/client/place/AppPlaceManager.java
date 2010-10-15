package com.appspot.mdisample.client.place;

import com.appspot.mdisample.client.event.NewTabEvent;
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
		String token = NameTokens.parseQuerystring(invalidHistoryToken);
		if (token != null) {
			String parameter = invalidHistoryToken.substring(token.length());

			if (parameter.length() > 0) {
				NewTabEvent.fire(this, token, parameter);

			}
		}

	}

}
