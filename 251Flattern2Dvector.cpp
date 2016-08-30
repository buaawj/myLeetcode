/*
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 2, 3, 4, 5, 6].

*/

class Vector2D {
    public:
      Vector2D(vector<vector<int>>& vec2d) {
          data = vec2d;
          row = 0; col=0;
          while(row<data.size() && data[row].size()==0)
            ++row;
     }

     int next()
     {
         int elem = data[row][col];
         ++col;
         while(row<data.size() && col == data[row].size())
         {
             col = 0;
             ++row;
         }
         return elem;
     }

    bool hasNext() {
         return row!=data.size();
 }
    private:
    vector<vector<int>> data;
    int row, col;
};
