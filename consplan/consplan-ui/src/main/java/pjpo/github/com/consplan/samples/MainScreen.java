package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import pjpo.github.com.consplan.ConsPlan;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MainScreen extends HorizontalLayout {

		public MainScreen(ConsPlan ui) {

			// Container for the content of views
			final CssLayout viewContainer = new CssLayout();
	        viewContainer.setSizeFull();
	        
	        // Navigator between views
	        final Navigator navigator = new Navigator(ui, viewContainer);

	        // Views
	        navigator.addView("", new EmployeesSummaryView());
	        navigator.addView(EmployeesSummaryView.NAME, new EmployeesSummaryView());
	        navigator.addView(EmployeesView.NAME, new EmployeesView(navigator));
	        navigator.addView(CountView.NAME, CountView.class);
	        final IntervalDateTimeForm intervalDateTimeForm = new IntervalDateTimeForm(
	        		ResourceBundle.getBundle("pjpo.github.com.consplan.samples.IntervalDateTimeForm", UI.getCurrent().getLocale()));
	        intervalDateTimeForm.register("test",
	        		(parameters) -> {return new IntervalDateTime();},
	        		(exitStatus, interval) -> {});
	        navigator.addView(IntervalDateTimeForm.NAME, intervalDateTimeForm);
	        
	        // Left Menu
	        final Menu menu = new Menu(navigator, UI.getCurrent().getLocale());
	        addComponent(menu);

	        addComponent(menu);
	        addComponent(viewContainer);

	        // Sets Sizes of components
	        setExpandRatio(viewContainer, 1);
	        setSizeFull();
		}
	
}
