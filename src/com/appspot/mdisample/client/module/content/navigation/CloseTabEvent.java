package com.appspot.mdisample.client.module.content.navigation;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author Christian Goudreau
 */
public class CloseTabEvent extends GwtEvent<CloseTabEvent.CloseTabHandler> {
  public interface CloseTabHandler extends EventHandler {
    void onCloseTab(CloseTabEvent event);
  }

  public interface HasCloseTabHandlers extends HasHandlers {
    HandlerRegistration addCloseTabHandler(CloseTabHandler handler);
  }

  private static Type<CloseTabHandler> TYPE = new Type<CloseTabHandler>();

  public static void fire(HasHandlers source) {
    if (TYPE != null) {
      source.fireEvent(new CloseTabEvent());
    }
  }

  public static Type<CloseTabHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<CloseTabHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CloseTabHandler handler) {
    handler.onCloseTab(this);
  }
}
