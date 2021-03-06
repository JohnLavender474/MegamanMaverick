package com.game.behaviors;

import com.game.System;
import com.game.core.IEntity;
import com.game.updatables.Updatable;

import java.util.Set;

/**
 * {@link System} implementation for updating entity behaviors.
 */
public class BehaviorSystem extends System {

    public BehaviorSystem() {
        super(Set.of(BehaviorComponent.class));
    }

    @Override
    protected void processEntity(IEntity entity, float delta) {
        BehaviorComponent behaviorComponent = entity.getComponent(BehaviorComponent.class);
        Updatable preProcess = behaviorComponent.getPreProcess();
        if (preProcess != null) {
            preProcess.update(delta);
        }
        behaviorComponent.getBehaviors().forEach(behavior -> behavior.update(delta));
        Updatable postProcess = behaviorComponent.getPostProcess();
        if (postProcess != null) {
            postProcess.update(delta);
        }
    }

}
