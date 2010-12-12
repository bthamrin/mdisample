package com.appspot.mdisample.client.module.content.detail;

import java.util.Random;

import com.appspot.mdisample.client.place.NameTokens;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


/**
 * @author jp
 */

public class DetailViewImpl extends ViewImpl implements DetailPresenter.DetailView {

	private static DetailViewImplUiBinder uiBinder = GWT.create(DetailViewImplUiBinder.class);

	interface DetailViewImplUiBinder extends UiBinder<Widget, DetailViewImpl> {
	}


	public final Widget widget;

	@UiField
	Label title;
	@UiField
	VerticalPanel panel;


	@Inject
	public DetailViewImpl() {
		widget = uiBinder.createAndBindUi(this);

		Random r = new Random();
		for (int i = 0; i < 15; i++) {
			int nr = r.nextInt(9999);
			Hyperlink a = new Hyperlink("Detail-" + nr, NameTokens.detail + "=Detail-" + nr);
			panel.add(a);
		}
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
