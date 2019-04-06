# COSC-310-G10-Assignment-2

### This chatbot program has three classes: findstate, response and main.
1. Class "main": public class main
* This class consist of code which implemented states to determine what responses are given to the user according to a given state. There are 24 states in total. Our bot gives responses and provide actions based on the the user input, by searching existence of certain keywords used in the user input. If a user gets to a point where the bot notices it cannot go any further (e.g. the action is fully completed) the bot reverts to the default state which asks the user whether they have other requests or questions. There are also basic information stored, such as opening hours, shop location and a list of generic clothing items. There are 4 actions the bot can artifically synthesize as interactions with the user: The bot can find certain items for the user and ask the user to either have it shipped or reserved. The bot can give shop location and office hours. The bot can cancel and track orders using randomizer to determine whether a user has an order or not and whether a package is delivered, on its way or not yet shipped. The bot can receive feedback from users as well.
2. Class "response": public class response
* This class is responsible for accessing the right response based on the current state of the conversation.
3. Class "findstate": public clas findstate
* This class creates the flow of the conversation by determining the state of the current situation.
