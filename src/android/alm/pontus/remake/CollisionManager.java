package android.alm.pontus.remake;

import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Rect;

public class CollisionManager {
	public static final CollisionManager INSTANCE = new CollisionManager();
	
	private CopyOnWriteArrayList<Entity> collidableEntities;
	@SuppressWarnings("unused")
	private String TAG = getClass().getSimpleName();
	
	public CollisionManager () {
		collidableEntities = new CopyOnWriteArrayList<Entity>();
	}
	
	public boolean collision() {
//		Log.e("SIZE", ""+collidableEntities.size());
		for(Entity entity1: collidableEntities) {
			for(Entity entity2: collidableEntities) {
				if(!entity1.equals(entity2)) {
					if(Rect.intersects(entity1.getRect(), entity2.getRect())) {
						entity1.collision();
						entity2.collision();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void addEntity(Entity entity) {
		collidableEntities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		collidableEntities.remove(entity);
	}
	
	public void removeDeleted() {
		for(Entity entity: collidableEntities) {
			if(entity.deleted) {
				if(entity.last) {
					Panel.newWave = true;
				}
				collidableEntities.remove(entity);
			}
		}
	}
}
