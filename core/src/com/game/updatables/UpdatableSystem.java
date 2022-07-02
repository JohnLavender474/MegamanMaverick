package com.game.updatables;

import com.game.Component;
import com.game.core.IEntity;
import com.game.System;

import java.util.Set;

/**
 * {@link System} implementation for handling {@link UpdatableComponent} instances.
 */
public class UpdatableSystem extends System {

    @Override
    public Set<Class<? extends Component>> getComponentMask() {
        return Set.of(UpdatableComponent.class);
    }

    @Override
    protected void processEntity(IEntity entity, float delta) {
        UpdatableComponent updatableComponent = entity.getComponent(UpdatableComponent.class);
        updatableComponent.getUpdatable().update(delta);
    }

}
