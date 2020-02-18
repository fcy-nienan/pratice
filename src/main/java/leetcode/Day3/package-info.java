package leetcode.Day3;
/*
* 组合(有重复的元素)
      for (i <- index until candidates.length if (currentSum+candidates(i) <= target)) {
//        这里传的是i是为了避免重复,表示下次循环从i开始
//        因为前面的组合已经包含了,比如[2,3],选择2的时候第二部可以选择2和3,所以有两种[2,2],[2,3]
//        然后从3开始选择的时候如果不需要重复的组合那么只能选择[3,3],也就是不选择2了
//        这个是排列组合的知识
        dfs(candidates, target, i, candidates(i) :: current, currentSum + candidates(i))
      }
* */