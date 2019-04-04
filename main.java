import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
	
public class main {
	private static short state = 0;
	private static short backupState = 0;
	private static String options = "\n- Looking for items \n- Show business hours and location \n- Tracking or cancel orders \n- Rate and leave comments";
	static ServerSocket serversocket = null;
	static Socket socket = null;
	static DataInputStream input;
	static DataOutputStream output;
	
	// A 2D array of keywords/phrases to detect actions for each states.
	private static String[][] keyword = { { "search", "get", "item", "looking for" }, // Goes from 0 to 1
			{ "buy", "purchase", "online", "order" }, // Goes from 2 to 3
			{ "reserve", "keep", "stay", "store" }, // Goes from 2 to 6
			{ "check", "hours", "location", "business", "time", "address" }, // Goes from 0 to 11
			{ "order", "cancel", "track", "locat" }, // Goes from 0 to 12
			{ "track", "follow", "find", "locat", "search", "find" }, // Goes from 13 to 14
			{ "cancel", "drop", "remove" }, // Goes from 13 to 15
			{ "comment", "leave", "feedback", "rat" }, // Goes from 0 to 17
			{ "correct", "right", "yes", "sure", "confirm" }, // Generic correct keywords
			{ "don't", "do not", "wrong", "not correct", "incorrect", "no" }, // Generic wrong keywords
			{ "return", "go back", "not what", "not this", "something else", "not looking", "wrong", "no thanks",
					"no, thanks", "no need"}, // Goes to state = 0; overrides every other conditions except state = 20
			{ "goodbye", "that's all", "is all", "done", "everything", "good" },// Goes to state = 20; overrides every other conditions 
			{"manager","report","explain","refund"}// Calls out manager, change to reply manually
			};
	// An array storing generic clothing items
	public static String[] clothingItems = { "t-shirt", "pants", "jeans", "jacket", "gloves", "shirt", "socks",
			"sneakers", "boots", "sweater", "coat" };
	public static String[] accessories = {"earrings","watch","sunglasses","bags","wallet","purse","cap","belt"};
	// Business times and location
	public static String info = "\nLocation: 123 J.Doe Street, Kelowna\n" + "Sun: Closed\n" + "Mon: 10 AM to 8 PM\n"
			+ "Tue: 10 AM to 8 PM\n" + "Wed: 10 AM to 8 PM\n" + "Thu: 10 AM to 8 PM\n" + "Fri: 10 AM to 9 PM\n"
			+ "Sat: 12 PM to 9 PM\n";
	// state == 0 is default state
	// Action group 1: Finding item and subsequent actions on the item
	// state == 1 is looking for item
	// state == 2 is choosing to buy an item online or reserve an item
	// state == 3 is purchase online by card no.
	// state == 4 is acknowledge card no.
	// state == 18; back to 0
	// state == 21 if no; back to 4
	// state == 5 is unacknowledge card no.; back to 3
	// state == 6 is reserve item
	// state == 7 is acknowledge name
	// state == 18; back to 0
	// state == 22 if no; back to 7
	// state == 8 is notify by email when restock
	// state == 9 is acknowledge email
	// state == 18; back to 0
	// state == 23 if no; back to 9
	// state == 10 is wrong item type; back to 0
	// Action group 2: Getting business hours and location
	// state == 11 is checking hours and location
	// Action group 3: Check order and subsequent actions on the order
	// state == 12 is checking order by order no.
	// state == 13 is track order or cancel order
	// state == 14 is track order by email
	// state == 9 is acknowledge email
	// state == 18; back to 0
	// state == 23 if no; back to 9
	// state == 15 is cancel order confirm
	// state == 16 is order not found
	// Action group 4: Leave comment
	// state == 17 is leave comment
	// state == 18; back to 0
	// state == 18 is generic confirm
	// state == 19 is unacknowledged action
	// state == 20 puts you out of the loop (end)
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					serversocket = new ServerSocket(3535);
					socket = serversocket.accept();
					input = new DataInputStream(socket.getInputStream());
					output = new DataOutputStream(socket.getOutputStream());
					String message = "";
					output.writeUTF("Hello there, welcome to SuperWet online customer service. How may I help you today?");
					output.writeUTF(options);
					while(true) {
						message = input.readUTF();
						output.writeUTF(respond(message));
					}
					
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}).start();
		
		
	}

	public static String respond(String str) {
		state = findstate.findState(str);
		String output = response.findResponse(str);
		return output;	
	}
	
	public static String getKeyWord(int i, int j) {
		return keyword[i][j];
	}
	public static int keywordLen(int i) {
		return keyword[i].length;
	}
	public static short getState() {
		return state;
	}
	
	public static short getbkState() {
		return backupState;
	}
	public static void setState(short i) {
		state = i;
	}
	public static void setbkState(short i) {
		backupState = i;
	}
}
