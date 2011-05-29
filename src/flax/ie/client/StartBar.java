package flax.ie.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

public class StartBar {
	
	private Desktop desktopRef;
	MenuBar startMenu;
	
	public StartBar(final Desktop desktopRef)
	{

		this.desktopRef = desktopRef;
		
		Command cmdAddApp = new Command() {
			   public void execute() {
				   desktopRef.addApp();
			   }
			};
			
		Command startgedit = new Command() {
				   public void execute() {
					   desktopRef.addApp(new gedit(desktopRef, "gedit"));
				   }
				};
			 
	    Command emptyApp = new Command() {
				   public void execute() {
					   desktopRef.addApp();
				   }
				 };
		
		startMenu = new MenuBar();
		
		startMenu.setAnimationEnabled(true);
		startMenu.setAutoOpen(true);
		
		MenuBar menu2 = new MenuBar(true);
		menu2.addItem("Stock Pro",cmdAddApp);
		menu2.addItem("Empty App",emptyApp );

		menu2.addItem("gedit",startgedit);

		
		startMenu.addItem("Start", menu2);
		startMenu.addItem("System",cmdAddApp);
		
		startMenu.setSize("100%", "20px");
		
		startMenu.addSeparator();
		
	}

	public Widget getWidget() {
		return startMenu;
	}
	


}
