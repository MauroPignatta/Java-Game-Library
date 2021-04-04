package com.mpJavaGame.interfaces;

import java.awt.geom.Rectangle2D;

public interface Collidable {

    Rectangle2D getHitbox();

    default boolean collide(Collidable other, float offsetX, float offsetY) {
        Rectangle2D hitbox = this.getHitbox();
        hitbox.setRect(hitbox.getX() + offsetX, hitbox.getY() + offsetY,
                hitbox.getWidth(), hitbox.getHeight());

        return this != other &&
                hitbox.intersects(other.getHitbox());
    }

}
