package edu.austral.starship.base.shot;

import edu.austral.starship.base.bullet.Bullet;

import java.util.ArrayList;
import java.util.List;

public class ShotController {

    private List<Shot> shoots = new ArrayList<>();

    public void shoot(Shot shot){
        shoots.add(shot);
    }

    public void moveShots(){
        shoots.forEach(shot -> {
            shot.getBullet().moveBullet();
        });
    }

    public List<Shot> getShoots() {
        return shoots;
    }

    public void clearShoots(){
        shoots.clear();
    }

    public List<Bullet> getBulletsFromShots(){
        List<Bullet> bullets = new ArrayList<>();
        shoots.forEach(shot -> bullets.add(shot.getBullet()));
        return bullets;
    }

    public void deleteShot(){
        List<Shot> shotList = new ArrayList<>();
        shoots.forEach(shot -> {
            if (shot.getBullet().hasColissioned() || checkParameters(shot.getBullet())){
                shotList.add(shot);
            }
        });
        shotList.forEach(shot -> shoots.remove(shot));
    }

    private boolean checkParameters(Bullet bullet){
        int y = (int) bullet.getPosition().getY();
        int x = (int) bullet.getPosition().getX();
        //chequeo si esta afuera o adentro de la pantalla
        return 505 < y || y < -5 || 1005 < x || x < -5;
    }

    private static ShotController ourInstance = new ShotController();

    public static ShotController getInstance() {
        return ourInstance;
    }

    private ShotController() {
    }
}
