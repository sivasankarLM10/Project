#include <bits/stdc++.h>
#include <iostream>
using namespace std;
 
int flip(int A[], int n)
{
    unordered_map<int, int> dp[2];
    bool flag = 1;
    int sum = 0;
    for (int i = 0; i < n; i++)
        sum += A[i];
     for (int i = -sum; i <= sum; i++)
        dp[0][i] = INT_MAX;
    dp[0][0] = 0;
    for (int i = 1; i <= n; i++) {
        for (int j = -sum; j <= sum; j++) {
            dp[flag][j] = INT_MAX;
            if (j - A[i - 1] <= sum && j - A[i - 1] >= -sum)
                dp[flag][j] = dp[flag ^ 1][j - A[i - 1]];
            if (j + A[i - 1] <= sum && j + A[i - 1] >= -sum
                && dp[flag ^ 1][j + A[i - 1]] != INT_MAX)
                dp[flag][j]
                    = min(dp[flag][j],
                          dp[flag ^ 1][j + A[i - 1]] + 1);
        }
        flag = flag ^ 1;
    }
    for (int i = 0; i <= sum; i++) {
        if (dp[flag ^ 1][i] != INT_MAX)
            return dp[flag ^ 1][i];
    }
    return n-1;
}
int main()
{
	int arr[] = {10,22,9,33,21,50,41,60};
	int n = sizeof(arr)/sizeof(arr[0]);
	cout<<flip(arr,n);
	return 0;
} 

