# GubaGame

## Introduction 
At some point in my 4th year of high school, I was really fascinated in making a game from scratch. I knew Java and after some searching, I found that the best (and most in-depth) free tutorial available for making a scratch game in Java was taught in a 100+ video series found here: https://www.youtube.com/playlist?list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf

## What makes GubaGame different from the tutorials?
TheChernoProject was being developed very slowly at the time (because the author had school/work/life and simply couldn't update the series as often as I hoped). I decided to not wait because I was very passionate about working on this game so I decided to use the knowledge I had gained from the tutorials (and past projects) to keep developing the game on my own without help from any tutorials. I got pretty far until I myself got busy with school and found other projects I was more passionate about (this was a solo project with no huge intentions aside from learning).

## How far the tutorials went:

1. Map Parsing and Loading
2. Player Movement
3. Basic Tile & Projectile Collision
4. Sprites/Graphics implemented for the above features

## What I added on my own with no tutorial:

1. Multiplayer (Mixture of UDP and TCP)
  1. Ran and managed server off a VPS that I bought
  2. Made a database file to hold all usernames, passwords, and inventories
  3. Implemented a login connection with TCP but a player-movement connection with UDP
2. Inventory System (Featuring my own homemade inventory GUI)
  1. Players could pick up/drop weapons over the server so other players could pick them up
  2. Picked up weapons would appear & save into the inventory.
3. Weapons
  1. Created the sprites for weapons
  2. Implemented the sprites so that the direction the player were facing determined how the sprite was positioned.
