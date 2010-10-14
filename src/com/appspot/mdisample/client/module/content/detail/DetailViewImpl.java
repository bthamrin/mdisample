package com.appspot.mdisample.client.module.content.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public class DetailViewImpl extends ViewImpl implements DetailPresenter.DetailView {

	private static DetailViewImplUiBinder uiBinder = GWT.create(DetailViewImplUiBinder.class);

	interface DetailViewImplUiBinder extends UiBinder<Widget, DetailViewImpl> {
	}


	public final Widget widget;
	
	@UiField
	Label title;


	@Inject
	public DetailViewImpl() {
		widget = uiBinder.createAndBindUi(this);
	}


	@Override
	public Widget asWidget() {
		return widget;
	}


	@Override
	public void setHeader(String text) {
		this.title.setText(text.toUpperCase());
	}

}
