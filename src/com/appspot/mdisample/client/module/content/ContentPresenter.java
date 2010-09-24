package com.appspot.mdisample.client.module.content;

import com.appspot.mdisample.client.module.AppPresenter;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabElement;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabEvent;
import com.appspot.mdisample.client.module.content.navigation.NavigationTabPresenter;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;


public class ContentPresenter extends Presenter<ContentPresenter.ContentView, ContentPresenter.ContentProxy> {

	@ProxyStandard
	public interface ContentProxy extends Proxy<ContentPresenter> {
	}


	public interface ContentView extends View {
	}


	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_content = new Type<RevealContentHandler<?>>();
	public static final Object SLOT_navigation = new Object();

	//private AbstractContentProxyFactory<ListPresenter, ListViewImpl> listFactory;
	//private AbstractContentProxyFactory<DetailPresenter, DetailViewImpl> detailFactory;
	private NavigationTabPresenter navtab;

	@Inject
	public ContentPresenter(EventBus eventBus, ContentView view, ContentProxy proxy,
			ProxyFailureHandler failureHandler, PlaceManager placeManager,
			/*Provider<AbstractContentPresenterProvider<ListPresenter, ListViewImpl>> listProvider,*/
			/*Provider<AbstractContentPresenterProvider<DetailPresenter, DetailViewImpl>> detailProvider,*/
			Provider<NavigationTabPresenter> navigation) {
		super(eventBus, view, proxy);

		//this.listFactory = new AbstractContentProxyFactory<ListPresenter, ListViewImpl>(listProvider, failureHandler,
		//	placeManager, eventBus, ListPresenter.class);
		//this.detailFactory = new AbstractContentProxyFactory<DetailPresenter, DetailViewImpl>(detailProvider,
		//	failureHandler, placeManager, eventBus, ArtistPresenter.class);
		navtab = navigation.get();
	}


	@Override
	public void setInSlot(Object slot, final PresenterWidget<?> content) {
		super.setInSlot(slot, content);

		if ((slot == SLOT_content) && (content instanceof NavigationTabElement)) {
			DeferredCommand.addCommand(new Command() {
				@Override
				public void execute() {
					NavigationTabEvent.fireReveal(ContentPresenter.this, (NavigationTabElement) content);
				}
			});
		}
	}


	@Override
	protected void onBind() {
		super.onBind();

	}

	@Override
	protected void onReveal() {
		super.onReveal();

		setInSlot(SLOT_navigation, navtab);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, AppPresenter.SLOT_content, this);
	}

}
