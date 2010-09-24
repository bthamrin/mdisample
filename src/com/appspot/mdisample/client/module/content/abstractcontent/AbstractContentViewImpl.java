package com.appspot.mdisample.client.module.content.abstractcontent;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public abstract class AbstractContentViewImpl extends ViewImpl implements AbstractContentPresenter.AbstractContentView {

	@Inject
	public AbstractContentViewImpl() {

	}


	@Override
	public Widget asWidget() {
		return new Button("hello");
	}


}