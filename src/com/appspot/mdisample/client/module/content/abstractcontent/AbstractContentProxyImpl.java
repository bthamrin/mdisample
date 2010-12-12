package com.appspot.mdisample.client.module.content.abstractcontent;

import java.util.ArrayList;
import java.util.List;

import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentProxy;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentView;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.StandardProvider;
import com.gwtplatform.mvp.client.proxy.PlaceImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.ProxyImpl;
import com.gwtplatform.mvp.client.proxy.ProxyPlaceImpl;


/**
 * @author jp
 */

public class AbstractContentProxyImpl<P extends AbstractContentPresenter, V extends AbstractContentView> extends
		ProxyPlaceImpl<AbstractContentPresenter> implements AbstractContentProxy<P> {

	public static class WrappedProxy extends ProxyImpl<AbstractContentPresenter> {
		@Inject
		public WrappedProxy(Provider<AbstractContentPresenter> presenter) {
			this.presenter = new StandardProvider<AbstractContentPresenter>(presenter);
		}
	}


	private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();


	public AbstractContentProxyImpl(AbstractContentPresenterProvider<P, V> presenter, String nameToken) {
		presenter.setProxy(this);
		this.proxy = new WrappedProxy(presenter);
		place = new PlaceImpl(nameToken);
	}


	@Override
	protected void bind(ProxyFailureHandler failureHandler, PlaceManager placeManager, EventBus eventBus) {
		super.bind(failureHandler, placeManager, eventBus);
	}

	void unbind() {
		for (HandlerRegistration handler : handlers) {
			handler.removeHandler();
		}
	}

}
