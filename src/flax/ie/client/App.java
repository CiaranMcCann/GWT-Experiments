package flax.ie.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class App extends FocusPanel{

	private boolean move;
	private Desktop desktopRef;
	private VerticalPanel topBar;
	
	public App(Desktop refToDesktop)
	{
		/**
		 * Lables
		 */
		
		Label moveLabel = new Label("Move");
		moveLabel.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				move = true;
				
			}
		});
		
		moveLabel.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				event.preventDefault();
				move = false;
				
			}
		});
		
		
		/**
		 * Setup the header bar
		 */
		topBar = new VerticalPanel();
		topBar.setSize("100%", "20px");
		topBar.addStyleName("app-topBar");
		topBar.add(moveLabel);
		this.add(topBar);
		//this.add(resizeLabel);
		
		desktopRef = refToDesktop;	
		desktopRef.setCurrentApp(this);
		this.setStyleName("app");
		this.setSize("300px", "300px");
		this.addMouseDownHandler( new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				//move = true;
				event.preventDefault();
				setAsCurrentApp();
				
			}
		});
		
		this.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				// TODO Auto-generated method stub
				//move = false;
				event.preventDefault();
			}
		});
		
		
	}
	
	private void setAsCurrentApp()
	{
		desktopRef.setCurrentApp(this);	
	}
	
	public boolean getMoveStatus()
	{
		return move;
	
	}
	
}
