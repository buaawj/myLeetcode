import java.util.ArrayList;

/*
On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:
stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.

作者：顽强的猫尾草
链接：https://www.jianshu.com/p/7d41b2601c97
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

/*
二分法：
1）left=0，right=已有的站从头到尾的总距离，left+0.00001<right时结果满足要求。
2）mid=(left+right)/2，计算以此mid作为最大间距时两站之间需要插入几个点，若大于K，说明mid取小了，令left=mid，反之令right=mid。

作者：顽强的猫尾草
链接：https://www.jianshu.com/p/7d41b2601c97
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

public static double minmaxGasDist(int[] stations, int K)
{
    int count, N = stations.length;
    float left = 0, right = stations[N - 1] - stations[0], mid;
    while (left + 0.00001 < right) {
        mid = (left + right) / 2;
        count = 0;
        for (int i = 0; i < N - 1; ++i)
            count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
        if (count > K) left = mid;
        else right = mid;
    }
    return right;

}






















}
