package com.mygdx.game.screens.menus;

import com.mygdx.game.utils.Direction;

/**
 * The interface MenuButton prompt.
 */
public interface MenuButton {

    /**
     * Action on select.
     *
     */
    void onSelect(float delta);

    /**
     * On navigate.
     *
     * @param direction  the direction
     */
    void onNavigate(Direction direction, float delta);

}