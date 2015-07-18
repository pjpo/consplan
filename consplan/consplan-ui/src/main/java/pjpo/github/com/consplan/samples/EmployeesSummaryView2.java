package pjpo.github.com.consplan.samples;

import java.util.ResourceBundle;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@DesignRoot
public class EmployeesSummaryView2 extends BaseView {

	/**
	 * String definition for this view
	 */
    public static final String NAME = "EmployeesSummary2";
    
	/**
	 *  Source of localized text
	 */
    private final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.EmployeesGrid", UI.getCurrent().getLocale());
	
    // Elements from the ui
    
    private EmployeesSummaryGrid2 grid;
    
    private Button create;
    
    private Button delete;
    
    public EmployeesSummaryView2(final Navigator navigator) {
    	// Builds the UI
		Design.read("EmployeesSummaryView.html", this);
		
		// Sets I18N text
		create.setCaption(resourceText.getString("CreateEmployee2"));
		delete.setCaption(resourceText.getString("DeleteEmployee2"));
		
		// Sets programmatically the necessary theme
		create.addStyleName(ValoTheme.BUTTON_PRIMARY);
		create.setIcon(FontAwesome.PLUS_CIRCLE);
		delete.addStyleName(ValoTheme.BUTTON_PRIMARY);
		delete.setIcon(FontAwesome.MINUS_CIRCLE);

		create.addClickListener((event) -> {
		});

		delete.addClickListener((event) -> {
		});

    }

	@Override
	public void enter(final ViewChangeEvent event, final UrlDecoded urlDecoded) {
		// In parameters, id of element selected or nothing if nothing selected
		final String selectedS = urlDecoded.getParameters().get("selected"); 
		Long selectedL = null;
		if (selectedS != null) {
			try {
				selectedL = Long.parseLong(selectedS);
			} catch (Exception e) {
				// Do nothing, consider nothing has been selected
			}
		}
		
		System.out.println("Selected = " + selectedL);
	}



}
