package edu.austral.starship.base.asteroid;

import edu.austral.starship.base.bullet.Bullet;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.collision.CollisionableDD;
import edu.austral.starship.base.gun.Gun;
import edu.austral.starship.base.starship.Starship;
import edu.austral.starship.base.starship.StarshipController;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid implements CollisionableDD {

    private double speedX;
    private double speedY;
    private Vector2 position;
    boolean isAlive = true;


    public Asteroid(double speedX, double speedY, Vector2 position) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.position = position;

    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void changePosition() {
        this.position = new Vector2((float) (position.getX() + speedX), (float) (position.getY() + speedY));
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public Shape getShape() {

        return new Rectangle((int) position.getX(), (int) position.getY(),40,30 );
    }

    @Override
    public void collisionedWith(CollisionableDD collisionable) {
        collisionable.colliedAsteroid(this);
    }

    @Override
    public void colliedS(Starship starship) {
        this.isAlive = false;
    }

    @Override
    public void colliedAsteroid(Asteroid asteroid) {

    }

    @Override
    public void colliedBullet(Bullet bullet) {
        this.isAlive = false;
        StarshipController.getInstance().addScore( bullet.getGun().getStarship(),(int)Math.sqrt(Math.pow(speedX,2) + Math.pow(speedY,2)));
    }
}
