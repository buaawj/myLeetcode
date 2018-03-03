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






































}
