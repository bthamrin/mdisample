package com.appspot.mdisample.client.module.content.home;

import com.appspot.mdisample.client.module.content.home.HomePresenter.HomeView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class HomeViewImpl extends Composite implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}


	public final Widget widget;


	public HomeViewImpl() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void addContent(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContent(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDrag() {

	}

	@Override
	public void removeContent(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromSlot(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		// TODO Auto-generated method stub

	}

}
