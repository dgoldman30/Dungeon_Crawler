# Testing 
* The prototype was tested by identifying values or arrays that may trigger an exception. We found these values and created solutions that are foundational and defensive. 
* We also accounted for invalid inputs/index out of bounds inputs and made it difficult to break the game. 
# Transcript
1) System: "Please Select a Race from the list below
   0: Human
   1: Minotaur
   2: Spriggan
   3: Dwarf
   4: Nymph
   5: Orc
   6: Kenku"
2) Expected User input: any int 0-6
3) System(expected input): "You have selected Race: {Race} Each caste favored skills and a set of starting equipment
   Please select a caste from the list below
   0: Gladiator
   1: Urchin
   2: Woodsman
   3: Fisherman
   4: Apprentice
   5: Clergyman"
4) System(unexpected input): "Invalid, input try again" refer to 1
5) Expected User input: any int 0-5
6) System(expected input): "You have selected {Caste}
   You get to assign 4 additional attribute points to your character
   Select 1 for STR, 2 for DEX, 3 for INT, and 4 for WILL"
7) System(unexpected input): "Invalid, input try again" refer to 3
8) Expected User input: any int 1-4
9) System(expected input): "You have increased your attribute" 6,8,9 repeated 4 times if expected input
10) System(unexpected input): "Invalid, input try again" refer to 6
11) Enter an action:
    - W, A, S, D to move/attack
    - X to open character sheet
    - "exit" to exit game
    {2d array map with player and enemy}
12) Expected user inputs:
- "X": {display character info and inventory} 
- "A": {display map} "you moved left." //// if touching wall - "You are at the map edge. You cannot move left."
- "S": {display map} "you moved down." //// if touching wall - "You are at the map edge. You cannot move down."
- "W": {display map} "you moved up." //// if touching wall - "You are at the map edge. You cannot move up."
- "D": {display map} "you moved right." //// if touching wall - "You are at the map edge. You cannot move right."
- "exit" close the program
13) Unexpected user input: {new map and player loses move due to misinput} 11, 12, 13 repeated until combat
14) If enemy encountered: System: "You have entered combat. Enter an input to attack the enemy"
15) Expected User input: *any input* 
16) System : "{Race} {Caste} hits {eRace} {eCaste} for * damage, leaving remaining HP at *
    {eRace} {eCaste} hits {Race} {Caste} for * damage, leaving remaining HP at *."
17) Expected User input: *any input* 
18) System (if player kills enemy): "{Race} {Caste} hits {eRace}  {eCaste} for * damage, killing them. All enemies are dead. Generating a new enemy." 14, 15, 16, 17, 18 repeated until "exit" or player killed
19) {displays map with new enemy in random position}
20) System (if enemy kills player): "{Race} {Caste} hits {eRace} {eCaste} for * damage, leaving remaining HP at *
    {eRace} {eCaste} hits you for * damage, killing you" /// program ends

