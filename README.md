# SmokeSignals
CS56 Winter 2016 - Independent Android Project

### Project Description
Android app that allows you to automate several tasks that can be triggered by an receiving a SMS from a different phone

### Javadoc
Insert here

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

#### W18
- Implement //Snap [#8] https://github.com/ucsb-cs56-projects/cs56-android-smoke-signals/issues/8
- Add RemindMe Feature [#11] https://github.com/ucsb-cs56-projects/cs56-android-smoke-signals/issues/11
- Make package name proper [#53] https://github.com/ucsb-cs56-projects/cs56-android-smoke-signals/issues/53
- Add feature that can make phone light flash on and off [#25] https://github.com/ucsb-cs56-projects/cs56-android-smoke-signals/issues/25
Total Points Possible

## Remarks
#### W18 Final Remarks
This application is basically utilizing the Broadcast Reciever and Room Persistance Library. Highly reccomend to look at the storage file to understand hwo the Room Persistance Library works. If you understand these two topics well, adding new features and fixing issues will become very simple(this project is still on its early phase).

When we first go the app, none of the use cases worked. We added a thread to the SMSManager that fixed this issue. Currently the app compiles and runs with no bugs and all the funtions return what they are intended to return. We highly advise you guys work on Unit Tests as soon as possible. It is crucial before the application gets any bigger. 

Existing bugs
 - It's a very good idea to add unit tests to make sure the program is working
 
Features we added
 - Added a toggle switch that turns all the settings off or on
 - Added a delete and reset button to the whitelist to delete a number or all numbers.
 - Fixed the app crashing when SMSmanager tried to intercept a text message.
 
 Features to add
 - New functions that a user can text for their phone to do
  -Ex. Toggle flashlight, text back a lists of apps open the phone.
 - Think of a better way than the thread to fix the crashing of the app when a message is intercepted.
 - Rename the packages.
 
 Advice
  - If new to Android Studio, look up tutorial videos on how to navigate through Android Studio. Learn how to make layouts for an app and how to link those layouts to certain java classes.
  - Look over the java code for the app. Understand how the app intercepts the message within the SMSManager class.
  - Play with the app. Test out its current functions and try to understand how these functions work.


### F17 Final Remarks
Currently the codebase is in decent condition, and the android app is bug free. The bulk of the work that was done this quarter was refactoring the old code into something more maintainable. The guiding principle we followed in refactoring was that it should be easy for future code editors to add new commands, etc. There is some code that used topics not covered in class, such as reflection and Android’s Room Library over SQLLite. Reflection is used `CommandManager.java` to be able to execute a command when given a SMS command. Room is used in the storage package to store data in a Sqlite database. I recommend reading up about it [here](https://developer.android.com/topic/libraries/architecture/room.html). Besides those two things, the code is fairly straightforward. Running the Android app can be a little tricky; I would ask your mentor if you run into trouble with any Android related issues. The gist of it is setting up an emulator.

How the Code Works:
There are two flows the user can use. 
- The first is texting to control the device. A broadcast receiver inside the manifest file sets up a BroadcastReceiver for text messages. The listener is defined in SMSRequestManager. It initializes a command manager to load up the commands and then parses the body of the text into a command. It then calls the execute method of the appropriate command passing in the args.
- The second is interacting with the app settings by opening it up. The class to look for is MainActivity which directs the user to the settings activity or the whitelist activity. From there, look at the Settings or Whitelist class.

Refactoring opportunities:
- The Activity classes are a little all over the place. By the Single Responsibility Principle, they would just manage the UI and related events. The “back end” code that runs when the events happen in the UI should be refactored into different files.
- In the storage package, there is some boiler plate code that is necessary in order to use Room. That could be refactored into its own package.
- Ideally, settings should be reworked as we didn't get a chance to refactor it this quarter. It uses SharedPreferences to store the data which is okay but the frontend and backend are coupled together. Try and follow Single Responsibility Principle when refactoring it.
- Maybe look into how to switch from inheritance to composition for the command validators.

New features:
- Take a look under issues. Previous quarters have added a lot of fun issues that you can tackle. A lot of them involve looking at a new API like the Camera or reworking another feature. Use the Android docs/online tutorials as a resource to learn more about them. Always look up what features are supported on your min API level as that dictates which new features you can use.
- We didn't get a chance to write unit tests for our code. Please take a look into how to do that!

Advice
- The code is in `app/src/main/java/com/konukoii/smokesignals`. Using Android Studio is highly recommended to edit the code.
- Instead of using ant, you will use Gradle. Gradle uses `build.gradle` instead of `build.xml`. There are two `build.gradle`s, a `build.gradle` in the root directory and `app/build.gradle`. If you are making edits you most likely want to edit `app/build.gradle`. You should only have to do this if you are updating dependencies or increasing the min API level for the app.
- The UI is constructed by the files in `app/src/res/layout`. If you are unfamiliar with this I’d take a look [here](https://developer.android.com/guide/topics/ui/declaring-layout.html)
- Any strings/hardcoded values should go into `app/src/res/values/strings.xml`.
- Adding a command is relatively easy: update the string array in the strings file above and then create the appropriately named class in the commands package. See `CommandManager.java` for how that gets instantiated.
- 

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

### Credits
Original Author: [Pedro M. Sosa] 

Authors: Franklin Tang, Jackey Lau (W16)

Authors: Simon Wong, Sayali Kakade (F16)

Authors: Ankush Rayabhari, Porter Haet (F17)

Authors: Brian Kim, Juan Manzo (W18)
