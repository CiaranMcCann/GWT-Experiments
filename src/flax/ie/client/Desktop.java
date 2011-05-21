package flax.ie.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;

public class Desktop extends AbsolutePanel implements MouseMoveHandler,HasMouseMoveHandlers {

	private ArrayList<App> appList = new ArrayList<App>();
	private App currentApp;
	
	public Desktop() {
		super();
		this.setSize("1000px", "1000px");
		this.addMouseMoveHandler(this);	
		Button addApps = new Button("Add new window");
		
		addApps.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addApp();
				
			}
		});
		this.add(addApps,0,0);
	
		this.add(new App(this), 200,200);
		//this.add(new App(this), 0,0);
	}
	
	public void addApp()
	{
		this.add(new App(this), 20, 40);
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
			if(currentApp.getMoveStatus())
			{
				this.setWidgetPosition(currentApp, event.getClientX(), event.getClientY());
			}
			
		}
		
	}



	
}
