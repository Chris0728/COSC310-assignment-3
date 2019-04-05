
public class response {
	
	
	private static String[][] responses = {
			{ "How else may I help you today?", "Is there anything else I can help you with?",
					"How else may I be of assitance?", "Do you want me to help you with other things?",
					"Is there anywhere else I can help?", "Do you still want me to do something for you?" },
			{ "Sure, what would you like me to find for you?", "What kind of item do you want me to get for you?",
					"Could you give me a name of what you need?" },
			{ "We do have the item in stock right now, do you want to order online or to reserve in in store?",
					"We have the item available, would you like to purchase online or us to reserve it in store?",
					"We currently have this item here, do you wish to buy it online right now or do you want us to keep it for you in store?" },
			{ "Please give us your credit card number for us to complete this purchase.",
					"Could you give us your credit card number to finish the transaction?",
					"Please provide us your credit card number to pay for the item." },
			{ "Are you sure your card number is ", "Is your card number ", "Do you confirm that your card number is " },
			{ "Sorry, your input is not a legimitate card number, please type your card number as a 16 digit number without spaces.",
					"Your number is not in the correct format, please type your card number as a 16 digit number without spaces." },
			{ "Please leave us your name and we will sell you the item we reserved for you.",
					"Give us your name so that we know it is you when you walk in.",
					"Could you leave us your name for us to know it is you once you come in store?" },
			{ "Just to be sure, your name is ", "Please confirm that your name is " },
			{ "We currently do not have the item in stock, but we can send you an email once we have the item again. Please give us your email.",
					"We do not have the item right now, but we can send you an email if you leave us one down below once we got them restocked.",
					"Sorry, this item is out of stock, but we will give you a notification if you leave us an email right now when we have it again." },
			{ "To be sure, your email is ", "Please confirm that your email is ", "So your email is " },
			{ "Sorry, we do not have the item you requested. ",
					"Sorry, we do not have this type of item in our stores. ",
					"We believe that we do not have the item you have mentioned, sorry. " },
			{ "Our business hours and location is as following: " },
			{ "Please give us your order number.", "Could you give us your order number?",
					"What is your order number?" },
			{ "We have received information of your order, would you like to track your order or cancel your order?",
					"We have confirmed your order, do you wish to track your order or cancel your order?",
					"We have found your order, do you want us to track your order or cancel your order?" },
			{ "Could you give us your email so that we can send tracking information to you?",
					"Please give us your email for us to give any information regarding the package to you.",
					"We can give you tracking information if you could type in your email below." },
			{ "Do you really wish to cancel your order?", "Are you sure to cancel your order?",
					"Do you want to have your order removed?" },
			{ "Sorry, we cannot find your item order. ", "Sorry, there is no record of your order. ",
					"Sorry, I do not think your order number is correct. " },
			{ "Please leave us your comment below, you can leave anything here.",
					"You could leave your comment below, we accept any appraisals and criticisms.",
					"Type in your comments below, and we will be sure to make improvements." },
			{ "Got it. ", "No problem. ", "Understood. ", "Okay. ", "Alright. ", "Done. " },
			{ "Sorry, I do not understand what you mean, can you try again?", 
					"My bad, could you say it once more?",
					"Sorry, I do not understand what you want me to do. Could you come again?",
					"Sorry, I do not know what you want me to do, could you repeat to me one more time?",
					"I am sorry, that is far beyond my understanding",
					"I am sorry, what you are asking for is out of my duty."},
			{ "Thank you for using our customer service, goodbye.", "Thank you for using our service, see you soon.",
					"Thank you for contacting us, we look forward to your visit again.",
					"Thanks for using our online services, don't hesitate to ask us if you have more questions." },
			{ "Please enter your credit card number again." }, 
			{ "Please enter your name again." },
			{ "Please enter your email again." } ,
			{"ALL ACCESSORIES ARE ON SALE!! Please visit our physical store for more details.",
				"30% OFF for ACCESSORIES! visit our store for more details!", 
				"DON'T MISS THE SALE!! All accessories are 40% off!"}};

	public static String findResponse(String input) {
		String response = "";
		response = getResponse();
		if (main.getState() == 10 || main.getState() == 18 || main.getState() == 16) {
			main.setState((short)0);
			response += getResponse();
		}
		if (main.getState() == 4)
			response += input + "?";
		if (main.getState() == 7 || main.getState() == 9)
			response += input + ".";
		if (main.getState() == 5)
			main.setState((short)3);
		if (main.getState() == 11) {
			response += main.info;
			main.setState((short)0);
			response += getResponse();
		}
		if (main.getState() == 19 ||main.getState()==24)
			main.setState(main.getbkState());
		return response;
	}

	public static String getResponse() {
		return responses[main.getState()][(int) (Math.random() * responses[main.getState()].length)];
	}
}
