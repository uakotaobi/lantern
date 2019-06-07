package main;
import com.googlecode.lanterna.SGR;
import java.util.ArrayList;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


	class game{
		  private Terminal terminal;
		  private Screen screen;

		  public game(Terminal terminal, Screen screen) {
		    this.terminal = terminal;
		    this.screen = screen;
		  }
		  //determines if the amount of actions able to be done this turn. 
		  void maxTurnActions(Terminal terminal, Screen screen, int additionalMaxMoves) {
			  int max =2+additionalMaxMoves;
			
			  
		  }
		  
		  // determines the length of an action
		  int actionlength(int a) { 
			int actions =0;
			// if  next.move()=
			actions+=1;
			
			return 0;
		  }
		  
		  int turnNumber(Terminal terminal, Screen screen, String action){
			  
			  return 0;
		  }
		  
	}

