package Users;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import alakeel.customer.CustomerOwner;
import alakeel.restaurant.Restaurant;
import alakeel.restaurant.RestaurantOwner;
import alakeel.runner.Runner;

public class AuthenticationService {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;
    @RolesAllowed({"RestaurantOwner"})

    public boolean signUpRestaurantOwner(String username, int pass) {
    	RestaurantOwner owner = new RestaurantOwner() ;
    	owner.setUserName(username);
    	owner.setpass(pass);
    	em.persist(owner);
        return true;
    }
    @RolesAllowed({"CustomerOwner"})

    public boolean signUpCustomer(String username, int pass) {
    	CustomerOwner customer = new CustomerOwner();
    	customer.setUserName(username);
    	customer.setpass(pass);
    	em.persist(customer);
    	return true;
    }
 @RolesAllowed({"runner"})
    public boolean signUpRunner(String username, int pass) {
        Runner runner=new Runner() ;
        runner.setpass(pass);
        runner.setpass(pass);
        em.persist(runner);
        return true;
    }
 @RolesAllowed({"RestaurantOwner"})

    public boolean loginRestaurantOwner(String username, int pass) {
        RestaurantOwner owner = em.createQuery("SELECT o FROM RestaurantOwner o WHERE o.username = :username", RestaurantOwner.class)
                .setParameter("username", username)
                .getSingleResult();
        return owner != null && owner 
    }
    @RolesAllowed({"CustomerOwner"})

    public boolean loginCustomer(String username, int pass) {
    	CustomerOwner customer = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username", CustomerOwner.class)
                .setParameter("username", username)
                .getSingleResult();
        return customer != null && customer.getpass().
    }
    @RolesAllowed({"runner"})

    public boolean loginRunner(String username, int pass) {
        Runner runner = em.createQuery("SELECT r FROM Runner r WHERE r.username = :username", Runner.class)
                .setParameter("username", username)
                .getSingleResult();
        return( runner != null && runner.getpass().equals(pass));

    }
}
