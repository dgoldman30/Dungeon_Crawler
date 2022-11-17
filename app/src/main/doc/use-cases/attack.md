# Use Case Name
Fight Enemy

# Scope
The Game

# Level
User goal

# Primary actor
The user

# Stakeholders and interest
User wants to be able to attack enemy 

# Preconditions
- There must be an enemy in sight of the character

# Postconditions
User attacks enemy and inflicts a specific amount of damage.

# Main success scenario
1. The user taps on an adjacent enemy (or a further enemy if their character wields a ranged weapon)
2. Both the character and the enemy perform their attack(s) on each other
3. They take whatever damage the attack dealt

# Extensions
- If the enemy is out of range or the character is out of ammunition for their ranged weapon, the character will [move](file:///C:/CMPU-203/cmpu203-team-b/dpc/use-cases/move.md)  in the direction the user tapped
- If the character takes lethal damage they die and receive a game over
- If the enemy takes lethal damage they die and drop loot and the character receives exp for the kill

# Special Requirements
- Functioning touch screen UI
- The user must have started the game

# Technology and data variations list
- graphical presentation of damage infliction
- Enemy health
- Player stamina

# Frequency of occurrence
Occurs frequently throughout a playthrough.

# Miscellaneous