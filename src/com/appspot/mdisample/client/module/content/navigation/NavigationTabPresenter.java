package com.appspot.mdisample.client.module.content.navigation;

import java.util.LinkedList;
import java.util.List;

import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenterProvider;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentProxyFactory;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentProxy;
import com.appspot.mdisample.client.module.content.list.ListPresenter;
import com.appspot.mdisample.client.module.content.list.ListViewImpl;
import com.appspot.mdisample.client.module.content.navigation.CloseTabEvent.CloseTabHandler;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabEvent.NavigationTabHandler;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabView.MyControls;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.HasControls;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;


/**
 * @author Olivier Monaco
 * @author Christian Goudreau
 */
public class NavigationTabPresenter extends PresenterWidget<NavigationTabPresenter.MyView> implements
		NavigationTabHandler, MyControls {
	public interface MyView extends View, HasControls<MyControls> {
		void createTab(Widget tabElement);

		void removeTab(int index);

		void selectTab(int index);
	}

	private List<NavigationTabElement> elements = new LinkedList<NavigationTabElement>();
	//private EditorProxyFactory editorFactory;
	private AbstractContentProxyFactory<ListPresenter, ListViewImpl> listFactory;
	private final PlaceManager placeManager;

	@Inject
	public NavigationTabPresenter(EventBus eventBus, MyView view,
			Provider<AbstractContentPresenterProvider<ListPresenter, ListViewImpl>> listProvider,
			/*EditorProxyFactory editorFactory,*/PlaceManager placeManager, ProxyFailureHandler failureHandler) {
		super(eventBus, view);

		getView().setControls(this);
		addRegisteredHandler(NavigationTabEvent.getType(), this);
		//this.editorFactory = editorFactory;
		this.listFactory = new AbstractContentProxyFactory<ListPresenter, ListViewImpl>(listProvider, failureHandler,
				placeManager, eventBus, ListPresenter.class);
		this.placeManager = placeManager;
	}

	@Override
	public void onCloseTab(NavigationTabElement element) {
		int index = elements.indexOf(element);
		getView().removeTab(index);
		elements.remove(index);
		if (index >= elements.size()) {
			index = elements.size() - 1;
		}
		onTabSelected(index);
	}

	@Override
	public void onRevealTab(NavigationTabElement element) {
		if (!elements.contains(element)) {
			getView().createTab(createTab(element));
			elements.add(element);
		}
		getView().selectTab(elements.indexOf(element));
	}


	@Override
	public void onNewRequest(String name) {
		//EditorProxy proxy = editorFactory.create(name);
		AbstractContentProxy<ListPresenter> proxy = listFactory.create(name);
		placeManager.revealPlace(new PlaceRequest(proxy.getNameToken()));
	}

	@Override
	public void onTabSelected(int index) {
		placeManager.revealPlace(new PlaceRequest(elements.get(index).getToken()));
	}

	private Widget createTab(final NavigationTabElement element) {
		TabElement tabElement = new TabElement(element.getName(), element.isClosable());

		tabElement.addCloseTabHandler(new CloseTabHandler() {
			@Override
			public void onCloseTab(CloseTabEvent event) {
				onTabClosed(elements.indexOf(element));
			}
		});

		return tabElement;
	}

	private void onTabClosed(int index) {
		NavigationTabEvent.fireClose(this, elements.get(index));
	}
}
