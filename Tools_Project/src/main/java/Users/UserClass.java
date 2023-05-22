package Users;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import alakeel.restaurant.Meal;
@Path("/Userlass")

public class UserClass {
	
	EntityManager entityManager;
    @GET
    @Path("/finalall")
    public List<Meal> findAll() {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT m FROM MealModel m", Meal.class);
        return query.getResultList();
    }

    @GET
    @Path("/findbyid/{id}")
    public Meal findById(@PathParam("id") int id) {
        return entityManager.find(Meal.class, id);
    }
  
    @POST
    @Path("/create/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("id") int id,Meal meal) {
        entityManager.persist(meal);
    }
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Meal meal) {
        entityManager.merge(meal);
    }
    @DELETE
    @Path("/delete/{id}")
    public void delete(@PathParam("id") int id,Meal meal) {
		entityManager.remove(meal);
    }
}
