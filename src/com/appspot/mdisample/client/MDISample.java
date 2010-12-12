package com.appspot.mdisample.client;

import com.appspot.mdisample.client.gin.ClientGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;


/**
 * @author jp
 */

public class MDISample implements EntryPoint {
	public final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	public void onModuleLoad() {
		DelayedBindRegistry.bind(ginjector);
		ginjector.getPlaceManager().revealCurrentPlace();
	}
}
