package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class TestMainLayout extends HorizontalLayout {

		public TestMainLayout(ConsPlan ui) {

			setStyleName("main-screen");
	        
	        addComponent(new Label("Hello 1"));
	        addComponent(new Label("Hello 2"));
	        
	        setSizeFull();
		}
	
}
