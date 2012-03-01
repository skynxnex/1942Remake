package android.alm.pontus.remake;

import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Canvas;

public class EntityManager {

	public static final EntityManager INSTANCE = new EntityManager();

	private CopyOnWriteArrayList<Entity> entities;

	public EntityManager() {
		this.setEntities(new CopyOnWriteArrayList<Entity>());
	}

	public CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(CopyOnWriteArrayList<Entity> entities) {
		this.entities = entities;
	}

	public void drawEntities(Canvas canvas) {
		for(Entity entity : this.entities){	
			entity.onDraw(canvas);
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void updatePosition() {
		for(Entity entity : this.entities){
			entity.updatePosition();
		}
	}

	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}

	public int getArraySize() {
		return this.entities.size();
	}

	public void removeDeleted() {
		for(Entity entity: entities) {
			if(entity.deleted) {
				if(entity.last) {
					Panel.newWave = true;
				}
				entities.remove(entity);
			}
		}
		CopyOnWriteArrayList<Entity> enemyPlanes = CollisionManager.INSTANCE.getEnemyPlanes();
		for(Entity entity: enemyPlanes) {
			if(entity.deleted) {
				enemyPlanes.remove(entity);
			}
		}
		CopyOnWriteArrayList<Entity> shots = CollisionManager.INSTANCE.getShots();
		for(Entity entity: shots) {
			if(entity.deleted) {
				shots.remove(entity);
			}
		}
	}
}
