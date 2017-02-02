
import resources.Script1Helper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author jcdia
 */
public class Script1 extends Script1Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Jan 26, 2017 7:07:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 10.0  Build 14393 ()
	 * 
	 * @since  2017/01/26
	 * @author jcdia
	 */
	public void testMain(Object[] args) 
	{
		startApp("ClassicsJavaA");
		sysTreeView32tree().
		
		// Frame: ClassicsCD
		tree2().click(atPath("Composers->Haydn->Location(PLUS_MINUS)"));
		tree2().click(atPath("Composers->Haydn->Symphonies Nos. 94 & 98"));
		placeOrder().click();
		
		// Frame: Member Logon
		newCustomer().click();
		ok().click();
		
		// Frame: Place an Order
		cardNumberIncludeTheSpaces().click(atPoint(81,10));
		placeAnOrder().inputChars("675765");
		placeOrder2().click();
		
		// 
		ok2().click();
		
		// Frame: Place an Order
		name().click(atPoint(181,7));
		placeAnOrder().inputChars("5747");
		placeOrder2().click();
		
		// 
		ok2().click();
		
		// Frame: Place an Order
		street().click(atPoint(201,8));
		placeAnOrder().inputKeys("e46546{TAB}");
		placeAnOrder().inputKeys("2542,3463%,fytr{TAB}");
		placeAnOrder().inputChars("354364567465");
		expirationDate().dragToScreenPoint(atPoint(34,14), placeAnOrder().getScreenPoint(atPoint(509,262)));
		placeAnOrder().inputChars("11/17");
		placeOrder2().click();
		
		// 
		ok3().drag();
	}
}

