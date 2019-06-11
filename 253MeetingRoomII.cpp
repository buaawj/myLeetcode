/*
Meeting Rooms II

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.+

For example, Given [[0, 30],[5, 10],[15, 20]], return 2.

*/

/* Min Heap */
bool compare(Interval a, Interval b)
{
    return a.start  < b.start;
}
int minMeetingRooms(vector<Interval>& intervals)
{
    int n = intervals.size();
	priority_queue<int, vector<int>, greater<int>>q;
    sort(intervals.begin(), intervals.end(), compare);
	int rooms = 1 ;
    q.push(intervals[0].end);
	for(int i=1; i<n; ++i)
	{
        if(!q.empty() && q.top() > intervals[i].start)
		{
            rooms++;
		}
        else
            q.pop();
        q.push(intervals[i].end);
    }

    return rooms;
}

/*    */
bool compare(node a, node b)
{
    return a.end  != b.end ? a.end < b.end : a.type > b.type;
}

typedef enum  {START, END }TYPE;
struct node
{
    TYPE type;
    int end;
    node(int _type, int _end)
    {
        type = _type;
        end = _end;
    }
};
int minMeetingRooms(vector<Interval>& intervals)
{
    vector<node> endPoints;
    for(int i=0; i<intervals.size(); ++i)
    {
        endPoints.push_back(node(START, intervals[i].start));
        endPoints.push_back(node(END, intervals[i].end));
    }
    sort(endPoints.begin(), endPoints.end(), compare());
    int maxRoom = 0 rooms = 0;
    for(int i=0; i<endPoints.size(); ++i)
    {
        if(endPoints[i].type == START)
        {
            ++rooms;
            maxRoom = max(rooms, maxRoom);
        }
        else
            --rooms;
    }
    return maxRoom;
}

// Java Version:
public int minMeetingRooms(Interval[] intervals) {
    if(intervals == null || intervals.length == 0) return 0;
    Arrays.sort(intervals, (i1, i2)->i1.start - i2.start);

    // Min heap
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for(int i=0; i<intervals.length; ++i)
    {
      if(!queue.isEmpty() && queue.peek()<=intervals[i].start)
        queue.poll();
        queue.offer(intervals[i].end);
    }

    return queue.size();
}

    public int minMeetingRooms(Interval[] intervals) {
       int []start = new int[intervals.length];
        int [] end = new int [intervals.length];
        for(int i=0;i<intervals.length;i++){
            start[i]=intervals[i].start;
            end[i]=intervals[i].end;
        }
        int room=0;
        int end_index=0;
        Arrays.sort(start);
        Arrays.sort(end);
        for(int i =0;i<intervals.length;i++){
            if(start[i]<end[end_index]){
                room++;
            }
            else{
                end_index++;
            }
        }
        return room;
    }
///////////////////////////////////

int calculate(string s)
{
    stack<int> operands;
    stack<char> operators;
    int n = s.length();
    for(int i=0; i<s.length(); ++i)
    {
        char tok = s[i];
        if(isdigit(tok))
        {
            int sum = 0;
            while(i<n && isdigit(s[i]))
            {
                sum = sum*10 + s[i++]-'0';
            }
            operands.push(sum);
            --i;
        }
        else
        {
            while(!operators.empty() && isPrecendence(tok) <= isPrecendence(operators.top()))
            {
                applyOperation(operands, operators);
            }

            operators.push(tok);
        }

        while(!operators.empty())
        {
            applyOperation(operands, operators);
        }

        return operators.empty() ? 0 : operands.top();
    }
}

bool isPrecendence(char c)
{
    if(c == '+' || c == '-')
        return 0;
    if(c=='*' || c=='*')
        return 1;
}

void applyOperation(stack<int>&operands, stack<char>&operators)
{
    int op2 = operands.top(); operands.pop();
    int op1 = operands.top(); operands.pop();
    int ret = 0;
    swtich(operators.top())
    {
        case '+':
            ret = op1 + op2;
            break;
        case '-':
            ret = op1 - op2;
            break;
        case '*':
            ret = op1 * op2;
            break;
        case '/':
            ret = op1 / op2;
            break;
    }

    operands.push(ret);
    operators.pop();
}
