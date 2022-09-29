# Use Case Name
Pickup item

# Scope
The Game

# Level
User goal

# Primary actor
The user

# Stakeholders and interest
The user cares about this function, so they can interact with items on the ground and dropped by enemies. The programmers care about this function so the game is fun and addictive.

# Preconditions
- The user needs a character
- The character needs to be standing in the same square as an item on the ground

# Postconditions
The user has looted the item(s) on the ground

# Main success scenario
1. An item on the ground is selected by the user
2. The character moves to it and a loot window is brought up
   3. If the character is occupying the same tile as loot, a "loot nearby" button will appear and allow for looting
3. The user chooses what to loot
4. The loot window is closed

# Extensions
- The character's inventory is full, and they must drop something (3)
- There isn't any pickup-able item on the ground (1)
- The character can't loot the selected item (3)

# Special Requirements
Touch screen UI on an andriod device.

# Technology and data variations list
Character Health, XP, fighting ability

# Frequency of occurrence
High priority as users will be consistently moving in the game to collect items.

# Miscellaneous