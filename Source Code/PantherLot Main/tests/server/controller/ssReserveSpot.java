package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class ssReserveSpot {
	
	ControllerFacade facade;
	ParkingSpot spot;
	String id;
	PrintWriter pW;
	
	Socket sockListener;
	InputStream in;
	OutputStream out;
	Scanner scan;
	PrintWriter pW2;
	
	
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createAccessControlServer(3732);
		facade.startAccessControlServer();
		id = "1663314";
		
		spot = mock(ParkingSpot.class);
		when(spot.getParkingNumber()).thenReturn("101");
		
		pW  = new PrintWriter(System.out);
		facade.getAccessControlServer().getDisplayConnections().put("101",  pW);
		
		sockListener = new Socket("localhost", 3732);
		in = sockListener.getInputStream();
		out = sockListener.getOutputStream();
		scan = new Scanner(in);
		pW2 = new PrintWriter(out, true);
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReserveSpot() {
		//facade.reserveSpot(spot, id);
		facade.sendMsg(id, pW);
		String received = "";
		while(scan.hasNextLine()){
			received = scan.nextLine();}
		assertEquals(id, received);
	}

}
