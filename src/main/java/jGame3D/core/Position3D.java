package jGame3D.core;

import jGame.core.Position;

public class Position3D {
    private final double x;
    private final double y;
    private final double z;

    public Position3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position3D setX(double x){
        return new Position3D(x, this.y, this.z);
    }

    public double getX(){
        return x;
    }

    public int getIntX(){
        return (int)x;
    }

    public Position3D setY(double y){
        return new Position3D(this.x, y, this.z);
    }

    public double getY(){
        return y;
    }

    public int getIntY(){
        return (int)y;
    }

    public Position3D setZ(double z){
        return new Position3D(this.x, this.y, z);
    }

    public double getZ(){
        return z;
    }

    public int getIntZ(){
        return (int)z;
    }

    public double distance(Position3D position3D){
        return Math.sqrt(Math.pow(x - position3D.x, 2) + Math.pow(y - position3D.y, 2) + Math.pow(z - position3D.z, 2));
    }

    public Position toPosition2D(double deep, double rotationX, double rotationY){
        //TODO
    }
}
