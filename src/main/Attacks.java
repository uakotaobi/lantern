package main;

public class Attacks {
	int movelength;
	int damageoutput;
	Attacks(int moveLength, int damageOutputMin, int damageOutputMax){
		movelength = moveLength;
		damageOutputMax= damageOutputMax - damageOutputMin;
		damageoutput = (int)((Math.random()*damageOutputMax +damageOutputMin));
	}
		
		Attacks(int moveLength, int damageOutput){
			movelength = moveLength;
			damageoutput =damageOutput;
		}
		//returns the duration of a Attack object;
		int duration() {
			return(movelength);
		}
		//returns the damageOutput of a Attack object;
	    int damageOutput() {
	    	return(damageoutput);
	    }
	    }
	
