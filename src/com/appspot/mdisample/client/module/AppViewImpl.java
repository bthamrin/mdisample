package com.appspot.mdisample.client.module;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


/**
 * @author jp
 */

public class AppViewImpl extends ViewImpl implements AppPresenter.AppView {

	private static AppViewImplUiBinder uiBinder = GWT.create(AppViewImplUiBinder.class);


	interface AppViewImplUiBinder extends UiBinder<Widget, AppViewImpl> {
	}


	public final Widget widget;

	@UiField
	LayoutPanel contentContainer;


	@Inject
	public AppViewImpl() {
		widget = uiBinder.createAndBindUi(this);
	}


	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == AppPresenter.SLOT_content) {
			contentContainer.clear();
			contentContainer.add(content);
		}
		else {
			super.setInSlot(slot, content);
		}
	}

}
