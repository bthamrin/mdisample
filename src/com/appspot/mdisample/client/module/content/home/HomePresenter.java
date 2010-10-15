package com.appspot.mdisample.client.module.content.home;

import com.appspot.mdisample.client.module.content.ContentPresenter;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabElement;
import com.appspot.mdisample.client.place.NameTokens;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;


/**
 * @author jp
 */

public class HomePresenter extends Presenter<HomePresenter.HomeView, HomePresenter.HomeProxy> implements
		NavigationTabElement {

	@ProxyStandard
	@NameToken(NameTokens.home)
	public interface HomeProxy extends ProxyPlace<HomePresenter> {
	}


	public interface HomeView extends View {

	}


	private int tabGroup;


	@Inject
	public HomePresenter(EventBus eventBus, HomeView view, HomeProxy proxy) {
		super(eventBus, view, proxy);

		tabGroup = 0;
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
		return "Home";
	}

	@Override
	public String getToken() {
		return getProxy().getNameToken();
	}

	@Override
	public boolean isClosable() {
		return false;
	}

	@Override
	public boolean isReusable() {
		return false;
	}

	@Override
	public int getTabGroup() {
		return tabGroup;
	}

	@Override
	public void setTabGroup(int tabGroup) {
		this.tabGroup = tabGroup;
	}

}
