package com.appspot.mdisample.client.module.content.abstractcontent;

import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabElement;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public abstract class AbstractContentPresenter
		extends
		Presenter<AbstractContentPresenter.AbstractContentView, AbstractContentPresenter.AbstractContentProxy<AbstractContentPresenter>>
		implements NavigationTabElement {

	@ProxyStandard
	public abstract interface AbstractContentProxy<P extends AbstractContentPresenter> extends
			ProxyPlace<AbstractContentPresenter> {

	}


	public abstract interface AbstractContentView extends View {

	}


	public AbstractContentPresenter(EventBus eventBus, AbstractContentView view,
			AbstractContentProxy<AbstractContentPresenter> proxy, String name) {
		super(eventBus, view, proxy);

	}


	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, ContentPresenter.SLOT_content, this);

	}
}
