package edu.austral.starship.base.starship;

import edu.austral.starship.base.asteroid.Asteroid;
import edu.austral.starship.base.bullet.Bullet;
import edu.austral.starship.base.collision.CollisionableDD;
import edu.austral.starship.base.gun.ClassicGun;
import edu.austral.starship.base.gun.ExpertGun;
import edu.austral.starship.base.gun.Gun;
import edu.austral.starship.base.gun.SuperGun;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.atan2;

public class Starship implements CollisionableDD {

    private Vector2 position;
    private double ySpeed = 0;
    private double xSpeed = 0;
    private List<Gun> guns = new ArrayList();
    private Gun currentGun;
    private int lives;
    private boolean isAlive;
    private double score;
    private int playerNumber;

    private Map<Integer,Actions> keyMap;

    public Map<Integer, Actions> getKeyMap() {
        return keyMap;
    }



    public void setKeyMap(Map<Integer, Actions> keyMap) {
        this.keyMap = keyMap;
    }

    Starship(Vector2 position, int playerNumber) {
        this.playerNumber = playerNumber;
        this.position = position;
        this.lives = 10;
        this.isAlive = true;
        Gun gun1 = new ClassicGun(this);
        Gun gun2 = new ExpertGun(this);
        Gun gun3 = new SuperGun(this);
        guns.add(gun1);
        guns.add(gun2);
        guns.add(gun3);
        currentGun = guns.get(0);
        score = 0;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    void changeCurrentGun(int index){
        currentGun = guns.get(index);

    }

    int getAmountOfGuns(){
        return guns.size();
    }

    public Vector2 getPosition() {
        return position;
    }

    void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    void setPosition(float x, float y){
        position = new Vector2( x, y);
    }

    double getySpeed() {
        return ySpeed;
    }

    double getxSpeed() {
        return xSpeed;
    }

    public void moveStarship(){
        this.position = new Vector2((float) (position.getX() + xSpeed), (float) (position.getY() + ySpeed));
    }

    double getAngle() {return (atan2(ySpeed, xSpeed) - atan2(0, 1));}

    Gun getCurrentGun(){
        return currentGun;
    }

    @Override
    public Shape getShape() {
        return new Rectangle((int) position.getX(), (int) position.getY(),50,80 );
    }

    @Override
    public void collisionedWith(CollisionableDD collisionable) {
        collisionable.colliedS(this);
    }

    @Override
    public void colliedS(Starship starship) {

    }

    @Override
    public void colliedAsteroid(Asteroid asteroid) {
        lives -=1;
        if (lives == 0){
            this.isAlive = false;
        }
    }

    @Override
    public void colliedBullet(Bullet bullet) {

    }

    public int getLivesLeft() {
        return lives;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void startOver() {
        isAlive = true;
        lives = 10;
        position = new Vector2(500,playerNumber*100 + 50);
        setxSpeed(0);
        setySpeed(0);
        score = 0;
    }

    public double getScore() {
        return score;
    }

    public void addScore(int velocity){
        score += (10 * velocity);
    }
}
