package edu.austral.starship.image;

import processing.core.PImage;

public class ImageStorage {
    private static ImageStorage ourInstance = new ImageStorage();

     private PImage starshipImage;
     private PImage bulletImage;
     private PImage asteroidImage;

    public static ImageStorage getInstance() {
        return ourInstance;
    }

    private ImageStorage() {}

    public void setAsteroid(PImage asteroidImage) {
        this.asteroidImage = asteroidImage;
    }

    public void setStarship(PImage starshipImage) {
        this.starshipImage = starshipImage;
    }

    public void setBullet(PImage bulletImage) {
        this.bulletImage = bulletImage;
    }

    public PImage getStarshipImage() {
        return starshipImage;
    }

    public PImage getBulletImage() {
        return bulletImage;
    }

    public PImage getAsteroidImage() {
        return asteroidImage;
    }
}
