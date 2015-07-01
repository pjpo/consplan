package pjpo.github.com.consplan.samples;

import pjpo.github.com.consplan.ConsPlan;
import pjpo.github.com.consplan.dao.EmployeesDao;

import com.github.pjpo.consplan.library.model.Employee;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;

public class EmployeesSummaryLogic {

    private EmployeesSummaryView view;

    private Employee selectedEmployee;
    
    public EmployeesSummaryLogic(EmployeesSummaryView view) {
        this.view = view;
    }

    public void init() {
        setEmployeeEdited(null);

        view.showEmployees(new EmployeesDao().getEmployees());
    }

    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
        view.editProduct(null);
    }

    /**
     * Update the fragment without causing navigator to change view
     */
    private void setFragmentParameter(String productId) {
        String fragmentParameter;
        if (productId == null || productId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = productId;
        }

        Page page = ConsPlan.get().getPage();
        page.setUriFragment("!" + EmployeesSummaryView.NAME + "/"
                + fragmentParameter, false);
    }

    public void enter(ViewChangeEvent event) {
    	// Do nothing
    }

    private Product findProduct(int productId) {
        return DataService.get().getProductById(productId);
    }

    public void saveProduct(Product product) {
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") updated");
        view.clearSelection();
        view.editProduct(null);
        view.refreshProduct(product);
        setFragmentParameter("");
    }

    public void deleteProduct(Product product) {
        DataService.get().deleteProduct(product.getId());
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") removed");

        view.clearSelection();
        view.editProduct(null);
        view.removeProduct(product);
        setFragmentParameter("");
    }

    public void editProduct(Product product) {
        if (product == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(product.getId() + "");
        }
        view.editProduct(product);
    }

    public void newProduct() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editProduct(new Product());
    }

    public void rowSelected(Product product) {
        if (MyUI.get().getAccessControl().isUserInRole("admin")) {
            view.editProduct(product);
        }
    }

}
