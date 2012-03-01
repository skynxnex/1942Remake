package android.alm.pontus.remake;

import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Rect;

public class CollisionManager {
	public static final CollisionManager INSTANCE = new CollisionManager();
	
	private CopyOnWriteArrayList<Entity> enemyPlanes;
	private CopyOnWriteArrayList<Entity> shots;
	@SuppressWarnings("unused")
	private String TAG = getClass().getSimpleName();
	
	public CollisionManager () {
		enemyPlanes 	= new CopyOnWriteArrayList<Entity>();
		shots			= new CopyOnWriteArrayList<Entity>();
	}
	
	public boolean collision() {
//		Log.e("SIZE", ""+collidableEntities.size());
		for(Entity entity1: enemyPlanes) {
			for(Entity entity2: shots) {
				if(!entity1.equals(entity2)) {
					if(Rect.intersects(entity1.getRect(), entity2.getRect())) {
						entity1.collision();
						entity2.collision();
						CounterManager.INSTANCE.addScore(entity1.getPoints() + entity2.getPoints());
						//TODO call a nice explosion sprite here
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void addPlane(Entity entity) {
		enemyPlanes.add(entity);
	}
	
	public void removePlane(Entity entity) {
		enemyPlanes.remove(entity);
	}
	
	public void removeDeleted() {
		for(Entity entity: enemyPlanes) {
			if(entity.deleted) {
				if(entity.last) {
					Panel.newWave = true;
				}
				enemyPlanes.remove(entity);
			}
		}
	}

	public CopyOnWriteArrayList<Entity> getShots() {
		return shots;
	}
	
	public CopyOnWriteArrayList<Entity> getEnemyPlanes() {
		return enemyPlanes;
	}

	public void addShot(Entity entity) {
		this.shots.add(entity);
	}
}
