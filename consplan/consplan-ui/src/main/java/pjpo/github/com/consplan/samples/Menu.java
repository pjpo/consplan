package pjpo.github.com.consplan.samples;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class Menu extends CssLayout {

	public Menu(Navigator navigator) {
		// This is the menu root
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
				
		// Menu items layout in which menu items are added
		CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName(ValoTheme.MENU_PART);
		
		// First item
        Button butt1 = new Button("Main");
        butt1.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        butt1.addClickListener((event) -> navigator.navigateTo(MainView.NAME));
        menuItemsLayout.addComponent(butt1);

        // Second item
        Button butt2 = new Button("Count");
        butt2.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        butt2.addClickListener((event) -> navigator.navigateTo(CountView.NAME));
        menuItemsLayout.addComponent(butt2);
        
        addComponent(menuItemsLayout);
	
	}
	
}
