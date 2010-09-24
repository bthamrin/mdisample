package com.appspot.mdisample.client.module.content.list;

import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


public class ListPresenter extends AbstractContentPresenter {

	public interface ListProxy extends AbstractContentPresenter.AbstractContentProxy<ListPresenter> {
	}


	public interface ListView extends AbstractContentPresenter.AbstractContentView {
	}


	private String name;


	public ListPresenter(EventBus eventBus, AbstractContentView view, AbstractContentProxy proxy, String name) {
		super(eventBus, view, proxy, name);
		
		this.name = name;
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

	@Override
	public boolean isClosable() {
		return true;
	}

}
