package com.mpJavaGame.math;

import java.awt.*;

public class Vector2F {

    private float x, y;

    public Vector2F() {
    }

    public Vector2F(float x, float y) {
        set(x, y);
    }

    public Vector2F(Vector2F v) {
        set(v);
    }

    public Vector2F(Point p) {
        set(p.x, p.y);
    }

    public float getX() {
        return x;
    }

    public int getIntX() {
        return (int) x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getIntY() {
        return (int) y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void set(float x, float y) {
        setX(x);
        setY(y);
    }

    public void set(Vector2F v) {
        setX(v.x);
        setY(v.y);
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector2F v) {
        this.x += v.x;
        this.y += v.y;
    }

    public Vector2F addV2F(Vector2F v) {
        return new Vector2F(v.x + x, v.y + y);
    }

    public Vector2F subtract(Vector2F v) {
        return new Vector2F(v.getX() - x, v.getY() - y);
    }

    public void multiply(float scale) {
        this.x *= scale;
        this.y *= scale;
    }

    public void multiply(float sx, float sy) {
        this.x *= sx;
        this.y *= sy;
    }

    public void multiply(Vector2F sv) {
        multiply(sv.x, sv.y);
    }

    public Vector2F multiplyV2F(float scale) {
        Vector2F v = clone();
        v.multiply(scale);
        return v;
    }

    public Vector2F multiplyV2F(float sx, float sy) {
        Vector2F v = clone();
        v.multiply(sx, sy);
        return v;
    }

    public Vector2F multiplyV2F(Vector2F mult) {
        Vector2F v = clone();
        v.multiply(mult);
        return v;
    }

    public void divide(float scale) {
        if (scale != 0d) {
            this.x /= scale;
            this.y /= scale;
        }
    }

    public void divide(float sx, float sy) {
        if (sx != 0d) {
            this.x /= sx;
        }
        if (sy != 0d) {
            this.y /= sy;
        }
    }

    public void divide(Vector2F v) {
        divide(v.x, v.y);
    }

    public Vector2F divideV2F(float scale) {
        Vector2F v = clone();
        v.divide(scale);
        return v;
    }

    public Vector2F divideV2F(float sx, float sy) {
        Vector2F v = clone();
        v.divide(sx, sy);
        return v;
    }

    public Vector2F divideV2F(Vector2F div) {
        Vector2F v = clone();
        v.divide(div);
        return v;
    }

    public Vector2F absV2F() {
        return new Vector2F(Math.abs(this.x), Math.abs(this.y));
    }

    public void abs() {
        setX(Math.abs(this.x));
        setY(Math.abs(this.y));
    }

    public Vector2F direction() {
        Vector2F v = new Vector2F();
        v.setX(this.getX() == 0 ? 0 : this.getX() > 0 ? 1 : -1);
        v.setY(this.getY() == 0 ? 0 : this.getY() > 0 ? 1 : -1);
        return v;
    }

    public Vector2F roundV2F(){
        return new Vector2F(Math.round(this.x), Math.round(this.y));
    }

    public void round(){
        x = Math.round(this.x);
        y = Math.round(this.y);
    }

    public float angle(Vector2F v) {
        float dot = x * v.getX() + y * v.getY();
        float det = x * v.getY() - y * v.getX() ;
        return (float) Math.atan2(det, dot);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Point toPoint() {
        return new Point(getIntX(), getIntY());
    }

    public boolean compare(Vector2F v) {
        return equals(v);
    }

    public boolean compare(Point v) {
        return this.toPoint().equals(v);
    }

    @Override
    public String toString() {
        return "Vector2D = [" + x + " , " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2F vector2F = (Vector2F) o;

        return x == vector2F.x && y == vector2F.y;
    }

    @Override
    public Vector2F clone() {
        return new Vector2F(x, y);
    }
}
