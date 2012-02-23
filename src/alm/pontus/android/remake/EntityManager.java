package alm.pontus.android.remake;

import java.util.ArrayList;

public class EntityManager {
	
	public static final EntityManager INSTANCE = new EntityManager();
	
	private ArrayList<Entity> entities;
	
	public EntityManager() {
		this.setEntities(new ArrayList<Entity>());
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
