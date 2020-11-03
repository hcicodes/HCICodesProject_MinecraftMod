#  HCI Codes - Mod
A work in progress mod for Minecraft 1.15.2
Humberside's Coding Club collaborative project


## Environment Setup
This section assumes that you use __Windows__.
You only have to do the steps listed here __ONCE__.

### Video
[This](https://www.youtube.com/watch?v=H55ClYTdQEI) video is a great resource that will walk you through these steps. __DO NOT__ follow any instructions about downloading forge - this will mess up the project. Whenever the forge folder is mentioned, know that it is where you cloned the git repo.

### Programs
1. Download the JDK 8 from [here](https://www.oracle.com/ca-en/java/technologies/javase/javase-jdk8-downloads.html).
	1. Click on either the Windows x86 or Windows x64 link, depending on which architecture your system has.
	2. You will be presented with a login page. You have two options:
		1. Create an account, go back to the page, click on the download link, and login with the account you just created.
		2. Go to [this link](http://bugmenot.com/view/oracle.com) and try one of the logins on the Java login page.
	3. Once downloaded, double click the installer, and follow the instructions.
2. Download Eclipse(IDE we are using for this) from [here](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-09/R/eclipse-inst-jre-win64.exe). Once downloaded, double click the installer exe.
3. In the installer, click the top result in the list. On the next screen, click install.

### Variables & Commands
4. We now have to setup Java's environment variables. I can't explain this in words, so [here](https://youtu.be/104dNWmM6Rs?t=109) is a video that will tell you what to do. I understand it is a pretty crappy video, but this is what I could find.
5. We are almost done now. Open command prompt, and use cd to navigate to where you cloned the git repo. There should be a file in here called gradlew. You need to run two commands.
`gradlew genEclipseRuns`
This will take a litle while. Once this is done, run this second command.
`gradlew eclipse`

### Eclipse Setup
6. Open eclipse up. You will get a window asking you to set a workspace. Set this to any folder that you'd like, it does not matter. Click OK.
7. Once eclipse has finished loading, you will be presented with a welcome screen. Click the X beside the welcome tab.
8. On the right hand side, where the package manager pane is, click 'Import Projects'.
9. In the window that pops up, click the small arrow presented beside the folder icon, and the name 'General'. From there, click 'Existing Projects into Worksapce', then click 'Next' at the bottom of the screen.
10. In the top right corner, click browse. In the explorer pane that opens up, browse to where the git repo is cloned. There should be some folders in here, such as 'src' and 'run'. Once you have browsed to the folder, click 'Select Folder' in the bottom right corner.
11. Click 'Finish'. Click the arrow on the right hand side of the screen, next to the folder icon that has just appeared.
12. Right-click the top item in the package explorer on the left side of the screen. It the context menu, under 'Build Path', click 'Configure Build Path'.
13. In the screen that pops up, click the 'Source' tab on the top of the window. Scroll down until you see <git-repo-path>/src/test/java. Click it, then click 'Remove on the right side of the window. Do the same with <git-repo-path>/src/test/resources.
14. Click 'Apply', and then 'Apply and Close'.
12. You're done! This was quite a lengthy process, but this only has to be done once. You can start programming now, under the src/main/java folder on the left side of the screen. Message me, Kittymatthew#7920 on discord if you need any help, or just post in the appropriate HCI project channel.

## Building
__!!!__ Please make sure to pull before starting to program anything! Failure to do so will result in conflicts, where your code will probably be deleted. You have been warned! __!!!__

1. Save all modified files that you have edited/created.
2. Click the green arrow above the package explorer.
3. Click 'runClient' (this only has to be done once).
4. The game will boot up like normal, but with the mod loaded.
5. Save your files, make any changes, then commit and push to this repository.
