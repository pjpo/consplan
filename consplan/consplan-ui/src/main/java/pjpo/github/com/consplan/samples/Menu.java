package pjpo.github.com.consplan.samples;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class Menu extends CssLayout {

	public Menu() {
		// This is the menu root
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
				
		// Menu items layout in which menu items are added
		CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName(ValoTheme.MENU_PART);
		
		// First item
        Button butt1 = new Button("Click1");
        butt1.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menuItemsLayout.addComponent(butt1);

        // Second item
        Button butt2 = new Button("Click2");
        butt1.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menuItemsLayout.addComponent(butt2);
        
        addComponent(menuItemsLayout);
	
	}
	
}
