package main;

public class Level {
 //NOTE TONEXT IS IN LAYERS OF 100 
	int tonext;
	int currentlevel;
	Level(int toNext, int currentLevel){
		tonext=toNext;
		currentlevel=currentLevel;
	}
	int currentLevel() {
		return (currentlevel);
	}
	int ChangeCurrentLevel() {
		currentlevel+=currentlevel;
		return (currentlevel);
	}
	
	int toNext(int exp) {
		tonext=tonext+exp;
		return(tonext);
	}
}
