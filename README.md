# fossdecrypt

Distribution to edit sav-files

Requirements:
- Java 1.8
- Git
- Console

Prepare executables:
1. Copy repo
2. run: gradlew build
3. go to folder Release

Usage
After there for windows, no shell file for linux. Yet, you may create it by yourself.
4. copy there a SAV-file, i.e. Vault1.sav
5. run: fossed.cmd Vault1.sav
...got a js-file with all your saved data
6.1. run: fossed.cmd Vault1.js
...got a new.Vault1.sav with dwellers 10-stats and bunch of weapons & outfits in storage
6.2. run: fossed.cms Vault1.js 30
...got a new.Vault1.sav same as in 6.1, yet with new dwellers up to total of 30

Enjoy!

To make changes you may use an Eclipse:
1. run: gradlew eclipse
2. open Eclipse and import the project

Thanks for original project for cipher schema!
