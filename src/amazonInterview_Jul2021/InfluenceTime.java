package amazonInterview_Jul2021;

import java.util.LinkedList;
import java.util.Queue;

/*
 * There are 2 types of people in a room. One is Influencer and Other is Non influencer. 
 * An Influencer can influence all 4 sides of his/her neighours(top,left,bottom, right) and then theirs neighbours in turn can influence 
 * 	all its neighbours.
 * For an influence to influence its neighours it will take 1 Time Unit.
 * Find the Number of Time units required to make the room full of Influencers.
 */
public class InfluenceTime {
	
	public int getInfluenceTime(int[][] room) {
		int row = room.length;
		int col = room[0].length;
		int maxPeople = row*col;
		int influencerCount = 0;
		Queue<int[]> queue = new LinkedList<>();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(room[i][j]==1) {
					influencerCount++;
					queue.add(new int[] {i,j});
				}
			}
		}
		if(influencerCount==0) {
			return -1;
		}
		if(influencerCount == maxPeople) {
			return 0;
		}
		int timeRequired = 1;  // not sure what i did during interview :(
		int[] rows = {-1,0,1,0};
		int[] cols = {0,-1,0,1};
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-->0) {
				int[] person = queue.poll();
				for(int i=0;i<4;i++) {
					int newRow = person[0] + rows[i];
					int newCol = person[1] + cols[i];
					if(newRow>=0 && newRow<row && newCol>=0 && newCol<col && room[newRow][newCol]==0) {
						queue.add(new int[] {newRow,newCol});
						room[newRow][newCol] =1;
						influencerCount++;
						if(influencerCount == maxPeople) {
							return timeRequired;
						}
					}
				}
			}
			timeRequired++;
		}
		return timeRequired;
	}
	

	public static void main(String[] args) {
		InfluenceTime influenceTime = new InfluenceTime();
		int[][] room1 = {{0,1,0},
						{0,1,0},
						{0,0,0}};
		System.out.println("Time to influence entire room1 : "+influenceTime.getInfluenceTime(room1));
		int[][] room2 = {{0,1,0},
						 {0,0,0},
						 {0,0,0}};
		System.out.println("Time to influence entire room2 : "+influenceTime.getInfluenceTime(room2));
		int[][] room3 = {{1,0,0},
				 		 {1,0,1},
				 		 {0,1,0}};
		System.out.println("Time to influence entire room3 : "+influenceTime.getInfluenceTime(room3));
	}

}
