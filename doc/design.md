# Domain Model
```plantuml
@startuml

hide circle
hide empty methods

'classes
class Character {
    race
    caste
    attributes
    skills
    items
    inventory
    location
}

class Caste {
    favoredSkills
    baseAttributes
}

class Item {
    description
}

class Attribute {
    value
    name
}

class Skill {
    value
    description
    toggled
}

class Weapon {
    damage
    accuracy
    description
}

class Spell {
    range
    description
    effect
}

class Attack {
    hit?
    damage
    range
}

class Game {
    map[TileR][TileC]
}

class Tile {
    contents
}



'associations
Character "1" - "1" Item : \tEquips-item\t
Character "1" - "*" Item : \tHas-in-inventory\t\t

Character "1" --- "5" Attribute : \tContains\t\t

Character "1" -left- "1" Spell : \tMemorized\t
Character "1" -left- "*" Spell : \tLearned\t

Character "1" --- "*" Skill : \tTrained\t
Character "1" --- "*" Skill : \tToggled\t

Character "1" --- "1,2" Weapon : \tHas-equipped\t

Character "1" --- "1" Attack : \tExecutes\t


@enduml

```
# Sequence Diagrams
start-game
```plantuml

@startuml
hide footbox

actor User
participant "c1 : Character" as character
participant "race : Race" as race
participant "caste : Caste" as caste
participant "game : Game" as game
participant "attributes[i] : Attribute" as attribute

User --> character : createCharacter()
character --> race : race = pickRace()
character --> caste : caste = pickCaste()
character -> game : game = createGame()
character -> attribute : attribute[i] = setAttribute()
@enduml
```

move
```plantuml
@startuml

hide footbox

actor User
participant "location : Tile" as loc
participant "c1 : Character" as char

User -> loc : selectTile()
char -> loc : moveTo()



@enduml
```

updateGame
```plantuml
@startuml

hide footbox

actor System
participant "g1 : Game" as Game
System -> Game : updateGame()
@enduml
```

cast-spell
```plantuml
@startuml

hide footbox

actor Character
participant "s1 : Spell" as spell
participant "t[i][j] : Tile" as target

Character -> spell : castSpell()
spell -> target : target = selectTile()

@enduml
```

attack
```plantuml
@startuml

hide footbox

actor Character
participant "e1 : Character" as enemy

Character -> enemy : attack()
@enduml
```

# Class Diagram

```plantuml
@startuml

hide footbox

'classes
abstract class Character {
    level : int
    race : Race
    caste : Caste
    myChar : char
    location : Tile
    dodgeValue : int
    armorValue : int 
    mental : int
    attributes : Attribute[]
    skills : ArrayList<Skill>
    items : Item[]
    inventory : Tile[]
    attunedSpell : Spell
    --
    public Tile[][] move(map : Tile[][])
    public void occupy(tile : Tile)
    public void executeMove(tile : Tile)
}

class  NPC {
    target : Character
    hostile : boolean
    --
    public void setTarget(target : Character)
    public Tile[][] move(map : Tile[][])
    public void occupy(tile : Tile)
}

class Player {
    experience : int
    --
    
    public String move(game : Game, input : String)
}

Character <|-- NPC
Character <|-- Player


class Race {
    favoredSkills : ArrayList<Skill>
    attributeAdjustments : int[]
    name : String
    description : String
}

class Caste {
    favoredSkills : ArrayList<Skill>
    startingEquipment : ArrayList<Item>
    description : String
    name : String
}

abstract Item {
    description : String
    name : String
    --
    abstract Item drop()
}

class Attribute {
    value : int
    name : String
    description : String
    --
}

class Skill {
    value : float
    name : String
    description : String
    toggled : boolean
    aptitude : int
    --
    public void increment()
    public void toggle()
}
class Weapon {
damage : int 
accuracy : int 
twoHanded : boolean

}
class Potion {
attributeMulti : int 
usageTime : int 
}

class Tile {
    available : boolean
    occupant : Character
    display : character 
    x : int 
    y : int 
    public char display()

}
class TextUI {
    game Game
    --
    public Player characterCreation()
    public Race pickRace()
    public Caste pickCaste()
    public int[] pickAtt()
    public String displayMap(map: Tile[][])
    public void moving(game: Game)
    public void combat(c1 : Character, c2 : Character)
    public String characterScreen(pc : Player)
    }

Item <|-- Weapon
Item <|-- Potion



'Here we're going to need to add associations between classes and specify now. We can do this while coding.
'Association between Game and Player
class Game {
int gameState
map : Tile[][]
enemy : Character 
{static} pc : Player
{static} skills : ArrayList<Skill> 
{static} weapons : Weapon[]
{static} potions : Potion[]
{static} castes : Caste[]
{static} gladSkills : ArrayList<Skill>
{static} gladItems : ArrayList<Item>
{static} urSkills : ArrayList<Skill>
{static} urItems : ArrayList<Item>
{static} woodSkills : ArrayList<Skill>
{static} woodItems : ArrayList<Item>
{static} fishSkills : ArrayList<Skill>
{static} fishItems : ArrayList<Item>
{static} appSkills : ArrayList<Skill>
{static} appItems : ArrayList<Item>
{static} clerSkills : ArrayList<Skill>
{static} clerItems : ArrayList<Item>
{static} races : Race[]
{static} humSkills : ArrayList<Skill>
{static} humAtt : int[]
{static} minSkills : ArrayList<Skill>
{static} minAtt : int[]
{static} dwSkills : ArrayList<Skill>
{static} dwAtt : int[]
{static} sprSkills : ArrayList<Skill>
{static} sprAtt : int[]
{static} nySkills : ArrayList<Skill>
{static} nyAtt : int[]
{static} orcSkills : ArrayList<Skill>
{static} orcAtt : int[]
{static} kSkills : ArrayList<Skill>
{static} kAtt : int[]



--
public Game updateGame()
public String toString()
}



@enduml
```