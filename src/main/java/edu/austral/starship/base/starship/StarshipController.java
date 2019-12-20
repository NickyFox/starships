package edu.austral.starship.base.starship;


import edu.austral.starship.base.gun.GunController;
import edu.austral.starship.base.vector.Vector2;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StarshipController {

    private int gunIndex = 0;

    private Long lastTimeGunShot = System.currentTimeMillis();

    private List<Starship> starships = new ArrayList<>();

    private boolean areAlive;

    private Starship starship1 = new Starship(new Vector2(500,200), 1);
    private Starship starship2 = new Starship(new Vector2(500,300), 2);

    private Properties properties = new Properties();

    public void generateStarshipKeys(){
        Map<Integer, Actions> starship1Map = new HashMap<>();
        starship1Map.put(getIntegerProperty("Starship_1_UP"), Actions.UP);
        starship1Map.put(getIntegerProperty("Starship_1_DOWN"), Actions.DOWN);
        starship1Map.put(getIntegerProperty("Starship_1_LEFT"), Actions.LEFT);
        starship1Map.put(getIntegerProperty("Starship_1_RIGHT"), Actions.RIGHT);
        starship1Map.put(getIntegerProperty("Starship_1_SHOOT"), Actions.SHOOT);
        starship1Map.put(getIntegerProperty("Starship_1_CHANGEGUN"), Actions.CHANGEGUN);

        Map<Integer, Actions> starship2Map = new HashMap<>();
        starship2Map.put(getIntegerProperty("Starship_2_UP"), Actions.UP);
        starship2Map.put(getIntegerProperty("Starship_2_DOWN"), Actions.DOWN);
        starship2Map.put(getIntegerProperty("Starship_2_LEFT"), Actions.LEFT);
        starship2Map.put(getIntegerProperty("Starship_2_RIGHT"), Actions.RIGHT);
        starship2Map.put(getIntegerProperty("Starship_2_SHOOT"), Actions.SHOOT);
        starship2Map.put(getIntegerProperty("Starship_2_CHANGEGUN"), Actions.CHANGEGUN);

        starship1.setKeyMap(starship1Map);
        starship2.setKeyMap(starship2Map);

    }

    public void load(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(path);
        properties.load(stream);
    }

    public Integer getIntegerProperty(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }
    public Character getCharProperty(String key) {
        return properties.getProperty(key).charAt(0);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }


    public void startGame(){

        starships.add(starship1);
        starships.add(starship2);
        areAlive = true;

    }

    public boolean checkAliveness(){
        for (Starship s : starships) {
            this.areAlive = (this.areAlive && s.isAlive());
        }
        return areAlive;
    }

    public void decodeMovement(Set<Integer> keySet){
        starships.forEach(starship -> {
            keySet.forEach(key -> {
                if(starship.getKeyMap().containsKey(key)) {
                    switch (starship.getKeyMap().get(key)) {
                        case CHANGEGUN:
                            changeGun(starship);
                            break;
                        case UP:
                            if (starship.getySpeed() > -5) starship.setySpeed(starship.getySpeed() - 0.2);
                            break;
                        case DOWN:
                            if (starship.getySpeed() < 5) starship.setySpeed(starship.getySpeed() + 0.2);
                            break;
                        case LEFT:
                            if (starship.getxSpeed() > -5) starship.setxSpeed(starship.getxSpeed() - 0.2);
                            break;
                        case RIGHT:
                            if (starship.getxSpeed() < 5) starship.setxSpeed(starship.getxSpeed() + 0.2);
                            break;
                        case SHOOT:
                            shoot(starship);
                            break;
                        default:
                            break;
                    }
                }
            });
        });

//        if (keySet.contains('a')) {
//                if (starship.getxSpeed() > -5) starship.setxSpeed(starship.getxSpeed() - 0.2);
//            }
//
//            if (keySet.contains('d')) {
//                if (starship.getxSpeed() < 5) starship.setxSpeed(starship.getxSpeed() + 0.2);
//            }
//
//            if (keySet.contains('w')) {
//                if (starship.getySpeed() > -5) starship.setySpeed(starship.getySpeed() - 0.2);
//            }
//
//            if (keySet.contains('s')) {
//                if (starship.getySpeed() < 5) starship.setySpeed(starship.getySpeed() + 0.2);
//
//            }
//            if (keySet.contains(' ')) {
//                shoot(starship);
//            }
//            if (keySet.contains('e')) {
//                changeGun(starship);
//            }

    }

    public double getPoints(Starship starship){
        return starship.getScore();
    }

    public void addScore(Starship starship,int velocity){
        starship.addScore(velocity);
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void checkPosition(Starship starship){

        float yPosition = starship.getPosition().getY();
        float xPosition = starship.getPosition().getX();


        if (xPosition < -5){
            starship.setPosition(495,yPosition);
        }else if (xPosition > 1005){
            starship.setPosition(0, yPosition);
        }else if(yPosition < -5){
            starship.setPosition(xPosition, 500);
        }else if(yPosition > 505){
        starship.setPosition(xPosition, 0);
        }
    }

    private boolean isAlive(Starship starship){

        return starship.isAlive();
    }

    private void shoot(Starship starship){

        GunController.getInstance().shoot(starship.getPosition(),starship.getAngle(),starship.getCurrentGun());

    }

    public String getGunName(Starship starship){
        return GunController.getInstance().getGunName(starship.getCurrentGun());
    }

    private void changeGun(Starship starship){


        if (lastTimeGunShot + 500 < System.currentTimeMillis()) {
            lastTimeGunShot = System.currentTimeMillis();
            if (gunIndex < starship.getAmountOfGuns()) {
                starship.changeCurrentGun(gunIndex);
                gunIndex +=1 ;
            } else {
                gunIndex = 0;
            }
        }
    }

    public int getBulletsLeft(Starship starship){
        return GunController.getInstance().getBulletsLeft(starship.getCurrentGun());
    }

    public Integer getStarshipPlayerNumber(Starship starship){
        return starship.getPlayerNumber();
    }

    private StarshipController() {
    }

    private static StarshipController ourInstance = new StarshipController();

    public static StarshipController getInstance() {
        return ourInstance;
    }

    public int getLives(Starship starship) {
        return starship.getLivesLeft();
    }

    public void gameOver() {
        this.areAlive = false;
        starships.clear();
    }
}
