package edu.austral.starship.base;

import edu.austral.starship.base.asteroid.Asteroid;
import edu.austral.starship.base.collision.CollisionableDD;
import edu.austral.starship.base.shot.Shot;
import edu.austral.starship.base.starship.Starship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListConcatenator {
    public static List<CollisionableDD> concatenate(List<CollisionableDD>... lists)
    {
        List<CollisionableDD> result = new ArrayList<>();
        Stream.of(lists).forEach(result::addAll);
        return result;
    }
}
