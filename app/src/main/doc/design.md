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

'classes
abstract class Character {
    level : int
    race : Race
    caste : Caste
    myChar : char
    location : Tile
    x : int
    y : int

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
    public void equipWeapon(weapon : Weapon)
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
NPC <|-- Enemy


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
damage : double
accuracy : double
crit : double
twoHanded : boolean
enum Weapons

}
class Potion {
attributeMulti : int 
usageTime : int
enum Potions
}

class Tile {
    available : boolean
    occupant : Character
    display : character 
    x : int 
    y : int 
    public char display()

}


Item <|-- Weapon
Item <|-- Potion

class Game {
int gameState
map : Tile[][]
enemy : Character 
pc : Player
{static} skills : ArrayList<Skill>
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
public boolean checkAdjacent(player : Character)
public void createMap(size : int)
public void createSkills()

}

package View {

    interface ICharCreationView {
    void onConfirm(race : String, caste : String, att : int[])
    }

    class CharCreationFragment implements ICharCreationView {
    binding : FragmentCharCreation
    listener : Listener
    --
    public View onCreateView(inflater : LayoutInflater , container : ViewGroup,  savedInstanceState : Bundle)
    public void onAttIncrease()
    public void onViewCreated(view : View, savedInstanceState : Bundle)
    public View getRootView()
    }

    interface IExploreFragment {
    void onInventory()
    void onCharSheet()
    void onEquipment()
    void onRestart()
    View getRootView()
    }

    class ExploreFragment implements IExploreFragment{
        binding : FragmentExploreBinding
        listener : Listener
        game : Game
        combatLayout : LinearLayout
        depth : int
        --
        public void onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle)
        public void onViewCreated(view : View, savedInstanceState : Bundle)
        public String printMap(game : Game)
        public void onCombat()

        private void regen()
        private void setMove()
        private void setXpProgress()
        private void createLog()
        private void addToLog(text : TextView)
        private void clearLog()
        private void updateHP()
        private void youDied()
        private void replaceEnemy()
        private void spawnEnemy()

        public View getRootView()
    }
    interface IMainView {
    View getRootView()
    void displayFragment(Fragment fragment, boolean allowBack, String name);
    }
    class MainView implements IMainView{
        fmanager : FragmentManager
        binding : MainBinding
        --
        public void displayFragment(fragment : Fragment, allowBack : Boolean, name : String)
    }
}

class ControllerActivity implements ICharCreationView.Listener, IExploreFragment.Listener{
    mainView : IMainView
    gameState : Game.GameState
    --
    protected void onCreate(savedInstanceState : Bundle)
    public void onConfirm(race : String, caste : String, att : int[])
    public void onRestart()
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