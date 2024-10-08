package test.endlessvoid.endfight;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class dragonevents implements Listener {
    private List<Location> destroyedCrystals = new ArrayList<>();
    private List<Location> healingCrystals = new ArrayList<>();
    private Location landlocation = null;
    private Location defaultlocation = null;
    @EventHandler
    public void dragonchangeevent(EnderDragonChangePhaseEvent event){
        World world = event.getEntity().getWorld();
        if (event.getNewPhase() == EnderDragon.Phase.LAND_ON_PORTAL) {
            new functions().spawnFireballsAroundlocation(event.getEntity().getPodium(),8,8,250,0.00625);
            }
        if (healingCrystals.isEmpty()) {if (event.getEntity().getDragonBattle() != null)
            for (EnderCrystal crystal : event.getEntity().getDragonBattle().getHealingCrystals()) {
                healingCrystals.add(crystal.getLocation());
                defaultlocation = event.getEntity().getPodium();
                landlocation = defaultlocation;
            }}
        if (event.getNewPhase() == EnderDragon.Phase.LEAVE_PORTAL) {
            if (landlocation != defaultlocation) {
                world.spawnEntity(landlocation,EntityType.END_CRYSTAL);
                // Spawn the EndCrystal

// Define the iron bar cage structure
                int cageSize = 2; // Size of the cage (3x3x3 in this case)
                Material cageMaterial = Material.IRON_BARS;

// Place the iron bars around the EndCrystal
                for (int dx = -cageSize; dx <= cageSize; dx++) {
                    for (int dy = -cageSize; dy <= cageSize; dy++) {
                        for (int dz = -cageSize; dz <= cageSize; dz++) {
                            if (Math.abs(dx) == cageSize || Math.abs(dy) == cageSize || Math.abs(dz) == cageSize) {
                                // Skip the bottom layer
                                if (dy == -cageSize) {
                                    continue;
                                }
                                // Calculate the block location
                                Location blockLocation = new Location(world, landlocation.getX() + dx, landlocation.getY() + dy, landlocation.getZ() + dz);
                                Block block = blockLocation.getBlock();
                                block.setType(cageMaterial);
                            }
                        }
                    }
                }
            }
            if (destroyedCrystals.isEmpty()) {
                event.getEntity().setPodium(defaultlocation);
            } else {
                landlocation = destroyedCrystals.getFirst();
                destroyedCrystals.removeFirst();
                event.getEntity().setPodium(landlocation);
            }
        }}
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Check if the damaged entity is an end crystal
        if (event.getEntity() instanceof EnderCrystal) {
            // Get the location of the destroyed crystal
            Location crystalLocation = event.getEntity().getLocation();
            // Check if the crystal is in the getHealingCrystals() list
            if (healingCrystals.contains(crystalLocation)) {
                // Add the location to the destroyedCrystals list
                destroyedCrystals.add(crystalLocation);
            }
        }
}   }
