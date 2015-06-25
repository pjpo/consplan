package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class MainScreen extends HorizontalLayout {

		public MainScreen(ConsPlan ui) {

			setStyleName("main-screen");
			
			// Container for the content of views
			final CssLayout viewContainer = new CssLayout();
	        viewContainer.setSizeFull();
	        
	        // Navigator between views
	        final Navigator navigator = new Navigator(ui, viewContainer);

	        // Views
	        navigator.addView(MainView.NAME, new MainView());
	        navigator.addView(CountView.NAME, CountView.class);
	        
	        // Left Menu
	        final Menu menu = new Menu(navigator);
	        addComponent(menu);

	        addComponent(menu);
	        addComponent(viewContainer);

	        setExpandRatio(viewContainer, 1);
	        setSizeFull();
		}
	
}
