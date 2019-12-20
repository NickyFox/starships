package edu.austral.starship.base.asteroid;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidController{

    private List<Asteroid> asteroids = new ArrayList<>();
    private Random random = new Random();
    private double time = System.currentTimeMillis();
    private Vector2 center = new Vector2(500,250);

    private void spawnAsteroid() {
        if (random.nextInt(1000) <= 10) {
           Asteroid asteroid = newAsteroid();
            asteroids.add(asteroid);
        }
    }

    public void clearAsteroids(){
        asteroids.clear();
    }

    private Asteroid newAsteroid() {
        Random randomBool = new Random();

        if (randomBool.nextBoolean()){
            if (randomBool.nextBoolean()){
                Vector2 initialPosition = new Vector2((float) random.nextInt(1000), -5);
                Vector2 resultant = center.substract(initialPosition).divide(getRandom(100,300));
                return new Asteroid((int)resultant.getX(),(int)resultant.getY(), initialPosition);
            }else {
                Vector2 initialPosition = new Vector2((float) random.nextInt(1000), 505);
                Vector2 resultant = center.substract(initialPosition).divide(getRandom(100,300));
                return new Asteroid((int)resultant.getX(),(int)resultant.getY(), initialPosition);
            }


        } else {
            if (randomBool.nextBoolean()) {
                Vector2 initialPosition = new Vector2(-5, (float) random.nextInt(1000));
                Vector2 resultant = center.substract(initialPosition).divide(getRandom(100, 300));
                return new Asteroid((int)resultant.getX(),(int)resultant.getY(), initialPosition);
            } else {
                Vector2 initialPosition = new Vector2(1005, (float) random.nextInt(1000));
                Vector2 resultant = center.substract(initialPosition).divide(getRandom(100, 300));
                return new Asteroid((int)resultant.getX(),(int)resultant.getY(), initialPosition);
            }
        }
    }

    private float getRandom(int min,int max){
        return min + random.nextInt(max-min);
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void deleteAsteroid(){
        List<Asteroid> asteroidList= new ArrayList<>();
        asteroids.forEach(asteroid -> {
            if (checkParameters(asteroid) || !asteroid.isAlive()){
                asteroidList.add(asteroid);
            }
        });

        asteroidList.forEach(asteroid -> asteroids.remove(asteroid));
    }

    private boolean checkParameters(Asteroid asteroid){
        int y = (int) asteroid.getPosition().getY();
        int x = (int) asteroid.getPosition().getX();
        //chequeo si esta afuera o adentro de la pantalla
      return 505 < y || y < -5 || 1005 < x || x < -5;
    }

    private static AsteroidController ourInstance = new AsteroidController();

    public static AsteroidController getInstance() {
        return ourInstance;
    }

    private AsteroidController() {
    }


    public void moveAsteroids() {
        spawnAsteroid();
        asteroids.forEach(Asteroid::changePosition);
    }
}
