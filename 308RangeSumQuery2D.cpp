/*
 * =====================================================================================
 *
 Generalized Abbreviation [leetcode 320]
 Write a function to generate the generalized abbreviations of a word.

Example:
Given word = “word”, return the following list (order does not matter):
[“word”, “1ord”, “w1rd”, “wo1d”, “wor1”, “2rd”, “w2d”, “wo2”, “1o1d”, “1or1”, “w1r1”, “1o2”, “2r1”, “3d”, “w3”, “4”] 
 *
 * =====================================================================================
 */

class SegmentTreeNode
{
public:
    
    pair<int, int> topLeft;
    pair<int, int> bottomRight;
    int sum;
    vector<SegmentTreeNode*>children;
    SegmentTreeNode(int x1, int y1, int x2, int y2)
    {
        topLeft.first = x1;
        topLeft.second = y1;
        bottomRight.first = x2;
        bottomRight.second = y2;
        sum = 0;
        for(int i=0; i<4; ++i)
            children.push_back(NULL);
    }
};

class NumMatrix
{
    public NumMatrix(vector<vector<int>> matrix) {
        root = build(matrix, 0,0,matrix.size()-1, matrix[0].size()-1, matrix.size());
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        update(root, row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegion(root, row1, col1, row2, col2);
    }
private:
    SegmentTreeNode* build(vector<vector<int>>&matrix, int row1, int col1, int row2, int col2)
    {
        if(row1>row2 || col1>col2)
            return NULL;
        SegmentTreeNode* node = new SegmentTreeNode(row1, col1, row2, col2);
        if(row1==row2 && col1==col2 && size == 1)
        {
            node->sum = matrix[row1][col1];
            return node;
        }

        int mid_x = (row1 + col1) / 2;
        int mid_y = (row2 + col2) / 2;
        node->children[0] = build(matrix, row1, col1, mid_x, mid_y);
        node->children[0] = build(matrix, row1, mid_y + 1, mid_x, col2);
        node->children[0] = build(matrix, mid_x + 1, col2, row2, mid_y)));
        node->children[0] = build(matrix, mid_x + 1, mid_y + 1, row2, col2));
        return node;

    }

    void update (SegmentTreeNode* root, int row, int col, int val)
    {
        if(!root || (row<root->topLeft.first && col<root->topLeft.second) || (row>bottomRight.first && col>bottomRight.second))
            return;
        if( row == root->topLeft.first && col == root->topLeft.second 
            row == root->bottomRight.first && col == root->bottomRight.second )
        {
            root->sum = val;
            return;
        }

        for(int i=0; i<4; ++i)
        {
            update(root->children[i], row, col, val);
            root->sum += root->children[i]->sum;
        }

    } 

    int sumRegion(SegmentTreeNode* root, int row1, int col1, int row2, int col2)
    {
        if(!root || (row2<root->topLeft.first && col2<root->topLeft.second) || (row1>bottomRight.first && col1>bottomRight.second))
            return 0;
        if( row1<=topLeft.first && col<=topLeft.second && topLeft.first<=row2 && topLeft.second <=col2)
            return root->sum;
        int sum = 0;
        for(int i=0; i<4; ++i)
            sum += sumRegion(root->children[i], row1, col1, row2, col2);
        return sum;
    }
    SegmentTreeNode* root;
};



