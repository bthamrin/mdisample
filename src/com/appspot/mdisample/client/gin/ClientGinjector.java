package com.appspot.mdisample.client.gin;

import com.appspot.mdisample.client.module.AppPresenter;
import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.home.HomePresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;


@GinModules( { DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {
	public ProxyFailureHandler getProxyFailureHandler();

	public PlaceManager getPlaceManager();

	public EventBus getEventBus();

	public DispatchAsync getDispatcher();


	public Provider<AppPresenter> getAppPresenter();

	public Provider<ContentPresenter> getContentPresenter();

	public Provider<HomePresenter> getHomePresenter();

}