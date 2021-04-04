package com.mpJavaGame.interfaces;

import com.mpJavaGame.game.Renderer;

public interface Renderable {

    void update(float delta);

    void render(Renderer g);
}
