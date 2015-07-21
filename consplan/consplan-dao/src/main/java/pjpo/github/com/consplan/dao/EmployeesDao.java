package pjpo.github.com.consplan.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pjpo.github.com.consplan.model.Employee;

public class EmployeesDao {

	private final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("consplanunit");
			
	public Collection<Employee> getEmployees() {
		final EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> from = criteriaQuery.from(Employee.class);
		
		CriteriaQuery<Employee> select = criteriaQuery.select(from);
		TypedQuery<Employee> typedQuery = em.createQuery(select);
		List<Employee> resultlist = typedQuery.getResultList();

		em.getTransaction().commit();
		
		em.close();
		
		return resultlist;
	}

	public Employee getById(final Long employeeId) {
		
		EntityManager em = null;
		Employee result = null;
		
		try {
			em = factory.createEntityManager();

			em.getTransaction().begin();

			result = em.find(Employee.class, employeeId);
		
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em != null) {
				em.close();
			}
		}
		
		return result;
	}
	
	public void deleteEmployee(final Long employeeId) {

		EntityManager em = null;
	      
		try {
			em = factory.createEntityManager();

			em.getTransaction().begin();
			
			Employee employee = em.find(Employee.class, employeeId);
			em.remove(employee);

			em.getTransaction().commit();
	            
	        } catch (Exception e){
	        	if (em != null) {
	        		em.close();
	        	}
	        }
	}
	
	public void updateEmployee(final Employee employee) {

		EntityManager em = null;
	      
		try {
			em = factory.createEntityManager();

			em.getTransaction().begin();
			
			em.merge(employee);

			em.getTransaction().commit();
	            
	        } catch (Exception e){
	        	if (em != null) {
	        		em.close();
	        	}
	        }
	}
	
}
