package pjpo.github.com.consplan.samples;

import java.util.Locale;
import java.util.ResourceBundle;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class Menu extends CssLayout {

	/**
	 * Definition of menu items and their links views
	 */
	private final static Object[][] menuElements = {
		{"Menu.Employees.Item", EmployeesSummaryView.NAME},
		{"Menu.CurrentEmployee.Item", CountView.NAME},
		{"Menu.FullPlanning.Item", IntervalDateTimeForm.NAME + "/origin=test"},
		{"Menu.PersonalPlanning.Item", EmployeesSummaryView2.NAME}
	};
	
	public Menu(Navigator navigator, Locale locale) {
		// This is the menu root
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
				
		// Menu items layout in which menu items are added
		final CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName(ValoTheme.MENU_PART);
        
		// Source of localized text
        final ResourceBundle resourceText = ResourceBundle.getBundle("pjpo.github.com.consplan.samples.Menu", locale);

        // Load each menu item
        for (final Object[] menuElement : menuElements) {
        	final Button button = newMenuButton((String) menuElement[0], resourceText, navigator, (String) menuElement[1]);
        	menuItemsLayout.addComponent(button);
        }

        // Adds all the items to the menu
        addComponent(menuItemsLayout);
	
	}
	
	/**
	 * Template to create the button with internationalized text with a defined target in
	 * the application menu
	 * @param captionId
	 * 				Id of the caption for the button in resource file
	 * @param captionSource
	 * 				Resource file
	 * @param navigator
	 * 				Vaadin navigator
	 * @param targetViewName
	 * 				Target view in Vaadin navigator
	 * @return
	 */
	private Button newMenuButton(
			final String captionId,
			final ResourceBundle captionSource,
			final Navigator navigator,
			final String targetViewName) {
		final Button button = new Button(captionSource.getString(captionId));
		button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		button.addClickListener((event) -> navigator.navigateTo(targetViewName));
		return button;
	}
	
}
