import java.util.ArrayList;

/*
308.	Range Sum Query 2D - Mutable
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
 */


// 1. Binary indexed tree
public static class NumMatrix {
    private int BIT2D[][];
    private int matrix[][];
    
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }
        BIT2D = new int[matrix.length + 1][matrix[0].length + 1];
        this.matrix = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - matrix[row][col];
        matrix[row][col] = val;
        for(int i = row + 1; i < BIT2D.length; i += i & (-i)) {         //also equals to i |= i + 1
            for(int j = col + 1; j < BIT2D[0].length; j += j & (-j)) {
                BIT2D[i][j] += delta;
            }
        }
    }
    

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2 + 1, col2 + 1) - getSum(row1, col2 + 1) - getSum(row2 + 1, col1) + getSum(row1, col1); 
    }
    
    private int getSum(int row, int col) {
        int sum = 0;
        for(int i = row; i > 0; i -= i & (-i)) {
            for(int j = col; j > 0; j -= j & (-j)) {
                sum += BIT2D[i][j];
            }
        }
        return sum;
    }
}


/*  Solution 2: Segment Tree */
public static class SegmentTreeNode
{
	int row1;
	int col1;
	int row2;
	int col2;
	int sum;
	SegmentTreeNode[] children;
	
	public SegmentTreeNode(int row1, int col1, int row2, int col2)
	{
		this.row1 = row1;
		this.col1 = col1;
		this.row2 = row2;
		this.col2 = col2;
		this.sum = 0;
		children = new SegmentTreeNode[4];    		
	}
	
	public SegmentTreeNode()
	{
		this.row1 = 0;
		this.col1 = 0;
		this.row2 = 0;
		this.col2 = 0;
		this.sum = 0;
		children = new SegmentTreeNode[4];    		
	}
}

public static class NumMatrix {
    
    private SegmentTreeNode root;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }
        
        root = buildTreeHelper(matrix, 0,0, matrix.length-1, matrix[0].length-1);
    }
    
    public void update(int row, int col, int val) {
    	updateHelper(root, row, col, val);
    }
    

    public int sumRegion(int row1, int col1, int row2, int col2) {

    	return sumRegionHelper(root, row1, col1, row2, col2);
    }
    
    private SegmentTreeNode buildTreeHelper(int[][] matrix, int row1, int col1, int row2, int col2)
    {
        if(row1>row2 || col1>col2)
            return null;
    	
    	SegmentTreeNode node = new SegmentTreeNode(row1, col1, row2, col2);
    	if(row1==row2 && col1==col2)
    	{
    		node.sum = matrix[row1][col1];
    		return node;
    	}
    	
    	int rowMid = (row1+row2)/2;
    	int colMid = (col1+col2)/2;
    	
    	node.children[0] = buildTreeHelper(matrix, row1, col1, rowMid, colMid);
    	node.children[1] = buildTreeHelper(matrix, row1, colMid+1, rowMid, col2);
    	node.children[2] = buildTreeHelper(matrix, rowMid+1, col1, row2, colMid);
    	node.children[3] = buildTreeHelper(matrix, rowMid+1, colMid+1, row2, col2);
    	
    	for(int i=0; i<4; ++i)
    		if(node.children[i]!=null)
    			node.sum += node.children[i].sum;
    	return node;
    }
    
    private void updateHelper(SegmentTreeNode node, int row, int col, int val)
    {
    	if(node==null || node.row1>row || node.col1>col ||node.row2<row || node.col2<col)
    		return;
    	
    	if(node.row1==node.row2 && node.col1==node.col2 && node.row1==row && node.col1==col)
    	{
    		node.sum = val;
    		return;
    	}
    	
    	int sum = 0;
    	for(int i=0; i<4; ++i)
    	{
    		updateHelper(node.children[i], row, col, val);
    		sum += node.children[i].sum;
    	}
    	
    	node.sum = sum;
    }
    
    
    private int sumRegionHelper(SegmentTreeNode node, int row1, int col1, int row2, int col2) 
    {
    	if(node==null || node.row1>row2 || node.col1>col2 ||node.row2<row1 || node.col2<col1)
    		return 0;
    	
    	if( node.row1>=row1 && node.col1>=col1 && node.row2<=row2 && node.col2<=col2 )
    	{
    		return node.sum;
    	}
    	
    	int sum = 0;
    	for(int i=0; i<4; ++i)
    	{
    		sum += sumRegionHelper(node.children[i], row1, col1, row2, col2);
    	}
    	
    	return sum;
    }
       
}







































}
