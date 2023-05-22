package Users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import alakeel.restaurant.Meal;

public class UserClass {
	EntityManager entityManager;
	
   public Meal findById(int id) {
        return entityManager.find(Meal.class, id);
    }
   
    public List<Meal> findAll() {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT m FROM MealModel m", Meal.class);
        return query.getResultList();
    }

    public void save(Meal meal) {
        entityManager.persist(meal);
    }

    public void update(Meal meal) {
        entityManager.merge(meal);
    }

    public void delete(Meal meal) 
    {
    	entityManager.remove(meal);
    }
}
