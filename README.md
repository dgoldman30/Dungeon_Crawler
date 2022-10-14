# Functionality
* For this prototype, we designed a text-based Rouge-like game. In this game, you are able to explore a 10 x 10 map with a customized character. To customize your character, you will be prompted to enter a race, caste and favored attributes. Enemies will move towards you as you explore the map.  When an enemy is in proximity, you engage in automated combat. If you defeat an enemy, another one will randomly spawn, and you can keep engaging in combat until you die or quit.
* Limitations: We have limited combat to one player and one non-playable enemy. Another limitation is the exploration of the map. Right now, you can only explore 100 possible tiles. In the future, we would like to automatically generate map space, and make it larger.
* Assumptions: We assume that the user is able to interact with a text-based UI and scanner to move around the map.
# Usage Instructions
* The class containing the main() method is Game.java
1) Select a Race from the presented list, by inputing the value associated with each Race and hitting enter.
2) Select a Caste from the presented list, by inputing the value associated with each Caste and hitting enter.
3) Apply 4 additional attribute points from the presented list, by inputing the value associated with each attribute and hitting enter after each input. 
4) Use characters a,s,w,d to explore the map, "exit" to quit the game, and "x" to open inventory. 'P' is the character on the map indicating where the player is. 'E' is the character indicating enemy. 
5) If you are engaged in combat, your character will attack and then your enemy will attack. 
6) There are two ways to exit the game: Input "exit" while exploring the map, or your character must die. 

