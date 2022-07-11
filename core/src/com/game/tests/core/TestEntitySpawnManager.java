package com.game.tests.core;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.game.utils.Resettable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;

import static com.game.utils.UtilMethods.rectToBBox;

@Getter
@RequiredArgsConstructor
public class TestEntitySpawnManager implements Resettable {

    private final Camera camera;
    private final Collection<Rectangle> playerSpawns;
    private final Collection<TestEntitySpawn> enemySpawns;

    @Setter
    private Rectangle currentPlayerSpawn;

    public void update() {
        enemySpawns.forEach(enemySpawn -> enemySpawn.update(camera));
        playerSpawns.stream().filter(playerSpawn -> camera.frustum.boundsInFrustum(rectToBBox(playerSpawn)))
                .findFirst().ifPresent(this::setCurrentPlayerSpawn);
    }

    @Override
    public void reset() {
        enemySpawns.forEach(enemySpawn -> {
            enemySpawn.cull();
            enemySpawn.resetCamBounds();
        });
    }

}
