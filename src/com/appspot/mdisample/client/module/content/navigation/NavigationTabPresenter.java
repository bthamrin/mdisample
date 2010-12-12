package com.appspot.mdisample.client.module.content.navigation;

import java.util.LinkedList;
import java.util.List;

import com.appspot.mdisample.client.event.CloseTabEvent;
import com.appspot.mdisample.client.event.NavigationTabEvent;
import com.appspot.mdisample.client.event.NewTabEvent;
import com.appspot.mdisample.client.event.CloseTabEvent.CloseTabHandler;
import com.appspot.mdisample.client.event.NavigationTabEvent.NavigationTabHandler;
import com.appspot.mdisample.client.event.NewTabEvent.NewTabEventHandler;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenterProvider;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentProxyFactory;
import com.appspot.mdisample.client.module.content.abstractcontent.AbstractContentPresenter.AbstractContentProxy;
import com.appspot.mdisample.client.module.content.detail.DetailPresenter;
import com.appspot.mdisample.client.module.content.detail.DetailViewImpl;
import com.appspot.mdisample.client.module.content.list.ListPresenter;
import com.appspot.mdisample.client.module.content.list.ListViewImpl;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabView.MyControls;
import com.appspot.mdisample.client.place.NameTokens;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
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

		void insertTab(Widget tabElement, int index);

		void removeTab(int index);

		void selectTab(int index);
	}


	private List<NavigationTabElement> elements = new LinkedList<NavigationTabElement>();
	private List<NavigationTabElement> currentElements = new LinkedList<NavigationTabElement>();
	private NavigationTabElement currentElement = null;

	private AbstractContentProxyFactory<ListPresenter, ListViewImpl> listFactory;
	private AbstractContentProxyFactory<DetailPresenter, DetailViewImpl> detailFactory;
	private final PlaceManager placeManager;


	@Inject
	public NavigationTabPresenter(EventBus eventBus, MyView view,
			Provider<AbstractContentPresenterProvider<ListPresenter, ListViewImpl>> listProvider,
			Provider<AbstractContentPresenterProvider<DetailPresenter, DetailViewImpl>> detailProvider,
			PlaceManager placeManager, ProxyFailureHandler failureHandler) {
		super(eventBus, view);

		getView().setControls(this);
		addRegisteredHandler(NavigationTabEvent.getType(), this);

		this.listFactory = new AbstractContentProxyFactory<ListPresenter, ListViewImpl>(listProvider, failureHandler,
				placeManager, eventBus, ListPresenter.class);
		this.detailFactory = new AbstractContentProxyFactory<DetailPresenter, DetailViewImpl>(detailProvider,
				failureHandler, placeManager, eventBus, DetailPresenter.class);
		this.placeManager = placeManager;

	}


	@Override
	protected void onBind() {
		super.onBind();

		addRegisteredHandler(NewTabEvent.getType(), new NewTabEventHandler() {

			@Override
			public void onNewTab(NewTabEvent event) {
				onNewRequest(event.getToken(), event.getParameter(), false);
			}
		});
	}


	@Override
	public void onCloseTab(NavigationTabElement element) {
		int index = currentElements.indexOf(element);
		getView().removeTab(index);
		currentElements.remove(index);
		if (index >= currentElements.size()) {
			index = currentElements.size() - 1;
		}
		onTabSelected(index);
	}

	@Override
	public void onRevealTab(NavigationTabElement element) {
		if (!elements.contains(element)) elements.add(element);

		if (!currentElements.contains(element)) {
			int index = -1;
			for (int i = 0; i < currentElements.size(); i++) {
				if (currentElements.get(i).getTabGroup() == element.getTabGroup())
					index = currentElements.indexOf(currentElements.get(i));
			}

			if (index > -1) {
				getView().insertTab(createTab(element), index);
				currentElements.remove(index);
				currentElements.add(index, element);
			}
			else {
				getView().createTab(createTab(element));
				currentElements.add(element);
			}

		}

		currentElement = element;

		getView().selectTab(currentElements.indexOf(element));
	}


	@Override
	public void onNewRequest(String token, String parameter, boolean inNewTab) {
		int tabGroup;
		if (currentElement != null) {
			if (currentElement.isReusable() && !inNewTab) {
				tabGroup = currentElement.getTabGroup();
			}
			else {
				tabGroup = 0;
				for (int i = 0; i < elements.size(); i++)
					if (elements.get(i).getTabGroup() > tabGroup) tabGroup = elements.get(i).getTabGroup();
				tabGroup++;
			}
		}
		else {
			tabGroup = 0;
		}

		AbstractContentProxy<?> proxy = null;
		if (token.equalsIgnoreCase(NameTokens.list + "=")) {
			proxy = listFactory.create(parameter, tabGroup);
		}
		else if (token.equalsIgnoreCase(NameTokens.detail + "=")) {
			proxy = detailFactory.create(parameter, tabGroup);
		}

		if (proxy != null) {
			final String nt = proxy.getNameToken();
			DeferredCommand.addCommand(new Command() {
				@Override
				public void execute() {
					placeManager.revealPlace(new PlaceRequest(nt));
				}
			});
		}
	}

	@Override
	public void onTabSelected(int index) {
		placeManager.revealPlace(new PlaceRequest(currentElements.get(index).getToken()));
	}

	private Widget createTab(final NavigationTabElement element) {
		TabElement tabElement = new TabElement(element.getName(), element.isClosable());

		tabElement.addCloseTabHandler(new CloseTabHandler() {
			@Override
			public void onCloseTab(CloseTabEvent event) {
				onTabClosed(currentElements.indexOf(element));
			}
		});

		return tabElement;
	}

	private void onTabClosed(int index) {
		NavigationTabEvent.fireClose(this, currentElements.get(index));
	}


}
