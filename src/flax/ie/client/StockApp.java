package flax.ie.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.gargoylesoftware.htmlunit.AlertHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

public class StockApp extends App{

	
	private CellTable<Stock> table;
	private Button addStock;
	private Random rand;
	
	
	public StockApp(Desktop refToDesktop) {
		
		super(refToDesktop, "Stock App");
		
	    rand = new Random( 19580427 );
		table =  new CellTable<Stock>();
		
		
		TextColumn<Stock> nameColumn = new TextColumn<Stock>() {

			@Override
			public String getValue(Stock object) {
				return object.getName();
			}
		};
		
		TextColumn<Stock> valueColumn = new TextColumn<Stock>() {
			
			@Override
			public String getValue(Stock object) {
				return  Integer.toString(object.getValue());
			}
		};
			
		table.addColumn(nameColumn, "Name");
		table.addColumn(valueColumn, "Stock Value");
		
		
		ListDataProvider<Stock> dataProvider = new ListDataProvider<Stock>();
		dataProvider.addDataDisplay(table);
				
		 // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    final List<Stock> list = dataProvider.getList();
	    list.add(new Stock(200, "Google"));
	    list.add(new Stock(300, "IBM"));
	    list.add(new Stock(1000, "Apple"));
	    
	   
	    
	    

		addStock = new Button("Add Stock");
		addStock.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
					list.add( new Stock(rand.nextInt( 100 ), "New Stock") );			
			}
		});
		
		
		 ListHandler<Stock> columnSortHandler = new ListHandler<Stock>(list);
			    columnSortHandler.setComparator(nameColumn,
			        new Comparator<Stock>() {
			          public int compare(Stock o1, Stock o2) {
			            if (o1 == o2) {
			              return 0;
			            }

			            // Compare the name columns.
			            if (o1 != null) {
			              return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
			            }
			            return -1;
			          }


			        });
		
		table.addColumnSortHandler(columnSortHandler);
		table.getColumnSortList().push(nameColumn);
		
		this.addWidget(addStock);
		this.addWidget(table);
	   
	}

	
}
