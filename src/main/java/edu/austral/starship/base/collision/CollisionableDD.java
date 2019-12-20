package edu.austral.starship.base.collision;

import edu.austral.starship.base.asteroid.Asteroid;
import edu.austral.starship.base.bullet.Bullet;
import edu.austral.starship.base.starship.Starship;

public interface CollisionableDD extends Collisionable<CollisionableDD> {
    void colliedS(Starship starship);
    void colliedAsteroid(Asteroid asteroid);
    void colliedBullet(Bullet bullet);

}
