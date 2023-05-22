package Users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import alakeel.restaurant.Meal;
@Path("/Userlass")

public class UserClass {
	EntityManager entityManager;
    public List<Meal> findAll() {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT m FROM MealModel m", Meal.class);
        return query.getResultList();
    }
	@PUT
    @Path("/findbyid")
    public Meal findById(int id) {
        return entityManager.find(Meal.class, id);
    }
  
    @POST
    @Path("/save")
    public void save(Meal meal) {
        entityManager.persist(meal);
    }
    @PUT
    @Path("/update")
    public void update(Meal meal) {
        entityManager.merge(meal);
    }
    @DELETE
    @Path("/delete")
    public void delete(Meal meal) 
    {
    	entityManager.remove(meal);
    }
}
