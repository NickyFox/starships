package edu.austral.starship.base.bullet;

import edu.austral.starship.base.asteroid.Asteroid;
import edu.austral.starship.base.asteroid.AsteroidController;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.collision.CollisionableDD;
import edu.austral.starship.base.gun.Gun;
import edu.austral.starship.base.starship.Starship;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Bullet implements CollisionableDD {

    private Vector2 position;
    private double speed;
    private double movex;
    private double movey;
    private boolean colissioned = false;
    private Gun gun;

    public Bullet(Vector2 position, double angle, int speed, Gun gun) {
        this.speed = speed;
        this.position = position;
        movex = Math.cos(angle) * speed;
        movey = Math.sin(angle) * speed;
        this.gun = gun;
    }

    public Gun getGun() {
        return gun;
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public void moveBullet(){
        this.position = new Vector2((float) (position.getX() + movex ), (float) (position.getY() + movey ));
    }

    public boolean hasColissioned() {
        return colissioned;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setMovex(double movex) {
        this.movex = movex;
    }

    public void setMovey(double movey) {
        this.movey = movey;
    }

    @Override
    public Shape getShape() {

        return new Rectangle((int) position.getX(), (int) position.getY(),10,10 );
    }

    @Override
    public void collisionedWith(CollisionableDD collisionable) {
        collisionable.colliedBullet(this);
    }

    @Override
    public void colliedS(Starship starship) {
    }

    @Override
    public void colliedAsteroid(Asteroid asteroid) {
        this.colissioned = true;
    }

    @Override
    public void colliedBullet(Bullet bullet) {

    }
}
