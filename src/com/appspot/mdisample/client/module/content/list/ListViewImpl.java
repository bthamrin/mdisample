package com.appspot.mdisample.client.module.content.list;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public class ListViewImpl extends ViewImpl implements ListPresenter.ListView {

	private static ListViewImplUiBinder uiBinder = GWT.create(ListViewImplUiBinder.class);

	interface ListViewImplUiBinder extends UiBinder<Widget, ListViewImpl> {
	}


	public final Widget widget;


	@Inject
	public ListViewImpl() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

}
