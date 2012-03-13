package android.alm.pontus.remake;

import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Rect;

public class CollisionManager {
	public static final CollisionManager INSTANCE = new CollisionManager();
	
	private CopyOnWriteArrayList<Entity> enemyPlanes;
	private CopyOnWriteArrayList<Entity> shots;
	private CopyOnWriteArrayList<Entity> bombs;
	
	@SuppressWarnings("unused")
	private String TAG = getClass().getSimpleName();
	
	public CollisionManager () {
		enemyPlanes 	= new CopyOnWriteArrayList<Entity>();
		shots			= new CopyOnWriteArrayList<Entity>();
		bombs			= new CopyOnWriteArrayList<Entity>();
	}
	
	public boolean collision() {
//		Log.e("SIZE", ""+collidableEntities.size());
		for(Entity entity1: enemyPlanes) {
			for(Entity entity2: shots) {
				if(!entity1.equals(entity2)) {
					if(Rect.intersects(entity1.getRect(), entity2.getRect())) {
						entity1.collision();
						entity2.collision();
						new Explosion(entity1.getX(), entity1.getY(), Panel.explosion, 25);
						CounterManager.INSTANCE.addScore(entity1.getPoints() + entity2.getPoints());
						return true;
					}
				}
			}
		}
		for(Entity entity: bombs) {
			if(Rect.intersects(entity.getRect(), Panel.player.getRect())) {
				GameThread.running = false;
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
	
	public void addBomb(Entity entity) {
		this.bombs.add(entity);
	}

	public void clearLists() {
		enemyPlanes.clear();
		bombs.clear();
		shots.clear();
	}
}
