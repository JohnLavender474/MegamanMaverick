package com.game.entities.contracts;

import com.game.core.IEntity;

import java.util.Set;

/**
 * Interface for {@link IEntity} instances that are able to be damaged.
 */
public interface Damageable {

    /**
     * DamagerDef mask set.
     *
     * @return the set
     */
    Set<Class<? extends Damager>> getDamagerMaskSet();

    /**
     * Take damage from the damager.
     *
     * @param damager the damager
     */
    void takeDamageFrom(Damager damager);

    /**
     * Is invincible.
     *
     * @return is invincible
     */
    default boolean isInvincible() {
        return false;
    }

    /**
     * Can be damaged by the damager. Returns true if {@link #isInvincible()} is false and {@link #getDamagerMaskSet()}
     * contains the supplied {@link Damager}.
     *
     * @param damager the damager
     * @return can be damaged by the damager
     */
    default boolean canBeDamagedBy(Damager damager) {
        return !isInvincible() && getDamagerMaskSet().contains(damager.getClass());
    }

}
