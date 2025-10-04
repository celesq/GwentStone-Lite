

# GwentStone Lite

<div align="center"><img src="https://tenor.com/view/witcher3-gif-9340436.gif" width="500px"></div> 

Built a full-featured card game engine in **Java**, combining mechanics inspired by *Gwent* and *Hearthstone*.  
The system handles **multi-deck battles** with 20+ cards per player, hero abilities, and strict rule enforcement, sustaining **hundreds of actions per match** through modular OOP architecture.  
All gameplay runs programmatically — no GUI — ensuring deterministic, testable logic and efficient memory management.  

# How to Use the Game Platform  

## Game Initialization  
The engine starts from a structured **JSON input file** describing:  
- Player decks and hero configurations  
- Random seed for deterministic shuffling  
- Sequential game commands (e.g., `placeCard`, `useHeroAbility`, `endPlayerTurn`)  

Each command is validated and executed step-by-step, with results written into a JSON output file.  
Errors automatically generate standardized messages according to the project specification.  

---

## Turn-Based Gameplay  
The game alternates between two players.  
At the beginning of each turn:  
- Mana increases by one (up to the cap)  
- A new card is drawn from the deck  
- Players can perform any number of valid actions before ending their turn  

Turns are strictly validated — illegal operations immediately return descriptive error outputs.  

---

## Card Placement  
**Command:** `placeCard`  
Allows a player to place a card from hand onto the table, consuming the required mana.  
The engine checks:  
- Sufficient mana availability  
- Row capacity (no overflow)  
- Card type constraints (e.g., *Tank* cards occupy the frontline)  

**Example Error:**  
```
Not enough mana to place card on table.
```

## Attacks  
**Command:** `cardUsesAttack`  
Enables one card to attack another. The system ensures:  
- Valid ownership of attacker and target  
- Attacker is not frozen and has not already attacked this turn  
- Target follows *Tank-first* attack rules if applicable  

Once an attack is processed, defeated cards are automatically removed, preserving board order.  

---

## Hero Abilities  
**Command:** `useHeroAbility`  
Certain heroes possess unique powers — freezing rows, healing, or dealing damage.  
Before applying an ability, the system verifies:  
- Mana sufficiency  
- Valid row targeting  
- One-use-per-turn limitation  

---

## Debug / State Inspection  
The engine supports introspection commands for debugging and validation:  
- `getCardsInHand` — lists all cards in the current player’s hand  
- `getCardsOnTable` — displays all board cards in order  
- `getPlayerMana` — returns the active player’s mana  
- `getPlayerTurn` — shows whose turn it is  

All debugging commands are **read-only**, ensuring deterministic replay behavior.  

---

## Ending the Match  
When a hero’s health reaches zero, the match concludes instantly.  
The engine prints `"Player one killed the enemy hero"` or `"Player two killed the enemy hero"`.

# Brief Presentation of the Implementation

This project implements a two-player card game engine entirely in Java with JSON I/O. Below is a description of each major component:

  ## JSON Input / Output  
  The program reads input files via Jackson (in `lib/`) into Java objects.  
  After executing each command, it appends output nodes into an `ArrayNode` in JSON.  
  Output format is validated via JSON comparison (not textual diff).

  ## Card & Hero Classes  
  Cards are represented by classes:  
  - Fields: `mana`, `health`, `attackDamage`, `name`, `description`, `colors`  
  - Subclasses / logic for special cards (Tank, Goliath, Warden, Miraj, The Cursed One, etc.)  
  Heroes also have fixed attributes and occasional abilities (e.g. freeze rows, ability to damage cards).

  ## Deck Shuffling & Determinism  
  Decks are shuffled using a seed from input to ensure determinism across runs.  
  The `Random` object is reset for each shuffle stage per test.

  ## Error Handling  
  Every invalid operation produces structured feedback such as:  

  `Attacked card does not belong to the enemy.
  Attacker card is frozen.
  Not enough mana to use hero ability.`

  ## Game Engine  
  The core loop parses and executes commands sequentially.  
  For each command, validation is performed; if valid, changes are applied; if invalid, correct error output is appended.  
  State consists of: hands, decks, board (rows), hero stats, mana, etc.

  ## Debug & Queries  
  Debug commands access current state (cards in hand, mana, board layout).  
  These commands do **not** modify state — only read.

  ## Error Handling & Edge Cases  
  All invalid cases (e.g. placing with insufficient mana, attacking invalid target) are handled gracefully, printing correct error string.  
  The program never crashes even with invalid commands (fails safe).  

  ## Design & Modularity  
  Classes organized by package: `fileio`, `game`, `cards`, `heroes`, etc.  
  Methods are small, single-purpose.  
  JSON handling is abstracted in its own module.  
  Clear separation between state model, command interpreter, and I/O.

  ## Testing & Validation  
  The system was validated through automated checkers on multiple datasets featuring **100+ commands**,  
  verifying correctness, determinism, and full JSON conformity.  
  The project includes reference input/output sets in `ref/`.  
  The checker will validate the output JSON against the reference JSON.  

  ## Summary
  GwentStone-Lite is a complete OOP-based battle simulator, implementing all fundamental mechanics of a competitive card game — deck management, mana economy, hero abilities, and combat resolution — fully     automated through JSON-driven execution.
  It demonstrates proficiency in Java design patterns, algorithmic reasoning, and modular architecture, while maintaining clean serialization, strict validation, and extensibility for future GUI integration.
