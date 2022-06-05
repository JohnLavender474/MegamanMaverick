package com.mygdx.game.controllers;

import com.mygdx.game.core.Component;
import com.mygdx.game.core.Entity;
import com.mygdx.game.core.System;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * {@link System} implementation for triggering controller events specific to an {@link Entity}. The component
 * {@link ControllerComponent} defines actions for button on just pressed, on press continuted, and on just released.
 * See {@link ControllerButtonActuator}.
 */
@RequiredArgsConstructor
public class ControllerSystem extends System {

    private final ControllerManager controllerManager;

    @Override
    public Set<Class<? extends Component>> getComponentMask() {
        return Set.of(ControllerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float delta) {
        ControllerComponent controllerComponent = entity.getComponent(ControllerComponent.class);
        controllerManager.listenToController(controllerComponent, delta);
    }

}