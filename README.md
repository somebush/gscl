Game Stage Condition Library (GSCL) is a lightweight library for developers.

The library adds a new method to your mods: isGameStageNearby(Entity entity, List<String> stages). 'entity' is a mob near which you need to check for a player with one or more 'stages'.

The mod works fine in single player. I haven't tested it on the server.

How it works? When the isGameStageNearby() method is called, the code gets a list of all players on the server. The code will check if the player is in the same dimension as the entity. The code will check if these players have the required game stage. The code gets the chunk loading distance set in a single player game or on the server side. The code uses Math.abs() to get the distance in chunks from the player with the desired game stage to the entity. If this distance is less than or equal to the chunk loading distance, isGameStageNearby() will return 'true'.

You are free to use and modify this mod as you please.

I'm not interested in supporting newer or older versions, I will only be working on the 1.12.2 version of the mod.
