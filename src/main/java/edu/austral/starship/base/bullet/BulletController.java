package edu.austral.starship.base.bullet;

import edu.austral.starship.base.asteroid.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class BulletController {

    void newBullet(){

    }

    private static BulletController ourInstance = new BulletController();

    public static BulletController getInstance() {
        return ourInstance;
    }

    private BulletController() {


    }
}
