#检查两个点是否在攻击线上
def attack(p1,p2):
    return p1[0]==p2[0] or p1[1] == p2[1] or (abs(p1[0]-p2[0])==abs(p1[1]-p2[1]))

#棋盘类
class Cell():
    def __init__(self,n):
        self.n = n
        self.cell = [[(i,j) for j in range(n)] for i in range(n)]

def isAttack(p,rp):
    for i in rp:
        if attack(p,i):
            return True
    return False

def eightQueen(num):
    cell = Cell(num)
    board = cell.cell
    result = [[(0,i)] for i in range(num)]
    count = 0
    for col in board:
        count+=1
        for p in col:
            if not result:
                result.append([p])
            else:
                #scan the result
                for rp in result:
                    if  not isAttack(p,rp):
                        copy = rp[:]
                        copy.append(p)
                        result.append(copy)
        result = filter(lambda x: x.__len__()==count,result)
    return filter(lambda x : x.__len__()==num,result)

#使用方式如下，返回所有的有效解
result = eightQueen(8)
class EightQueen:
    #define const  queen number
    queenNum = 8
    def __init__(self):
        #定义一个8行 8 列的二维数组来模拟棋盘
        self.qiPan = []
        self.num = 0
        #init a n*n  list[][] as qi pan
        for i in range(self.queenNum):
            rows = []
            for j in range(self.queenNum):
                xy = [i,j]
                rows.append(xy)
            self.qiPan.append(rows)
    #定义一个递归方法,递归去下一行寻找符合条件的坐标点

    #param e8  存放符合条件的坐标点的列表

    #param rowNum   寻找的行号
    def getNext(self,e8,rowNum):
         if len(e8) == self.queenNum:
            self.num += 1
            #输出符合条件的全部坐标点
            print("method "+str(self.num)+"is:"+str(e8));
            return
         if rowNum > len(self.qiPan):
             return
         for d in self.qiPan[rowNum]:
             if self.matchAllE8(e8,d):
                 e8copy = e8[:]
                 e8copy.append(d)
                 self.getNext(e8copy,rowNum+1)

    def calcNum(self):

        #遍历棋盘的第一行
        for i in self.qiPan[0]:

            #从第二行开始递归寻找符合条件的坐标
            self.getNext([i],1)
        print("all method is:"+str(self.num));

   #判断left0坐标 是否满足上面行中已找到的全部符合条件的坐标都不攻击
    def matchAllE8(self,e8,left0):
        for i in e8:
            if not self.isMatch(i,left0):
                return False
        return True


    # 符合不攻击的条件
    def isMatch(self,f,g):
        return (f[0]+f[1] != g[0]+g[1]) and\
               (f[0]-f[1] != g[0]-g[1])  and\
               (f[0] != g[0]) and (f[1] != g[1])

e = EightQueen()

e.calcNum()