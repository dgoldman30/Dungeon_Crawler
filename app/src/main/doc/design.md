# Sequence Diagrams

# onCombat()
```plantuml
@startuml
hide footbox

actor System
participant "explore : ExploreFragment" as exploreFragment
participant "logLayout : LinearLayout" as logLayout
participant "pc : Player" as pc
participant "enemy : NPC" as enemy

System -> exploreFragment: onCombat()
exploreFragment -> logLayout: addView(enterCombat)

alt pcToHit >= enemy.DV.value && (pc.STR.value * Math.random()) >= enemy.AV.value
exploreFragment -> pc: pc.weapon.strike(pc, enemy)
end

alt enemyToHit >= pc.DV.value && (enemy.STR.value * Math.random()) >= pc.AV.value
exploreFragment -> enemy: enemy.weapon.strike(enemy, pc)
end


alt enemy.HP.value <= 0
exploreFragment -> pc: pc.experience += enemy.level * 10
exploreFragment -> exploreFragment: replaceEnemy(), setXpProgress()
end

alt pc.HP.value <= 0
exploreFragment -> exploreFragment: youDied()
end

exploreFragment -> logLayout: addView(combatRound)

@enduml
```

# youDied()
```plantuml
@startuml
hide footbox

actor System

participant "exploreView : ExploreFragment" as exploreView
participant "exploreLayout : ConstraintLayout" as exploreLayout
participant "restartButton : Button" as restartButton
participant "ControllerActivity : Activity" as Controller
participant "mainView : MainView" as mainView

System -> exploreView: youDied()
exploreView -> exploreLayout: setVisibility(LinearLayout.GONE)
exploreView -> restartButton: setVisibility(Button.VISIBLE)

restartButton -> Controller: onRestart()
Controller -> mainView: .display(charCreationFragment)

@enduml
```

# addToLog()
```plantuml
@startuml
hide footbox

actor System
participant "explore : ExploreFragment" as exploreFragment
participant "log : ScrollView" as log
participant "logLayout : LinearLayout" as logLayout

System -> log: addToLog()
exploreFragment -> log: addView(logLayout)
exploreFragment -> logLayout: addView(entry)


@enduml
```

# clearLog()
```plantuml
@startuml
hide footbox

actor System
participant "explore : ExploreFragment" as exploreFragment
participant "logLayout : LinearLayout" as logLayout

System -> exploreFragment: clearLog()
exploreFragment -> logLayout: clearAllViews()

@enduml
enduml```


# Class Diagram

```plantuml
@startuml

hide footbox
abstract class Character {
    level : int
    race : Race
    caste : Caste
    myChar : char
    location : Tile
    
    sprite : int
    maxHP : int
    x : int
    y : int
    
    attributes : Attribute[]
    skills : ArrayList<Skill>
    items : Item[]
    inventory : Tile[]
    attunedSpell : Spell

    --
    public void calcStats()
    public void xpIncrement()
    public String levelUp()
    public void occupy(tile : Tile)
    public void executeMove(tile : Tile)
    public void remove(tile : Tile)
    public void equipWeapon(item : Item)
    public String drinkPotion() 
  
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
    int xpBase
    int scale
    int xpToLevel
    --
    public void setXpToLevel()
    public String move(game : Game, input : String)
}

Character <|-- NPC
Character <|-- Player

enum Race {
    favoredSkills : ArrayList<Skill>
    attributeAdjustments : int[]
    name : String
    description : String
}

enum Caste {
    favoredSkills : ArrayList<Skill>
    startingEquipment : ArrayList<Item>
    description : String
    name : String
}

abstract Item {
    description : String
    name : String
    disp : char
    equipable : boolean
    --
    abstract Item drop()
    public String getName()
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
    damage : double
    accuracy : double
    crit : double
    twoHanded : boolean
    enum Weapons
    --
    public double strike(user : Character, target : Character)
}

enum Weapons {
    DAGGER
    SWORD
    BRASSKNUCKLES
    HARPOON
    SABRE
    CLUB
    BALLANDCHAIN
    HAMMER
    MACE
} 
Weapons -> Weapon

class Potion {
    name : String
    factor : int
    target : int
    enum Potions
    --
    public String drink(target : Character)
    public String getName()
    
}
enum Potions {
    HEALTH
    DEX
    STR
    INT
    WILL
}

Potions -> Potion

class Armor {
    encumberance : double
    AV : double
    enum Armors
    --
    public String getName()
}

enum Armors {
    CHAIN
    LEATHER
    PLATE
    ROBES
    RAGS
    CLOTHES    
}

Armors -> Armor

class Tile {
    available : boolean
    occupant : Character
    display : character 
    x : int 
    y : int 
    floor : R.drawable
    ladder : R.drawable
    --
    public char display()
    public void toStairs()
}

Item <|-- Weapon
Item <|-- Potion
Item <|-- Armor

class Game {
depth : int
gameState : GameStates
enemiesCleared : int
map : Tile[][]
enemy : NPC 
pc : Player
--
public boolean checkAdjacent()
public void createMap(sizeX : int, sizeY : int)
public static void createSkills()
}


package View {
}

'associations
Game "1" -> "1" Player : \tHas()\t
Game "1" -> "1" NPC : \tHas()\t
Game "1" -> "11" Skill : \tcontainsListof()\t
Caste "1" -> "1" Game : \tgetsCastePresets()\t
Race "1" -> "1" Game : \tgetsRacePresets()\t
Game "1" -> "100" Tile : \tcontainsMapOf()\t
View "1" -> "1" Game : \tcontains()\t


@enduml
```