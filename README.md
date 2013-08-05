####PhazonMod####
The Phazon Mod adds an infection to Minecraft: Phazon

###Mechanics###

##Phazon Summon##

This nifty Item is created with 3 diamonds ('D') and 1 Redstone ('R') in the following pattern:
A|D|A 
D|R|D 
A|A|A 

If you use it, a Phazon Meteor will instantly crash within a 50 block radius of you.

##Phazon Block##

This Block Spreads to dirt,grass,sand,logs and leaves 1 block around it.
It is rather quick, so try and contain it with a gap.

##Phazon Mobs##

Phazon also is highly mutagenic, mutating many mobs. Those who can't mutate, die.
All Phazon Mobs drop a Phazon Drop on death.

Pigs, Cows and Chickens mutate and turn aggressive.

Zombies mutate, they are twice as fast and do 6 hearts of damage.

Skeletons mutate, they gain a phazon Canon to shoot you with.

Creepers mutate, they are 1.5 times faster and spawn a Phazon Meteor where they explode
(so don't let them explode)
small tip, if one of those get hit by lighning, RUN, the explosion (and therefor the meteor) will be twice as big

##Player Corruption##

If you touch Phazon, your Phazon bar (top of the screen) fills up.
If the Phazon bar is full and your still touching Phazon, you take 5 hearts of damage. (kills you very quickly)

If you use a Phazon Tool, you consume Phazon, but you also corrupt a little bit
(Corruption bar is top of the screen, too)
if the corruption bar is full, you start self generating phazon

#Phazon Cure#
Craft 8 Phazon drops from Phazon Mobs around a bucket, and you got yourself a cure
it cures up to 20% of corruption.

##Phazon Tools##
Those use Phazon, but keep in mind that you can't use them if your Phazon is low
(so better get corruption going)

#Phazon Canon#
This is the same Canon the skeletons use. it uses 10% Phazon per shot.
it does 6 hearts of damage, which is usually 1-2 hits.
it is crafted with a Bow ('B') 5 Phazon Drops ('D') and a Phazon Cure ('C')
A|D|A 
D|B|D 
D|C|D 

#Phazon Pick#
This has the same properties as a diamond pickaxe, but uses 5% Phazon per Block
it is crafted with 3 Phazon Drops ('D'), 2 Sticks ('S') and 2 Gold Ingots ('G')
D|D|D 
G|S|G 
A|S|A 

###Configuration###
all id's are editable in the config file

###Installation Instructions###
The mod is compiled for Minecraft 1.6.2

1. Download and Install Minecraft Forge (http://files.minecraftforge.net/)
	+ The mod was compiled using Minecraft Forge version 9.10.0.804
2. Download the mod
3. Place the downloaded File into the /mods directory in the .minecraft directory (for Client) or the root of the server directory (for Server)
4. Run the client or server as usual

###Compilance Instructions###

1. Download , extract and install Minecraft Forge src version 9.10.0.804
2. Download and extract (or clone) this repository
3. go into the mcp/src/minecraft folder and drop the contents of the ph_common folder there
4. run the recompile and then the reobfuscate script in the mcp folder
5. go into the mcp/reobf/minecraft folder and drop the contents of the resources folder there
6. package all contents of this folder into a zip file
7. done