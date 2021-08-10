package model.values;

import model.interfaces.*;
import model.types.*;

public class LockValue implements Value{
	boolean locked;
	int size;
	int target;
	
	public LockValue() {
		boolean locked = false;
		this.size = 0;
		this.target = -1;
	}
	public LockValue(boolean locked) {
		this.locked = locked;
		this.size = 0;
		this.target = -1;
	}
	public LockValue(int target) {
		this.locked = false;
		this.size = 0;
		this.target = target;
	}
	
	public void setLock(boolean bool) {
		this.locked = bool;
	}
	public boolean getLock() {
		return this.locked;
	}
	
	public void incrementSize() {
		this.size += 1;
	}
	public int getSize() {
		return this.size;
	}
	public void resetSize() {
		this.size = 0;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
	public int getTarget() {
		return this.target;
	}
	
	public boolean ready() {
		return (size == target);
	}
	
	public boolean equals(Object another) {
		return false;
	}
	
	public Type getType() {
		return new LockType();
	}
	
	public String toString() {
		return "<locked: " + Boolean.toString(this.locked) + ", size: " + Integer.toString(this.size) + ", target: " + Integer.toString(this.target) + ">";
	}
}
