package server.storage;

import java.io.PrintWriter;

import server.controller.ParkingUser;

public interface IParkingSpot {

	/**
	 * Mutator that assigns a parking user to the parking spot
	 * @param pu Parking user object storing the information of the person
	 * parking
	 */
	public abstract void assignParkingSpot(ParkingUser pu);

	/**
	 * Mutator that removes a parking user to the parking spot
	 * removes the user that is currently parked on this spot
	 */
	public abstract void removeAssignedUser();

	/**
	 * Accessor that returns the parking user object
	 * @return the user currently parked on this spot
	 */
	public abstract ParkingUser getUser();

	/**
	 * Accessor that returns the parking spot
	 * @return the number of the parking spot
	 */
	public abstract int getParkingSpot();

	/**
	 * 
	 * @return
	 */
	public abstract String getParkingNumber();

	/**
	 * 
	 * @return
	 */
	public abstract int getFloor();

	/**
	 * accessor
	 * @return the printwriter of this parking spot
	 */
	public abstract PrintWriter getPrintWriter();

	/**
	 * mutator
	 * @param p assigns this printwriter to the parking spot
	 */
	public abstract void setPrintWriter(PrintWriter p);

	/**
	 * accessor 
	 * @return the directions of this parking spot
	 */
	public abstract String getDirections();

	/**
	 * accessor
	 * @return true iff there is a screen connected to this spot
	 */
	public abstract boolean isConnected();

	/**
	 * Accessor that returns if the parking spot is available for use
	 * @return if the spot is currently taken by an user or not
	 */
	public abstract boolean isAvailable();

	public abstract int compareTo(Object o);

	public abstract String toString();

	/**
	 * accessor 
	 * @return the type of parking this parking spot is for
	 */
	public abstract String getParkingType();

}