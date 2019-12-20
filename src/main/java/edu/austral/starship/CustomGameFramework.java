package edu.austral.starship;

import edu.austral.starship.base.ListConcatenator;
import edu.austral.starship.base.asteroid.Asteroid;
import edu.austral.starship.base.asteroid.AsteroidController;
import edu.austral.starship.base.bullet.BulletController;
import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.collision.CollisionableDD;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.gun.GunController;
import edu.austral.starship.base.shot.ShotController;
import edu.austral.starship.base.starship.Starship;
import edu.austral.starship.base.starship.StarshipController;
import edu.austral.starship.image.ImageStorage;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomGameFramework implements GameFramework {

    private Set<Character> keyDown = new HashSet<>();
    private CollisionEngine collisionEngine = new CollisionEngine();


    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(1000, 500);
        PImage starshipImage = imageLoader.load("src/visual-Images/starship.png");
        starshipImage.resize(50,80);
        ImageStorage.getInstance().setStarship(starshipImage);
        PImage bulletImage = imageLoader.load("src/visual-Images/bullet.png");
        bulletImage.resize(10,10);
        ImageStorage.getInstance().setBullet(bulletImage);
        PImage asteroidImage = imageLoader.load("src/visual-Images/asteroid.png");
        asteroidImage.resize(30,40);
        ImageStorage.getInstance().setAsteroid(asteroidImage);

        StarshipController.getInstance().startGame();
        try {
            StarshipController.getInstance().load("application.properties");
        }catch (IOException e){
            e.printStackTrace();
        }
        StarshipController.getInstance().generateStarshipKeys();

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {

        if (StarshipController.getInstance().checkAliveness()){

                drawHood(graphics);

                checkCollission();

                StarshipController.getInstance().decodeMovement(keySet);

                drawAll(graphics);

        }else {
            AsteroidController.getInstance().clearAsteroids();
            ShotController.getInstance().clearShoots();
            StarshipController.getInstance().gameOver();
//
            graphics.text("GAME OVER", 450, 250);
//            graphics.text("Press 'Enter' to start over", 400, 265);
//
//            if (keyDown.hashCode() == 10){
//                System.out.println("hola");
//                StarshipController.getInstance().startGame();
//            }
        }

    }

    @Override
    public void keyPressed(KeyEvent event) {
        keyDown.add(event.getKey());

    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyDown.remove(event.getKey());
    }

    private void drawHood(PGraphics graphics){
        graphics.text(" Player 1 " +
            "\n Lives: "+ StarshipController.getInstance().getStarships().get(0).getLivesLeft() +
            "\n Current Gun: " + StarshipController.getInstance().getGunName(StarshipController.getInstance().getStarships().get(0)) +
                "\n Bullets Left: " +  StarshipController.getInstance().getBulletsLeft(StarshipController.getInstance().getStarships().get(0)) +
                "\n Point: " + StarshipController.getInstance().getPoints(StarshipController.getInstance().getStarships().get(0)), 15, 10
            );

        graphics.text(" Player 2 " +
                "\n Lives: "+ StarshipController.getInstance().getStarships().get(1).getLivesLeft() +
                "\n Current Gun: " + StarshipController.getInstance().getGunName(StarshipController.getInstance().getStarships().get(1)) +
                "\n Bullets Left: " +  StarshipController.getInstance().getBulletsLeft(StarshipController.getInstance().getStarships().get(1)) +
                "\n Point: " + StarshipController.getInstance().getPoints(StarshipController.getInstance().getStarships().get(1)), 205, 10
        );
    }

    private void drawAll(PGraphics graphics){
        for (Starship starship: StarshipController.getInstance().getStarships()) {
            StarshipController.getInstance().checkPosition(starship);
            starship.moveStarship();
            graphics.image(ImageStorage.getInstance().getStarshipImage(), starship.getPosition().getX(), starship.getPosition().getY());

            AsteroidController.getInstance().deleteAsteroid();
            AsteroidController.getInstance().moveAsteroids();
            AsteroidController.getInstance().getAsteroids().
                    forEach(x ->
                            graphics.image(ImageStorage.getInstance().getAsteroidImage(),
                                    x.getPosition().getX(),
                                    x.getPosition().getY()

                            ));
        }

        ShotController.getInstance().deleteShot();
        ShotController.getInstance().moveShots();
        ShotController.getInstance().getShoots().
                forEach(
                        x ->
                                graphics.image(ImageStorage.getInstance().getBulletImage(), x.getBullet().getPosition().getX(), x.getBullet().getPosition().getY())

                );
    }

    private void checkCollission(){
        List<CollisionableDD> collisionableDDS = new ArrayList<>();
        collisionableDDS.addAll(AsteroidController.getInstance().getAsteroids());
        collisionableDDS.addAll(StarshipController.getInstance().getStarships());
        collisionableDDS.addAll(ShotController.getInstance().getBulletsFromShots());
        collisionEngine.checkCollisions(collisionableDDS);
    }





}
