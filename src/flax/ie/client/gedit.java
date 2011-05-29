package flax.ie.client;

import com.google.gwt.user.client.ui.RichTextArea;

public class gedit extends App {

	RichTextArea editor;
	
	public gedit(Desktop refToDesktop, String title) {
		super(refToDesktop, title);
		
		editor = new RichTextArea();
		
		this.addWidget(editor);
	}

}
