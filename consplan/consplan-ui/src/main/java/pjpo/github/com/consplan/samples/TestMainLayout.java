package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class TestMainLayout extends HorizontalLayout {

		public TestMainLayout(ConsPlan ui) {

			setStyleName("main-screen");
			
			// Container for the content of views
			final CssLayout viewContainer = new CssLayout();
	        viewContainer.setSizeFull();

	        // Left Menu
	        final Menu menu = new Menu();
	        addComponent(menu);
	        
	        // Navigator between views
	        final Navigator navigator = new Navigator(ui, viewContainer);

	        // Views
	        navigator.addView(MainView.NAME, new MainView());
	        navigator.addView(CountView.NAME, CountView.class);
	        
	        addComponent(menu);
	        addComponent(viewContainer);

	        setSizeFull();
		}
	
}
