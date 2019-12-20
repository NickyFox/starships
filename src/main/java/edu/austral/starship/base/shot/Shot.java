package edu.austral.starship.base.shot;

import edu.austral.starship.base.bullet.Bullet;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.gun.Gun;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Shot {

    private Bullet bullet;

    public Shot(Vector2 direction, double angle,int bulletVelocity, Gun gun ) {
        this.bullet = new Bullet(direction,angle, bulletVelocity, gun);

    }

    public Bullet getBullet() {
        return bullet;
    }

}
