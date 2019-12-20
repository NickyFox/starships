package edu.austral.starship.base.gun;

import edu.austral.starship.base.starship.Starship;

public class ClassicGun extends Gun {
    private final String gunName;
    private int bullets = 15;
    private int bulletVelocity = 7;
    private long timeBetweenBullets = 300;
    private int bulletsLeft;
    private long lastTimeGunShot = System.currentTimeMillis();
    private long timeInBetweenCharge = 3000;
    private Starship starship;

    public ClassicGun(Starship starship) {
        bulletsLeft = bullets;
        this.gunName = "Classic Gun";
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
        return timeBetweenBullets;
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
