# SmokeSignals
CS56 Winter 2016 - Independent Android Project

### Project Description
Android app that allows you to automate several tasks that can be triggered by an receiving a SMS from a different phone

### Javadoc
file:///Users/briankim/Desktop/cs56-android-smoke-signals/javadoc/index.html

## Android Debug Databbase
https://github.com/amitshekhariitbhu/Android-Debug-Database

### Requirements
Android Studio

### Features
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

Ex:
![Before clicking](https://i.imgur.com/s7xqA4d.png)
![After clicking](https://i.imgur.com/nffqEPv.png)


## Project History
#### W16
- Fix the Ring feature, it rings for too long.
- Implement a BlackList feature
- Commands are too strict, make it check the substring instead. For example - "//Joke " will not trigger the joke feature -because it has a space after.
- WhiteList and BlackList should have a GUI that allows users to check boxes off in a list of contacts.
- More commands that check if other services are on or off.
- Add a RemindMe feature that sets an event on the calendar
- Debugging GUI
- Tune location
- Powersave
- Snap

#### F16
- Add feature that can make phone light flash on and off [#25](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/25)
- GUI for Debugging [#9](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/9)
- Add RemindMe Feature [#11](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/11)
- Allow user to choose how to run commands [#13](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/13)
- Add a toggle to switch all feature on or off [18](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/18)
- Improve UI [#24](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/24)

#### F17
- Refactor Settings [#48](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/48)
- Add unit testing [#47](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/47)
- Add a notification command [#46](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/46)
- Add a "Did you mean" feature for misspelled commands [#45](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/45)
- Keep a record of user actions [#37](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/37)
- Create user accounts [#36](https://github.com/UCSB-CS56-Projects/cs56-android-smoke-signals/issues/36)


### Credits
Original Author: [Pedro M. Sosa] 

Authors: Franklin Tang, Jackey Lau (W16)

Authors: Simon Wong, Sayali Kakade (F16)

Authors: Ankush Rayabhari, Porter Haet (F17)

Authors: Brian Kim, Juan Manzo (W18)
