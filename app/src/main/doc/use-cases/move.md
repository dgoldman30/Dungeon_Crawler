# Use Case Name
Move

# Scope
The Game

# Level
User goal

# Primary actor
The user

# Stakeholders and interest
The user cares about this function, so they can play the game effectively. The programmers care about this function so the game is interactable.

# Preconditions
Create a character and start the game.

# Postconditions
The user should be able to move their character to the location in the map where they press the screen.

# Main success scenario
The user inputs the location they want to move to by tapping the location in the GUI and their selected character will move to that location in the game.
1. User selects a tile on their screen
2. If there are no hostiles on screen, the character will move to the tile

# Extensions
- If an item was selected, open the loot window and [pickup item](file:///C:/CMPU-203/cmpu203-team-b/dpc/use-cases/pickup-item.md)
- If an enemy was selected or the character attempts to move onto an enemy/immovable object perform an [attack](file:///C:/CMPU-203/cmpu203-team-b/dpc/use-cases/attack.md)
- If an enemy enters the character's vision while moving, the character will halt
- If an enemy is in the character's vision, the user enters combat and can only take one action per turn (move/attack/use item/cast spell)


# Special Requirements
Touch screen UI on an android device.

# Technology and data variations list
Where the screen is touched on the touch screen UI.

# Frequency of occurrence
High priority as users will be consistently moving in the game to fight enemies and collect items.

# Miscellaneous