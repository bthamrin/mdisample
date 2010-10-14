package com.appspot.mdisample.client.module.content.detail;

import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public class DetailPresenter extends AbstractContentPresenter {

	public interface DetailProxy extends AbstractContentPresenter.AbstractContentProxy<DetailPresenter> {
	}


	public interface DetailView extends AbstractContentPresenter.AbstractContentView {
		void setHeader(String text);
	}


	private String name;
	private DetailViewImpl view;


	public DetailPresenter(EventBus eventBus, AbstractContentView view, AbstractContentProxy proxy, String name,
			int tabGroup) {
		super(eventBus, view, proxy, name, tabGroup);

		this.name = name;

		// FIXME: Possible to avoid this?
		this.view = (DetailViewImpl) getView();

		this.view.setHeader(name);
	}


	@Override
	protected void onBind() {
		super.onBind();

	}

	@Override
	protected void onReveal() {
		super.onReveal();

	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, ContentPresenter.SLOT_content, this);
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getToken() {
		return getProxy().getNameToken();
	}

}