package all.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import server.storage.*;
import server.controller.*;

@RunWith(Suite.class)
@SuiteClasses({ ControllerSubSystemTesting.class,
		SST022_testSetParkingNotification_RD.class,
		SST023_testSetParkingNotification_RD.class,
		SST024_EntranceDisplayController_DisplayGettersSettersTest.class,
		SST025_EntranceDisplayConroller_isValidTest.class,
		SST026_EntranceDisplayConroller_SetMessage1Test.class,
		SST027_EntranceDisplayController_SetMessage2Test.class,
		SST028_EntranceDisplayController_SetGetDuplicateTest.class,
		SST029_TestIdentifyUser_SD.class, SST030_TestIdentifyUser_SD.class,
		ParkingSpotTest.class, AccessControlServerTest.class})
public class allTests {

}
