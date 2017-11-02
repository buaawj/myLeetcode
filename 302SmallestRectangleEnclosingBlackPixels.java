import java.util.ArrayList;

/*
302.	Smallest Rectangle Enclosing Black Pixels

An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,



Return 6.
 */




public class Solution {


// dfs: O(mn)

public class Solution {
    int minX = Integer.MAX_VALUE, maxX = 0, minY = Integer.MAX_VALUE, maxY = 0;
    public int minArea(char[][] image, int x, int y) {
        if ( image == null || image.length == 0 || image[ 0 ].length == 0 )
            return 0;
        dfs ( image, x, y );
        return ( maxX - minX + 1 ) * ( maxY - minY + 1 );
    }
    public void dfs ( char[][] image, int x, int y ){
        int m = image.length;
        int n = image[ 0 ].length;
        if ( x < 0 || x >= m || y < 0 || y >= n || image [x][y] == '0' )
            return;
        image[x][y] = '0';
        minX = Math.min( minX, x );
        maxX = Math.max( maxX, x );
        minY = Math.min( minY, y );
        maxY = Math.max( maxY, y );
        dfs ( image, x - 1, y );
        dfs ( image, x + 1, y );
        dfs ( image, x, y - 1 );
        dfs ( image, x, y + 1 );
    }
}

// Binary search O(mlogn + nLogm)
  public  int minArea(char[][] image, int x, int y)
  		    {
  		    	int m = image.length, n = image[0].length;

  		    	int left = SearchColumn(image, 0, y, true);
  		    	int right  = SearchColumn(image, y+1, n-1,false);
  		    	int up = SearchRow(image, 0, x, true);
  		    	int down = SearchRow(image, x+1, m-1, false);

  		    	return (right-left)*(down-up);
  		    }

  		    private static int SearchColumn(char[][] image, int start, int end, Boolean left)
  		    {

  		    	while(start <=end)
  		    	{
  		    		int mid = start+(end-start)/2;
  		    		int k = 0;
  		    		while(k<image.length && image[k][mid]=='0') ++k;

  		    		if(k < image.length == left )
  		    			end = mid - 1;
  		    		else
  		    			start = mid + 1;
  		    	}

  		    	return start;
  		    }

  		    private  int SearchRow(char[][] image, int start, int end, boolean top)
  		    {

  		    	while(start <=end)
  		    	{
  		    		int mid = start+(end-start)/2;
  		    		int k = 0;
  		    		while(k<image[0].length && image[mid][k]=='0') ++k;

  		    		if(k < image[0].length == top )
  		    			end = mid - 1;
  		    		else
  		    			start = mid + 1;
  		    	}

  		    	return start;
  		    }
}







































}
