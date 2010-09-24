package com.appspot.mdisample.client.module.content.navigation;

import com.appspot.mdisample.client.module.content.navigation.CloseTabEvent.CloseTabHandler;
import com.appspot.mdisample.client.module.content.navigation.CloseTabEvent.HasCloseTabHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TabElement extends Composite implements HasCloseTabHandlers {
  private static Binder uiBinder = GWT.create(Binder.class);
  interface Binder extends UiBinder<Widget, TabElement> {
  }

  @UiField
  Label nameLabel;
  @UiField
  Button closeButton;
  
  public TabElement(String name, boolean isClosable) {
    initWidget(uiBinder.createAndBindUi(this));
    
    nameLabel.setText(name);
    closeButton.setVisible(isClosable);
  }

  @UiHandler("closeButton")
  void onCloseButtonClicked(ClickEvent event) {
    CloseTabEvent.fire(this);
  }

  @Override
  public HandlerRegistration addCloseTabHandler(CloseTabHandler handler) {
    return addHandler(handler, CloseTabEvent.getType());
  }
}
