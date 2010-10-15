package com.appspot.mdisample.client.module;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;


public class AppPresenter extends Presenter<AppPresenter.AppView, AppPresenter.AppProxy> {

	public interface AppView extends View {
	}


	@ProxyStandard
	public interface AppProxy extends Proxy<AppPresenter> {
	}


	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_content = new Type<RevealContentHandler<?>>();


	@Inject
	public AppPresenter(final EventBus eventBus, final AppView view, final AppProxy proxy) {
		super(eventBus, view, proxy);

	}


	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

}
