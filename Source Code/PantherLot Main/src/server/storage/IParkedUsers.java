package server.storage;

import java.util.ArrayList;
import java.util.TreeSet;

import server.controller.ParkingUser;

public interface IParkedUsers {

	/**
	 * accessor
	 * also updates the status before returning it
	 * @return status to be send to security
	 */
	public abstract ArrayList<String> getStatus();

	/**
	 * Searches for a free parking spot to assign to the user.
	 * @param user the user that is looking for a parking spot
	 * @return the spot that will be assigned to the user
	 */
	public abstract IParkingSpot searchParkingSpot(ParkingUser user);

	/**
	 * method to add a parking user to an specific spot on the garage
	 * @param spot Parking Spot that is free (does not contain a parking user)
	 * @param user Parking User that will be assigned a parking spot
	 */
	public abstract void addParkingUser(IParkingSpot spot, ParkingUser user);

	/**
	 * removes a parking user from a parking spot in the garage
	 * @param spot Parking Spot that is taken
	 */
	public abstract void removeParkedUser(IParkingSpot spot);

	/**
	 * searches the hashmap for a spot with the provided spot number
	 * @param number the parking spot number to do the search by
	 * @return the spot with the corresponding spot number
	 */
	public abstract IParkingSpot searchBySpotNumber(String number);

	public abstract String toString();

	/**
	 * accessor
	 * @return an iterable of the hashmap of the garage
	 */
	public abstract Iterable<TreeSet<ParkingSpot>> values();

}