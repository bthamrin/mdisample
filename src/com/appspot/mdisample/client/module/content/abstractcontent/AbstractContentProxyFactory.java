package com.appspot.mdisample.client.module.content.abstractcontent;

import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentProxy;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentView;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;


/**
 * @author jp
 */

public class AbstractContentProxyFactory<P extends AbstractContentPresenter, V extends AbstractContentView> {

	private Provider<AbstractContentPresenterProvider<P, V>> provider;
	private ProxyFailureHandler failureHandler;
	private PlaceManager placeManager;
	private EventBus eventBus;
	private Class<P> presenterClass;


	@Inject
	public AbstractContentProxyFactory(Provider<AbstractContentPresenterProvider<P, V>> provider,
			ProxyFailureHandler failureHandler, PlaceManager placeManager, EventBus eventBus, Class<P> presenterClass) {
		this.provider = provider;
		this.failureHandler = failureHandler;
		this.placeManager = placeManager;
		this.eventBus = eventBus;
		this.presenterClass = presenterClass;
	}


	public AbstractContentProxy<P> create(String name, int tabGroup) {
		AbstractContentPresenterProvider<P, V> presenter = provider.get();
		presenter.setName(name);
		presenter.setPresenterClass(presenterClass);
		presenter.setTabGroup(tabGroup);
		AbstractContentProxyImpl<P, V> proxy = new AbstractContentProxyImpl<P, V>(presenter, presenter.getNameToken());
		proxy.bind(failureHandler, placeManager, eventBus);
		return proxy;
	}

}
