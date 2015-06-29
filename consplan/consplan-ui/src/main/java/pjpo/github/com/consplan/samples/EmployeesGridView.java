package pjpo.github.com.consplan.samples;

import java.util.Locale;

import pjpo.github.com.test.samples.backend.data.Product;
import pjpo.github.com.test.samples.crud.CollectionToStringConverter;
import pjpo.github.com.test.samples.crud.EuroConverter;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.CellReference;
import com.vaadin.ui.Grid.CellStyleGenerator;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.HtmlRenderer;

@SuppressWarnings("serial")
public class EmployeesGridView extends Grid {

    public EmployeesGridView() {
        setSizeFull();

        setSelectionMode(SelectionMode.SINGLE);

        BeanItemContainer<Product> container = new BeanItemContainer<Product>(
                Product.class);
        setContainerDataSource(container);
        setColumnOrder("id", "productName", "price", "availability",
                "stockCount", "category");

        // Show empty stock as "-"
        getColumn("stockCount").setConverter(new StringToIntegerConverter() {
            @Override
            public String convertToPresentation(Integer value,
                    java.lang.Class<? extends String> targetType, Locale locale)
                    throws Converter.ConversionException {
                if (value == 0) {
                    return "-";
                }

                return super.convertToPresentation(value, targetType, locale);
            };
        });

        // Add an traffic light icon in front of availability
        getColumn("availability").setConverter(availabilityConverter)
                .setRenderer(new HtmlRenderer());

        // Add " €" automatically after price
        getColumn("price").setConverter(new EuroConverter());

        // Show categories as a comma separated list
        getColumn("category").setConverter(new CollectionToStringConverter());

        // Align columns using a style generator and theme rule until #15438
        setCellStyleGenerator(new CellStyleGenerator() {

            @Override
            public String getStyle(CellReference cellReference) {
                if (cellReference.getPropertyId().equals("price")
                        || cellReference.getPropertyId().equals("stockCount")) {
                    return "align-right";
                }
                return null;
            }
        });
    }

	
}
