package edu.austral.starship.base.gun;

import edu.austral.starship.base.bullet.Bullet;
import edu.austral.starship.base.shot.Shot;
import edu.austral.starship.base.starship.Starship;
import edu.austral.starship.base.vector.Vector2;


public abstract class Gun {
    private int bullets;
    private int bulletsLeft;
    private int bulletVelocity;
    private long timeBetweetnBullets;
    private long lastTimeGunShot = System.currentTimeMillis();
    private long timeInBetweenCharge = 300;
    private String gunName;
    private Starship starship;

    public Starship getStarship() {
        return starship;
    }

    public Gun() {
    }

    public String getGunName() {
        return gunName;
    }

    public long getLastTimeGunShot() {
        return lastTimeGunShot;
    }

    public long getTimeInBetweenCharge() {
        return timeInBetweenCharge;
    }

    public void shoot (){
        lastTimeGunShot = System.currentTimeMillis();
        bulletsLeft -=1;
    }

    public void recharge(){
        this.bulletsLeft = bullets;
    }

    public int getBulletsLeft() {
        return bullets;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public long getTimeBetweenBullets() {
        return timeBetweetnBullets;
    }
}
