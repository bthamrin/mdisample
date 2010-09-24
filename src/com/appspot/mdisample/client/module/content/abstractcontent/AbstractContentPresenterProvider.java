package com.appspot.mdisample.client.module.content.abstractcontent;

import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentProxy;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentView;
import com.appspot.mdisample.client.module.content.list.ListPresenter;
import com.appspot.mdisample.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.EventBus;


public class AbstractContentPresenterProvider<P extends AbstractContentPresenter, V extends AbstractContentView>
		implements Provider<AbstractContentPresenter> {

	private AbstractContentPresenter presenter;
	private EventBus eventBus;
	private Provider<V> view;
	private AbstractContentProxy<P> proxy;
	private String name;

	private Class<P> presenterClass;


	@Inject
	public AbstractContentPresenterProvider(EventBus eventBus, Provider<V> view) {
		this.eventBus = eventBus;
		this.view = view;

	}


	@Override
	public AbstractContentPresenter get() {
		assert proxy != null : "You must call setProxy first";
		assert presenterClass != null : "You must call setPresenterClass() first";
		if (presenter == null) {

			if (presenterClass == ListPresenter.class)
				presenter = new ListPresenter(eventBus, view.get(), proxy, name);
			/*
			else if(presenterClass == DetailPresenter.class)
				presenter = new DetailPresenter(eventBus, view.get(), proxy, name);
			 */
		}
		return presenter;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameToken() {
		assert presenterClass != null : "You must call setPresenterClass() first";
		String nameToken = null;
		if (presenterClass == ListPresenter.class)
			nameToken = NameTokens.list + "=" + name;
		
		return nameToken;
	}

	public void setProxy(AbstractContentProxy<P> proxy) {
		this.proxy = proxy;
	}

	public void setPresenterClass(Class<P> presenterClass) {
		this.presenterClass = presenterClass;
	}


}
