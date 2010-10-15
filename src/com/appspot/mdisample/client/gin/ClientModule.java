package com.appspot.mdisample.client.gin;

import com.appspot.mdisample.client.module.AppPresenter;
import com.appspot.mdisample.client.module.AppViewImpl;
import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.ContentViewImpl;
import com.appspot.mdisample.client.module.content.ContentPresenter.ContentView;
import com.appspot.mdisample.client.module.content.detail.DetailViewImpl;
import com.appspot.mdisample.client.module.content.detail.DetailPresenter.DetailView;
import com.appspot.mdisample.client.module.content.home.HomePresenter;
import com.appspot.mdisample.client.module.content.home.HomeViewImpl;
import com.appspot.mdisample.client.module.content.home.HomePresenter.HomeProxy;
import com.appspot.mdisample.client.module.content.list.ListViewImpl;
import com.appspot.mdisample.client.module.content.list.ListPresenter.ListView;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabPresenter;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabView;
import com.appspot.mdisample.client.place.AppPlaceManager;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultEventBus;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;


public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		// Singletons
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(AppPlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);

		bindPresenter(AppPresenter.class, AppPresenter.AppView.class, AppViewImpl.class, AppPresenter.AppProxy.class);
		bindPresenter(ContentPresenter.class, ContentView.class, ContentViewImpl.class,
				ContentPresenter.ContentProxy.class);
		bindPresenterWidget(NavigationTabPresenter.class, NavigationTabPresenter.MyView.class, NavigationTabView.class);
		bindPresenter(HomePresenter.class, HomePresenter.HomeView.class, HomeViewImpl.class, HomeProxy.class);

		bind(ListView.class).to(ListViewImpl.class);
		bind(DetailView.class).to(DetailViewImpl.class);

	}

}