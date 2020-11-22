# **Dead Ahead**

## Zombie survival game

In this game, the player will control a character that will be able to move in 
4 directions in 2D. The point of the game is to **kill the numerous zombies** that 
will be crawling towards the player from all directions using the weapons available. 
**Survive** for as many rounds as you can and beat your personal record!

*Target audience*:
- Children and teenagers
- Gamers who enjoy retro games
- *Anyone* who just wants to kill some free time with a fun game!

**Motivation behind the project**:
I have always been passionate about game development because it lets me merge my
creativity and technical skills to build meaningful products that I care about and
that allow me to have hours of fun developing and using. 

**User stories**:

Phase 1:
- As a user, I would like to be able to collect weapons from the field and
add them to my weapon inventory.
- As a user, I would like to change my current weapon for other weapons in
my inventory.
- As a user, I would like to be able to create a new account in the game
- As a user, I would like to be able to log into the game with my username 
and password

Phase 2:
- As a user, I would like to save my gameplay and account information to file
- As a user, I would like to load my gameplay and account information from file 

Phase 4: Task 2
I made the class Arsenal robust by modifying the only method that had a requires 
clause to make it throw an exception instead. The method is called dropWeapon and
it takes a Weapon as an argument that will be dropped from the arsenal. If the weapon
is not contained in the arsenal, then the method will throw a WeaponNotFoundException. 
The three last three test methods in ArsenalTest test this behavior. The first of these 
two methods, testDropWeaponNotHandGun and testDropWeaponHandGun, test the method when 
a weapon contained in the arsenal is dropped. The test method testDropWeaponNotThere tests
the method when the weapon passed in is not in the arsenal.

Phase 4: Task 3
 - The first clear improvement that could be done if I had more time would be to reduce coupling 
 present in some cycles of my UML diagram. There is an unnecessary cycle between the Bullet class, 
 the player class, and the Direction enumeration. I could delete the association between bullet and
 direction and only use player in Bullet to determine the direction of the bullet. I could also eliminate the cycle
 between Account, Player, and Arsenal. The account only has to be associated to the Player class and use the Player
 field to access the player's arsenal. By reducing these two cycles I can effectively reduce the coupling between
 my classes and make it easier to refactor in the future. 
 - I could implement the Observer pattern for the association between DeadAhead (the game class) and Player. The player
 can be at different states and positions that can change. The game class DeadAhead has to keep track of these changes in state.
 I could extend and implement the observable and Observer class and interface to apply the observer pattern to this association
 and thus reduce the coupling in my project.
