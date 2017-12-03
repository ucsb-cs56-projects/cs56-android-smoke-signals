# SmokeSignals
CS56 Winter 2016 - Independent Android Project

Original Author: [Pedro M. Sosa]

Authors: Franklin Tang, Jackey Lau
Authors: Simon Wong, Sayali Kakade 

### Project Objective
Android app that allows you to automate several tasks that can be triggered by an incoming SMS

![Before clicking](https://i.imgur.com/s7xqA4d.png)
![After clicking](https://i.imgur.com/nffqEPv.png)

### Implemented Methods


Say you forgot your phone, Using someone else's phone you can text yourself:

- **"//Location" :**  and the phone will text you back with it's gps coordinates.
- **"//Contact [name]" :** and the phone will text you back with the stored contact info of anyone who fully or partially matches [name].
- **"//Battery" :** and the phone will text you back with its battery percentage and charging status.
- **"//Calls" :** and the phone will text you back the last phone calls it has gotten.
- **"//Ring" : (WIP)** and the phone will start ringing.
- **"//Help" :** Return the list of all possible 
- **"//SMS [number] m:[message]" :** Send an sms to [number] saying [message].
- **"//Joke" :** Send a random joke from an array of jokes from the phone's data base.
- **"//Wifi [arguemnt] :** and the phone will turn the wifi "on", "off", or will return the status if there is no argument. 
- **//Bluetooth [argument]:** and the phone will turn the bluetooth "on", "off", or will return the status if there is no argument.
- **//Status [argument]:** get the status of the "wifi", "bluetooth", or "all" using these arguments. 
- **//Powersave [function name]:** to turn off function. 
- **//WhiteList :** not a text but a function that allows the phone fucntions to be exclusive to the numbers that are saved on the phone.

### Ideas for Improvments

Essentially you can come up with tons of different things to automate, so you can use that phone "by proxy" through SMS from some other phone.

- **Custom Commands:** Commands shouldn't be hardcoded. The user should be allowed to change them.
- **Fix Ringing:** It should ring for say 2 minutes and then stop ringing as soon as the user interacts with the phone *(hint: you might find Intent android.intent.action.USER_PRESENT usefull)*
- **GUI :**Setup a GUI that lets you change commands to custom commands, blacklist, whitelists.
- **Blacklist:** which phones are prohibited to use our service.
- **//Location:** Use Fused location instead of GSM only. Maybe add some timer, so that if it can't find any GSM location it stops after certain time (otherwise it'll drain the battery)
- **//Snap:** Take a selfie and send media sms with the picture (mobile data has to be enabled)
- **//ScreenSnap:** Take a screenshot of what is going on your phone currently and send media sms with the picture
- **(NEW) //Joke:** Instead of having an array of jokes, use Whitelist as a base to read from a file to get the jokes. This allows the java file to look much cleaner.


### User Stories

Completed issues: 

- As a user, I can text my phone "Jokes" so that I can random grab a joke from a data base in the app. **150 points**
- As a developer, I can save the preferences of the settings after it has been made. **300 points**
- As a developer, I can provide a GUI to switch off functions so that I can turn off functions that I don't want to use. **300 points**
- As a user, I can text my phone to send a message to a phone number so that people will know I don't have my phone. **50 points**
- As a user, I can text my phone to send the Wifi state of my phone so I know if it's using battery life on Wifi. **75 points**
- As a user, I can text my phone to send the Bluetooth state of my phone so I know if it's using battery life on Bluetooth. **75 points**
- As a user, I can whitelist phone numbers to prohibit most people from using the app so that I can maintain privacy. **100 points**

To be implemented: 

- As a user, I can text my phone to turn off functions to save battery life.**100 points**
- As a developer, I can put jokes in a text file to read from so that I can allow the java file look cleaner. **50 points**
- As a developer, I can provide a screen to record activies so that I can debug activities and show the actions of the app to the user.
- As a user, I can blacklist people so that I can maintain privacy. **100 points**
- As a developer, I can improve the whitelist function by allowing to add people from contacts or numbers with symbols. **100-300 points**
- As a developer, I can make a blacklist function to be able to stop specific numbers from using the app's functions. When the blacklist setting is on, the whitelist setting is automatically turned off. **100 points**
- Further issues and point values can be found in the issues section of the repo. 

### F16 Final Remarks

Currently, everything in the code that is a part of the implemented methods and implemented user stories works. The app compiles and runs and all of these functions return the correct output. Additionally, leniency has been implemented to make the commands less strict, so texting a variation of the commands will either provide the help text or still continue to work. 

Potential features to be added: Besides the issues and features listed above, a few new issues that we added were

- Add feature that can make phone light flash on and off (useful for when user is searching for a lost phone or using the ring function) 
- Improve UI by making the interface more visually pleasing by potentially using more of the empty white space, etc. 
- Add a toggle to switch all features on or off so users don't have set them individually 

Existing bugs: 

- In whitelist, make sure to test and potentially modify the two different functions (SAVE and SAVE NUMBER) for adding numbers to the whitelist, because in making the whitelist less strict, bugs could have been introduced when adding numbers to the whitelist under different situations
- Again in whitelist, a bug that we didn't get around to fixing was that duplicate numbers are still added to the file

Opportunities for refactoring: 

- You could try seperating the different functions that the application provides into seperate classes, in coordinance with the Single Responsibility design principle, so that adding new functions and making changes to existing functions doesn't require that you modify multiple parts of the SMSRequestManager class

Advice for working with code/legacy code in general: 

- Start by looking at the Java files in the "app/src/main/java/com/konukoii/smokesignals" directory
- Then, check out the corresponding .xml files for the layout of the application in the "app/src/main/res/layout" directory
- Try to research and understand the different APIs that come with the code so that you can optimize the way you use the provided functions in the APIs 
- Read the Android tutorial in the UCSB CS56 projects page 
- Make sure that when you make in the Java portion of the code, you also change the layout in the .xml files if needed 
- Good luck! 
