'''
Created on Oct 16, 2016

@author: gwd
'''
from comtypes import defaultvalue


def strtonumber(s,defaultvalue=0):
    """
    string to number
    """
    try:
        fnum=float(s)
    except:
        fnum=defaultvalue
    return fnum    
#sys.stdout.write("输出的字串")
"""
str to int method
"""
def strtoint(s,defaultVaule=0):
    try:
        inum=int(s)
    except:
        inum=defaultVaule
    return inum

if __name__ == '__main__':
    pass