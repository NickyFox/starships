package edu.austral.starship.base.gun;

import edu.austral.starship.base.starship.Starship;

public class ExpertGun extends Gun {
    private final Starship starship;
    private int bullets = 10;
    private int bulletVelocity = 15;
    private long timeBetweetnBullets = 100;
    private long lastTimeGunShot = System.currentTimeMillis();
    private long timeInBetweenCharge = 3000;
    private int bulletsLeft;
    private String gunName;

    public ExpertGun(Starship starship) {
        this.starship = starship;
        bulletsLeft = bullets;
        this.gunName = "Expert Gun";
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
        System.out.println("recharging gun" + bulletsLeft);
    }
}


