package flax.ie.client;

import java.io.Console;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



import flax.ie.client.animations.slideInOut;

/**
 * Extending app will give you a standard app widget
 * @author ciaran
 *
 */
public class App extends FocusPanel {

	/**
	 * CSS styles classes
	 */
	private static final String RESIZE = "resize";
	private static final String APP_WINDOW = "appWindow";
	private static final String STYLE_HEADER_BAR = "headerBar";
	
	public enum state { IDLE, MOVE, RESIZE , MINIMIZE}	
	private state state;
	
	private Desktop desktopRef;
	private VerticalPanel appWindow;
	private HorizontalPanel headerBar;
	private Label resizeLabel,moveLabel, minLabel, closeLabel ;
	private MenuBar menuBar;
	
	private int width;
	private int height;
	
	public App(Desktop refToDesktop, String title)
	{
		
		width = height = 300;	
		
		desktopRef = refToDesktop;	
		desktopRef.setCurrentApp(this);
		
		setHeaderBar(title);
		
		this.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				setAsCurrentApp();
				
			}
		});
		
		this.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				event.preventDefault();
				state = state.IDLE;
				
			}
		});
		
		
		setupMenu();
		
		this.add(appWindow);
		addWidget(menuBar);
						
	}
	
	
	
	protected void setupMenu()
	{
		
		 Command cmd  = new Command() {
			   public void execute() {
				  
			   }
			 };
			 
	 MenuBar fileMenuDropDown = new MenuBar(true);
	 fileMenuDropDown.addItem("Open", cmd);
	 fileMenuDropDown.addItem("Save", cmd);
		
		menuBar = new MenuBar();
		menuBar.addItem("File", fileMenuDropDown);
		menuBar.setAnimationEnabled(true);
		menuBar.addSeparator();
		menuBar.addItem("Edit", cmd);
		menuBar.addSeparator();		
	}
	
	
	/**
	 * Setup the header bar
	 * @param title 
	 */
	protected void setHeaderBar(String title){
		
		

		headerBar = new HorizontalPanel();
		headerBar.setSize("100%", "0px");
		headerBar.setStylePrimaryName(STYLE_HEADER_BAR);
		
		appWindow = new VerticalPanel();
		appWindow.setSize( width + "px", height + "px");
		appWindow.setStyleName(APP_WINDOW);
		
		
		/**
		 * Labels
		 */		
		moveLabel = new Label(title);
		resizeLabel = new Label();
		resizeLabel.setSize("15px", "15px");
		resizeLabel.setStyleName(RESIZE);
		
		Label closeLabel = new Label("X");
		
		
		Label minLabel = new Label("¬");
		
		minLabel.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				minimize();
				
			}
		});
		
		closeLabel.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				exit();			
			}
		});
			
		moveLabel.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				state = state.MOVE;
				
			}
		});
		
		moveLabel.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				event.preventDefault();
				state = state.IDLE;
				
			}
		});
				
		resizeLabel.addMouseDownHandler(new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				event.preventDefault();
				
				
				state = state.RESIZE;
				
			}
		});
		
		resizeLabel.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				event.preventDefault();
				state = state.IDLE;
				
			}
		});
			
		
		headerBar.add(moveLabel);
		headerBar.add(minLabel);
		headerBar.add(closeLabel);
		headerBar.setCellWidth(moveLabel, "100%");		
		appWindow.add(headerBar);
		appWindow.setCellHeight(headerBar, "20px");		
	
	}
	
	
	/**
	 * Adds widgets to the vertical panel which is the main
	 * body of the app
	 * @param w
	 */
	protected void addWidget(Widget w)
	{
		appWindow.add(w);
		
		int numberOfWidgets = appWindow.getWidgetCount();
		appWindow.insert(resizeLabel, numberOfWidgets);
		appWindow.setCellHeight(resizeLabel, "1%");
	}
	
	
	/**
	 * Sets this app as the current app in focus on desktop
	 */
	private void setAsCurrentApp()
	{
		desktopRef.setCurrentApp(this);	
	}
	
	/**
	 * Removes this app from desktop
	 */
	protected void exit(){
		desktopRef.remove(this);
	}
	
	protected void minimize() {
		
		 slideInOut animation = new slideInOut(this.getElement());
		
		desktopRef.minimize(this);
		if(state != state.MINIMIZE)
		{	
			state = state.MINIMIZE;
			//this.setVisible(false);
			animation.scrollTo(0, 1000, 500);
		}
		else
		{	
			state = state.IDLE;
			//this.setVisible(true);
			animation.scrollTo((desktopRef.getWidth()/2)-width, (desktopRef.getHeight()/2)-height, 500);
			appWindow.insert(headerBar,0);
			appWindow.setCellHeight(headerBar, "20px");
		}
						
	}
	
	/**
	 * Gets the current state of the window
	 * Eg: Moving, resizing, idle
	 * @return
	 */
	protected state getStatus()
	{
		return state;	
	}

	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		
		if(width >= 150)
		{
			this.width = width;
			appWindow.setSize( this.width + "px", this.height + "px");
		}
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		
		if(height >= 150)
		{
			this.height = height;
			appWindow.setSize( this.width + "px", this.height + "px");
		}
	}



	public Widget getHeader() {
		return headerBar;
	}

	
	
}
