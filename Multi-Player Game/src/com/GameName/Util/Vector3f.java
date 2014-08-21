package com.GameName.Util;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Vector3f {
	private float x, y, z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Vector3f)) return false;
		
		Vector3f other = (Vector3f) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x)) return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y)) return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z)) return false;
		
		return true;
	}

	public Vector3f add(Vector3f add) {
		return new Vector3f(
				x + add.getX(),
				y + add.getY(),
				z + add.getZ()
			);
	}
	
	public Vector3f add(float add) {
		return new Vector3f(
				x + add,
				y + add,
				z + add
			);
	}
	
	public Vector3f subtract(Vector3f subtract) {
		return new Vector3f(
				x - subtract.getX(),
				y - subtract.getY(),
				z - subtract.getZ()
			);
	}
	
	public Vector3f subtract(float subtract) {
		return new Vector3f(
				x - subtract,
				y - subtract,
				z - subtract
			);
	}
	
	public Vector3f multiply(Vector3f mult) {
		return new Vector3f(
				x * mult.getX(),
				y * mult.getY(),
				z * mult.getZ()
			);
	}
	
	public Vector3f multiply(float mult) {
		return new Vector3f(
				x * mult,
				y * mult,
				z * mult
			);
	}
	
	public Vector3f divide(Vector3f divide) {
		return new Vector3f(
				x / divide.getX(),
				y / divide.getY(),
				z / divide.getZ()
			);
	}
	
	public Vector3f divide(float divide) {
		return new Vector3f(
				x / divide,
				y / divide,
				z / divide
			);
	}
	
	public float distance(Vector3f other) {
		return (float) Math.sqrt(
				Math.pow(x - other.getX(), 2) +
				Math.pow(y - other.getY(), 2) + 
				Math.pow(z - other.getZ(), 2)
			);
	}
	
	public float length() {
		return (float) Math.sqrt(
				Math.pow(x, 2) +
				Math.pow(y, 2) + 
				Math.pow(z, 2)
			);
	}
	
	public Vector3f rotate(Vector3f center, Vector3f rot) {
		rot = rot.toRadians();
		
		return new Vector3f(
				(float) (((cos(rot.getZ()) * cos(rot.getY()) + sin(rot.getY()) * -sin(rot.getZ()) * cos(rot.getX())) * x)		+ ((-sin(rot.getZ()) * cos(rot.getX())) * y)	+ ((-sin(rot.getY()) * cos(rot.getZ()) + cos(rot.getY()) * -sin(rot.getZ()) *  sin(rot.getX()) ) * z)),
				(float) (((sin(rot.getZ()) * cos(rot.getY()) + sin(rot.getY()) * -sin(rot.getX()) * cos(rot.getZ())) * x)		+ (( cos(rot.getZ()) * cos(rot.getX())) * y)	+ ((-sin(rot.getY()) * sin(rot.getZ()) + cos(rot.getY()) *  cos(rot.getZ()) * -sin(rot.getX()) ) * z)),
				(float) (((cos(rot.getX()) * sin(rot.getY())													   ) * x)		+ (( sin(rot.getX()) 				  ) * y)	+ (( cos(rot.getY()) * cos(rot.getX())														   ) * z))		
			);
	}
	
	public Vector3f toRadians() {
		return new Vector3f(
				(float) Math.toRadians(x),
				(float) Math.toRadians(y),
				(float) Math.toRadians(z)
			);
	}
	
	public Vector3f toDegrees() {
		return new Vector3f(
				(float) Math.toDegrees(x),
				(float) Math.toDegrees(y),
				(float) Math.toDegrees(z)
			);
	}
	
	public Vector3f clone() {
		return new Vector3f(x, y, z);
	}
	
	public String toString() {
		return "Vector3f [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
}
