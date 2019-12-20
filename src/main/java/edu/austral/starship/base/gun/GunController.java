package edu.austral.starship.base.gun;

import edu.austral.starship.base.shot.Shot;
import edu.austral.starship.base.shot.ShotController;
import edu.austral.starship.base.vector.Vector2;

public class GunController {

    private Long lastTimeGunShot = System.currentTimeMillis();

    private static GunController ourInstance = new GunController();

    public static GunController getInstance() {
        return ourInstance;
    }

    private GunController() {
    }

    public int getBulletsLeft(Gun gun){
        return gun.getBulletsLeft();
    }

    public String getGunName(Gun gun) {
        return gun.getGunName();
    }

    public void shoot(Vector2 position, double angle, Gun gun) {
        if (!(lastTimeGunShot + gun.getTimeBetweenBullets() >= System.currentTimeMillis())) {
            if (gun.getBulletsLeft() > 0) {
                lastTimeGunShot = System.currentTimeMillis();
                gun.shoot();
                ShotController.getInstance().shoot(new Shot(position, angle, gun.getBulletVelocity(), gun));
            }else if ((gun.getTimeInBetweenCharge() + lastTimeGunShot) < System.currentTimeMillis()){
                gun.recharge();
                lastTimeGunShot = System.currentTimeMillis();
                gun.shoot();
                ShotController.getInstance().shoot(new Shot(position, angle, gun.getBulletVelocity(), gun));
            }
        }
    }

}
