package flax.ie.client;

import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

import flax.ie.client.App.state;

public class Desktop extends AbsolutePanel implements MouseMoveHandler, HasMouseMoveHandlers {

	private static final String START_BAR = "startBar";
	private static final String DESKTOP = "desktop";
	
	
	private ArrayList<App> appList = new ArrayList<App>();
	private App currentApp;
	private int width, height;
	private HorizontalPanel startbar;
	private StartBar menu;
		
	
	public Desktop() {
	
		super();
		menu = new StartBar(this);
		
		/**
		 * Set Deskop size and set style
		 */
		width = Window.getClientWidth();
		height = Window.getClientHeight();	
		this.setSize( width + "px", height + "px");		
		this.setStylePrimaryName(DESKTOP);
		

		/**
		 * Setup start bar and add it to the desktop
		 */
		startbar = new HorizontalPanel();
		startbar.setSize("100%", "50px");
		startbar.setStylePrimaryName(START_BAR);
		this.add(menu.getWidget(),0,0);
		this.add(startbar,0,height-50);
		
		
		
		this.addMouseMoveHandler(this);	
		
		/**
		 * Start bar buttons setup
		 */
		Button addApps = new Button("Add new window");		
		addApps.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addApp();
				
			}
		});
		
		/**
		 * Add buttons to start bar
		 */
		//startbar.add(addApps);
		
		addApp();

	}
	

	public void addApp()
	{	
			this.add(new StockApp(this), 100, 100);			
	}
	
	public void addApp(App app)
	{	
			this.add(app, 100, 100);			
	}
	
	public void minimize(App appTobeMinimized)
	{
		if(appTobeMinimized.getStatus() != state.MINIMIZE)
		{
		
			startbar.add(appTobeMinimized.getHeader());
			startbar.setCellWidth(appTobeMinimized.getHeader(), "20px");
		}
		
		if(appTobeMinimized.getStatus() == state.MINIMIZE)
		{
			startbar.remove(appTobeMinimized.getHeader());
			//startbar.setCellWidth(appTobeMinimized.getHeader(), "20px");
		}
		
		

	}

	public void setCurrentApp(App reference)
	{
		currentApp = reference;
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		
		event.preventDefault();
		if(currentApp != null)
		{
			if(currentApp.getStatus() == state.MOVE)
			{
				
				this.setWidgetPosition(currentApp, event.getClientX(), event.getClientY());
			}
			
			if(currentApp.getStatus() == state.RESIZE)
			{
								
				float amountMovedX = ( currentApp.getAbsoluteLeft()+currentApp.getWidth() ) - event.getClientX() - 10;	
				float amountMovedY = ( currentApp.getAbsoluteTop()+currentApp.getHeight() ) - event.getClientY() - 10;
				
				currentApp.setWidth((int)(currentApp.getWidth()-amountMovedX));
				currentApp.setHeight((int)(currentApp.getHeight()-amountMovedY));
						
			}
			
		}
		
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}



	
}
