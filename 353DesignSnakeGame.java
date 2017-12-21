import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

public class SnakeGame {

	int width;
	int height;
	int row;
	int col;
	int credit;
	Queue<Integer> pos;
	Map<String, int[]> mapping;
	
	/** Initialize your data structure here.
    	@param width - screen width
    	@param height - screen height 
    	@param food - A list of food positions
    E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */	   
	public SnakeGame(int width, int height, int[][] food) 
	{
		this.width = width;
		this.height = height;
		this.row = 0;
		this.col = 0;
		this.credit = 0;
		this.queue = new LinkedList<>();
		for(int[] item : food)
		{
			Queue.offer(item[0]*row + item[1]);
		}
		
		mapping = new HashMap<>();
		mapping.add("U", {-1, 0});
		mapping.add("L", {0, -1});
		mapping.add("R", {0, 1});
		mapping.add("D", {1, 0});
	}
	   
	/** Moves the snake.
       @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
       @return The game's score after the move. Return -1 if game over. 
       Game over when snake crosses the screen boundary or bites its body. */
   public int move(String direction) 
   {
	   int newrow = this.row + mapping.get(direction)[0];
	   int newcol = this.col + mapping.get(direction)[1];
	   if(newrow>=this.width || newrow<0 || newcol >=this.height || newcol<0)
		   return -1;
	   this.row = newrow;
	   this.col = newcol;
	   
	   if(!Queue.isEmpty())
	   {
		   int foodrow = queue.peek()/this.width;
		   int foodcol = queue.peek()%this.width;
		   if(foodrow == newrow && foodcol == newcol)
		   {
			   this.credit++;
			   Queue.poll();
		   }
	   }
	   
	   return this.credit;
   }

}







































}
