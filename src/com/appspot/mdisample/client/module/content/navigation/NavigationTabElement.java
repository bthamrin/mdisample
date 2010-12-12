package com.appspot.mdisample.client.module.content.navigation;

/**
 * @author Olivier Monaco
 */

public interface NavigationTabElement {
	public String getName();

	public String getToken();

	public boolean isClosable();
	
	public boolean isReusable();
	
	public int getTabGroup();
	
	public void setTabGroup(int tabGroup);

}
