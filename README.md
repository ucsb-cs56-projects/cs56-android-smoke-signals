# SmokeSignals#
CS56 Winter 2015 - Independent Android Project

Original Author: [Pedro M. Sosa]

Authors: Franklin Tang, Jackey Lau

<h2>Project Objective</h2>
Android app that allows you to automate several tasks that can be triggered by an incoming SMS

<h2>Implemented Methods</h2>
Say you forgot your phone, Using someone else's phone you can text yourself:
- **"//Location" :**  and the phone will text you back with it's gps coordinates.
- **"//Contact [name]" :** and the phone will text you back with the stored contact info of anyone who fully or partially matches [name].
- **"//Battery" :** and the phone will text you back with it's battery percentage.
- **"//Calls" :** and the phone will text you back the last phone calls it has gotten.
- **"//Ring" : (WIP)** and the phone will start ringing.
- **"//Help" :** Return the list of all possible 

<h2>Ideas for Improvments</h2>

Essentially you can come up with tons of different things to automate, so you can use that phone "by proxy" through SMS from some other phone.

- **Custom Commands:** Commands shouldn't be hardcoded. The user should be allowed to change them.
- **Fix Ringing:** It should ring for say 2 minutes and then stop ringing as soon as the user interacts with the phone *(hint: you might find Intent android.intent.action.USER_PRESENT usefull)*
- **GUI :**Setup a GUI that lets you change commands to custom commands, blacklist, whitelists, turn certain commands on or off.
- **Whitlist/Blacklist:** which phones are allowed to use our service.
- **//Location:** Use Fused location instead of GSM only. Maybe add some timer, so that if it can't find any GSM location it stops after certain time (otherwise it'll drain the battery)
- **(NEW) //Powersave:** Turn off/on Wi-Fi or Mobile Data
- **(NEW) //Snap:** Take a selfie and send media sms with the picture (mobile data has to be enabled)
- **(NEW) //ScreenSnap:** Take a screenshot of what is going on your phone currently and send media sms with the picture
- **//Battery:** Add info regarding if charging/discharging status
- **//SMS [number] m:[message] :** Send an sms to [number] saying [message].

<h2>User Stories</h2>

- As a user, I can text my phone "Jokes" so that I can random grab a joke from a data base in the app.
- As a user, I can specify who can use certain functions so that I can maintain privacy.
- As a developer, I can provide a GUI to switch off functions so that I can turn off functions that I don't want to use.
- As a developer, I can provide a screen to record activies so that I can debug activities and show the actions of the app to the user.
- As a user, I can text my phone to send a message to a phone number so that people will know I don't have my phone. 
