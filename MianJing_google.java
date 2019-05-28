import java.util.ArrayList;

/*
1. 一个一米长的sidewalk，假设是0-1,设雨滴的宽度
固定为0.01米，每次下雨的位置随机，精度是double，问多少滴雨才能使整个sidewalk
变湿。模拟整个过程
 */
 class Interval
 {
     double left, right;
     boolean isWet()
     {
         return left <= right;
     }

     Interval(double left, double right)
     {
         this.left = left;
         this.right = right;
     }
 }

 int Emulator()
 {
     Interval[] intvs = new Interval[100];
     int start = 0, size = 0.01;
     for(int i=0; i<100; ++i)
     {
         intvs[i] = new Interval(start, start+size);
         start += size;
     }

     int ret = 0, wetCnt = 0;
     while(wetCnt < 100)
     {
         double center = Math.randon();
         double left = center - 0.005;
         double right = center + 0.005;

         if(left >=0)
         {
             int index = (int)(left/0.01);
             if (!intervals[index].isWet()) {
                 if (left < intervals[index].right) {
                     intervals[index].right = left;
                     if (intervals[index].isWet()) wetCount++;
                 }
             }
         }

         if(right <= 1)
         {
             int index = (int)(right/0.01);
             if (!intervals[index].isWet()) {
                 if (right > intervals[index].left) {
                     intervals[index].left = right;
                     if (intervals[index].isWet()) wetCount++;
                 }
             }
         }

         ++ret;
     }

     return ret;
 }

/* Decode string: "{a,b}c{d,e}f" --> [acdf, acef, bcdf, bcef] BFS*/

	static Queue<String> decodeString(String s)
	{
		List<String> ret = new ArrayList<>();
		Queue<String> q = new LinkedList<>();
		int i = 0, n = s.length();

		while(i<n)
		{
			int j=i;
			while(j<n && s.charAt(j) !='{') ++j;
			update(q, new String[] {s.substring(i, j)});
			int start = j+1;
			if(start >= n) break;
			while(j<n && s.charAt(j) !='}') ++j;
			update(q, s.substring(start, j).split(","));
			i = j+1;
		}

		return q;
	}

	static void update(Queue<String> q, String[] strs)
	{
		if(q.isEmpty()) q.add("");
		for(int i=q.size()-1; i>=0; --i)
		{
			String cur = q.poll();
			for(String s : strs)
				q.offer(cur+s);
		}
	}

/*fOLLOWUP: "a{b{c, d}e{f}}""->  [abcef, abdef]*/
static void dfs(String s, List<String>ret)
	{
		if(!s.contains("{"))
		{
			ret.add(s);
			return;
		}
		Queue<String> ret1 = decodeString(s);
		for(String s1 : ret1)
			dfs(s1, ret);
	}

	static Queue<String> decodeString(String s)
	{
		Queue<String> q = new LinkedList<>();
		int i = 0, n = s.length();

		while(i<n)
		{
			int j=i;
			while(j<n && s.charAt(j) !='{') ++j;
			update(q, new String[] {s.substring(i, j)});
			int start = j+1;
			if(start >= n) break;
			int cnt = 1;
			++j;
			while(j<n && cnt > 0)
			{
				if(s.charAt(j) =='{') ++cnt;
				else if(s.charAt(j) =='}') --cnt;
				++j;
			}
			--j;
			String substr = s.substring(start, j);
			update(q, splitString(substr));
			i = j+1;
		}

		return q;
	}


	static String[] splitString(String s)
	{
		List<String> ret = new ArrayList<>();
		int cnt = 0, i=0, n= s.length();
		int start = 0;
		while(i<n)
		{
			if(s.charAt(i) =='{')
			{
				++cnt;
			}
			else if(s.charAt(i) =='}') --cnt;
			else if(s.charAt(i) ==',' && cnt ==0)
			{
				ret.add(s.substring(start, i));
				start = i+1;
			}
			++i;
		}

		if(start < n)
			ret.add(s.substring(start));

		return ret.toArray(new String[ret.size()]);
	}


	static void update(Queue<String> q, String[] strs)
	{
		if(q.isEmpty()) q.add("");
		for(int i=q.size()-1; i>=0; --i)
		{
			String cur = q.poll();
			for(String s : strs)
				q.offer(cur+s);
		}
	}




































}
