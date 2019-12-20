package edu.austral.starship.base.gun;

import edu.austral.starship.base.starship.Starship;

public class SuperGun extends Gun {
    private int bullets = 5;
    private int bulletVelocity = 20;
    private long timeBetweetnBullets = 80;
    private int bulletsLeft;
    private long lastTimeGunShot = System.currentTimeMillis();
    private long timeInBetweenCharge = 3000;
    private String gunName;
    private Starship starship;

    public SuperGun(Starship starship) {
        this.bulletsLeft = bullets;
        this.gunName = "Super gun";
        this.starship = starship;
    }

    public Starship getStarship() {
        return starship;
    }

    public String getGunName() {
        return gunName;
    }

    public void shoot (){
        bulletsLeft -=1;
    }

    public int getBulletsLeft() {
        return bulletsLeft;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public long getTimeBetweenBullets() {
        return timeBetweetnBullets;
    }


    public long getLastTimeGunShot() {
        return lastTimeGunShot;
    }

    public long getTimeInBetweenCharge() {
        return timeInBetweenCharge;
    }

    public void recharge(){
        this.bulletsLeft = bullets;
        System.out.println("recharging" + bulletsLeft);
    }
}
