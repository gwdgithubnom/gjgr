 # -*- coding: utf8 -*-

def place(x, k):

    """判断第k个皇后当前的列位置x[k]是否与其它皇后冲突，不冲突返回真，否则返回假

    """

    for i in range(1, k):

        if x[i] == x[k] or abs(x[i] - x[k]) == abs(i - k):

            return False

    return True





def n_queens(n):

    """计算n皇后的其中一个解，将解向量返回

    """

    k = 1

    #解向量

    x = [0 for row in range(n + 1)]

    x[1] = 0

    while k > 0:

        #在当前列加1的位置开始搜索

        x[k] = x[k] + 1



        while (x[k] <= n) and (not place(x, k)):#当前列位置是否满足条件

            #不满足条件，继续搜索下一列位置

            x[k] = x[k] + 1

        if x[k] <= n:

            #是最后一个皇后，完成搜索

            if k == n:

                break

            else:

                #不是，则处理下一行皇后

                k = k + 1

                x[k] = 0

        #已判断完n列，均没有满足条件

        else:

            #第k行复位为0，回溯到前一行

            x[k] = 0

            k = k - 1

    return x[1:]


#主函数

#打印出n皇后的一个解

print(n_queens(8))

def printQueens(state):
   """printQueens - print the state
   """
   def line(pos , length = len(state)) :
      print( ' . '*pos+' X '+' . '*(length-pos-1));
   for pos in state:
      line(pos)

printQueens(n_queens(8))
