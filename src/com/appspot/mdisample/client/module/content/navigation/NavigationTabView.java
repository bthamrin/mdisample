package com.appspot.mdisample.client.module.content.navigation;

import com.appspot.mdisample.client.module.content.navigation.NavigationTabPresenter.MyView;
import com.appspot.mdisample.client.place.NameTokens;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.Controls;
import com.gwtplatform.mvp.client.ViewWithControls;


/**
 * @author Christian Goudreau
 */
public class NavigationTabView extends ViewWithControls<NavigationTabView.MyControls> implements MyView {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, NavigationTabView> {
	}

	public interface MyControls extends Controls {
		void onNewRequest(String token, String parameter, boolean inNewTab);

		void onTabSelected(int intex);
	}

	private final Widget widget;
	private int counter = 0;

	@UiField
	Button newButton;
	@UiField
	TabBar tabBar;

	public NavigationTabView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void createTab(Widget tabElement) {
		tabBar.addTab(tabElement);
	}

	@Override
	public void removeTab(int index) {
		tabBar.removeTab(index);
	}

	@Override
	public void selectTab(int index) {
		tabBar.selectTab(index);
	}

	@UiHandler("newButton")
	void onNewButtonClicked(ClickEvent event) {
		counter++;
		getControls().onNewRequest(NameTokens.list + "=", "List-" + counter, true);
	}

	@UiHandler("tabBar")
	void onTabBarSelection(SelectionEvent<Integer> event) {
		getControls().onTabSelected(event.getSelectedItem());
	}

	@Override
	public void insertTab(Widget tabElement, int index) {
		//int currentTab = tabBar.getSelectedTab();
		tabBar.insertTab(tabElement, index);
		tabBar.removeTab(index + 1);
		
	}
}
