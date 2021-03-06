package com.game.levels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.utils.interfaces.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Background implements Drawable {

    private final List<Sprite> backgroundSprites = new ArrayList<>();

    public Background(TextureRegion textureRegion, float startX, float startY, float width, float height,
                      int rows, int cols) {
        Sprite backgroundModel = new Sprite(textureRegion);
        backgroundModel.setBounds(startX, startY, width, height);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Sprite backgroundSprite = new Sprite(backgroundModel);
                backgroundSprite.setPosition(startX + width * cols, startY + height * rows);
                backgroundSprites.add(backgroundSprite);
            }
        }
    }

    public void translate(float x, float y) {
        backgroundSprites.forEach(sprite -> sprite.translate(x, y));
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        backgroundSprites.forEach(sprite -> sprite.draw(spriteBatch));
    }

}
