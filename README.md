# COSC-310-22299382-Assignment-3

### The chatbot program has three classes: findstate, response and main.
1. Class "main": public class main
* This class consist of code which implemented states to determine what responses are given to the user according to a given state. There are 24 states in total. Our bot gives responses and provide actions based on the the user input, by searching existence of certain keywords used in the user input. If a user gets to a point where the bot notices it cannot go any further (e.g. the action is fully completed) the bot reverts to the default state which asks the user whether they have other requests or questions. There are also basic information stored, such as opening hours, shop location and a list of generic clothing items. There are 4 actions the bot can artifically synthesize as interactions with the user: The bot can find certain items for the user and ask the user to either have it shipped or reserved. The bot can give shop location and office hours. The bot can cancel and track orders using randomizer to determine whether a user has an order or not and whether a package is delivered, on its way or not yet shipped. The bot can receive feedback from users as well.
* Server socket is created to receive user's message via a socket (3535, need to be changed to the IP address where the chatbot at)
* Keyword list is extended for shortcuts and invoking manager class.
* items list "accessories" are newly added for expanding topics.
2. Class "response": public class response
* This class is responsible for accessing the right response based on the current state of the conversation.
3. Class "findstate": public clas findstate
* This class creates the flow of the conversation by determining the state of the current situation.
* Stanford toolkit (POS) is applied for creating shortcuts for verbs.
### The client interface consists of one classes: clientSide.
* This class is a designed GUI for user to interact with the chatbot. There is a scrollable text area, which shows the entire history of the conversation; There is also a textfield for user to input their speech. With the "send" button, user can send the message to the chatbot through a socket. (localhost - socket 3535, need to be changed to the IP address where the chatbot at)
### Manager interface is newly added: manager.
* This class is basically a server (other than the chatbot) for user to connect and communicate with. When client ask for real-person-representative, the chatbot would switch to this class and start up manager server for user to connect. After connection, manager GUI would appear on manager side (also on local host this time), and manager are ready to have conversation with the client.

### List of features added:
* -GUI created
* GUI added for increase user's experience in asethetic aspect.
* -extra topic added
* extra topics added for increasing flow of conversation (not easily off topic)
* -respsonses for out of topics expanded
* the responses are more unpredictable and randomized
* -Stanford POS toolkit applied
* cut down the time for searching responses for typical tasks (like searching, buying, reserving, etc.)
* -sockets applied in connection between client, chatbot, and manager
* it is created so that the client side now is totally independent of the chatbot
* -manager class added for real-time conversation with real representatives
* last resort for communication if the chatbot cannot satisfy the client.

### Features that can be extracted and share with others as API:
* socket connection of client, chatbot(class), and manager class
* application of stanford toolkit
* switching of socket(or IP address) connection
* server-client chatroom
* changing states and randomized responses system of the chatbot
