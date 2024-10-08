package test.endlessvoid.endfight;

import org.bukkit.Location;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class functions {
    public void spawnFireballsAroundlocation(Location entity, int radius, int numLocations, double height, double velocity) {
        double angleIncrement = 360.0 / numLocations;
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < numLocations; i++) {
            double angle = i * angleIncrement;
            double x = radius * Math.cos(Math.toRadians(angle));
            double z = radius * Math.sin(Math.toRadians(angle));
            Location location = new Location(entity.getWorld(), x + entity.getX(), height, z + entity.getZ());
            locations.add(location);
        }
        for (Location location : locations) {
            // Create a new dragon fireball entity
            DragonFireball fireball = (DragonFireball) location.getWorld().spawnEntity(location, EntityType.DRAGON_FIREBALL);

            // Set the fireball's velocity and direction
            fireball.setVelocity(new Vector(0, -velocity, 0));
        }
    }
}
