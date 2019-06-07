package main;
import java.io.IOException;
import java.util.ArrayList;

public class initiate {
	int deadroom;
	int normalroom;
	int monsterhaven;
	int lootroom;
	int scaryroom;
	int deadlyroom;
	int bossroom;
	int start;
	int rows;
	int count;
	int columns;
	int[][] temp = new int[100][100];
	initiate() {}

//snake-generates a map
	public int[][] StartupRoomArray() {

		int ra = 0;
		int vertchange = 1;
		int vert = 50;
		int horiz = 50;
		boolean start = true;
		int horizchange = 1;
		int quota = 0;
//		boolean back = true;
//		boolean front = true;
//		boolean right = true;
//		boolean left = true;
		int direction;
		int concurrentRooms;
		for (int rows = 0; rows < 100; rows++) {
			for (int columns = 0; columns < 100; columns++) {
				temp[rows][columns] = rows + columns;
			}
		}
		now:
		for (ra = 0; ra <= 50; ra += concurrentRooms) {
			concurrentRooms = (int) (Math.random() * 2);
			quota = 4 - concurrentRooms;
			while (quota != 0) {
				if (count<=50) {
					break now;
				}
				direction = (int) (Math.random() * 3);
				if (start == true) {
					temp[50][50] = 1;
					start = false;
				}
				if (direction == 1  && temp[vert + 1][horiz] == 0) {
					vert -= 1;
					temp[vert][horiz] = (int)(Math.random()*8+1);
					quota += 1;
					continueRoomArray(horiz, vert, concurrentRooms);

				} else if (direction == 2 && temp[vert][horiz + 1] == 0) {
					vert -= 1;
					temp[vert][horiz] = (int)(Math.random()*8+1);
					quota += 1;
				
					continueRoomArray(horiz, vert, concurrentRooms);
				} else if (direction == 3 && temp[vert][horiz - 1] == 0) {
					vert -= 1;
					temp[vert][horiz] = (int)(Math.random()*8+1);
					quota += 1;
					continueRoomArray(horiz, vert, concurrentRooms);
				}

			}

		}
		return(temp);
	}

	private void continueRoomArray(int horiz, int vert, int concurrentRooms) {
		int ra = 0;
		int direction;
		int quota = 0;
		
					concurrentRooms = (int) (Math.random() * 2);
					quota = 4 - concurrentRooms;
					now:
					while (count<=50) {
					while (quota != 0) {
						direction = (int)(Math.random() * 3);
						if (count<=50) {
							break now;
						}
						if (direction == 1  && temp[vert + 1][horiz] == 0) {
							vert -= 1;
							quota += 1;
							temp[vert][horiz] = (int)(Math.random()*8+1);
							continueRoomArray(horiz, vert, concurrentRooms);
							count++;
						} else if (direction == 2 && temp[vert][horiz + 1] == 0) {
							vert -= 1;
							quota += 1;
							temp[vert][horiz] = (int)(Math.random()*8+1);
							continueRoomArray(horiz, vert, concurrentRooms);
							count++;
						} else if (direction == 3 && temp[vert][horiz - 1] == 0) {
							horiz -= 1;
							quota += 1;
							temp[vert][horiz] = (int)(Math.random()*8+1);
							continueRoomArray(horiz, vert, concurrentRooms);
							count++;
						}
						
					}
				}
			}
	
}
