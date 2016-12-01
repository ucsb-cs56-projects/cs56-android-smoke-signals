# SmokeSignals#
CS56 Winter 2016 - Independent Android Project

Original Author: [Pedro M. Sosa]

Authors: Franklin Tang, Jackey Lau
Authors: Simon Wong, Sayali Kakade 

<h2>Project Objective</h2>
Android app that allows you to automate several tasks that can be triggered by an incoming SMS

<h2>Implemented Methods</h2>
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

<h2>Ideas for Improvments</h2>

Essentially you can come up with tons of different things to automate, so you can use that phone "by proxy" through SMS from some other phone.

- **Custom Commands:** Commands shouldn't be hardcoded. The user should be allowed to change them.
- **Fix Ringing:** It should ring for say 2 minutes and then stop ringing as soon as the user interacts with the phone *(hint: you might find Intent android.intent.action.USER_PRESENT usefull)*
- **GUI :**Setup a GUI that lets you change commands to custom commands, blacklist, whitelists.
- **Blacklist:** which phones are prohibited to use our service.
- **//Location:** Use Fused location instead of GSM only. Maybe add some timer, so that if it can't find any GSM location it stops after certain time (otherwise it'll drain the battery)
- **//Snap:** Take a selfie and send media sms with the picture (mobile data has to be enabled)
- **//ScreenSnap:** Take a screenshot of what is going on your phone currently and send media sms with the picture
- **(NEW) //Joke:** Instead of having an array of jokes, use Whitelist as a base to read from a file to get the jokes. This allows the java file to look much cleaner.


<h2>User Stories</h2>

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
-Further issues and point values can be found in the issues section of the repo. 

<h2>F16 Final Remarks</h2>


