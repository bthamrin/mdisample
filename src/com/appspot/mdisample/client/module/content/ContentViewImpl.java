package com.appspot.mdisample.client.module.content;

import com.appspot.mdisample.client.module.content.ContentPresenter.ContentView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public class ContentViewImpl extends ViewImpl implements ContentView {

	private static ContentViewUiBinder uiBinder = GWT.create(ContentViewUiBinder.class);


	interface ContentViewUiBinder extends UiBinder<Widget, ContentViewImpl> {
	}


	public final Widget widget;

	@UiField
	SimplePanel navigationPanel;
	@UiField
	SimplePanel contentPanel;


	@Inject
	public ContentViewImpl() {
		widget = uiBinder.createAndBindUi(this);
	}


	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == ContentPresenter.SLOT_content) {
			contentPanel.clear();
			contentPanel.add(content);
		}
		else if (slot == ContentPresenter.SLOT_navigation) {
			navigationPanel.clear();
			navigationPanel.add(content);
		}
		else {
			super.setInSlot(slot, content);
		}
	}
}
