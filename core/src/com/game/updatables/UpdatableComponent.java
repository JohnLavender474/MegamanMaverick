package com.game.updatables;

import com.game.Component;
import com.game.utils.Updatable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for {@link Component} that acts simply as a "catch-all" for {@link Updatable} implementations.
 */
@Getter
public class UpdatableComponent implements Component {
    private final List<Updatable> updatables = new ArrayList<>();
}