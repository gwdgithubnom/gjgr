'''
Created on Oct 16, 2016

@author: gwd
'''
import re
import sys

pattern=re.compile('\d*_') 

def readfile(filename):
    print("starting the job, about file:"+filename)
    inputFile=open(filename,"r+")
    outputFile=open("re_"+filename,"w+")
    while True:
        line=inputFile.readline().rstrip('\n').rstrip('\r')          
        #l=pattern.search(line)
        if line:
            n=matchString(line)
            outputFile.write(line+" "+str(int(n)-1)+"\n")
            sys.stdout.write(".")    
            pass 
        else:
            print("\nfinish the job")
            break
    inputFile.close()
    outputFile.close()
def matchString(mstr):
    l=pattern.search(mstr)
    if l:
        n=l.group().rstrip("_")
    else:
        n=0
    return n
    
if __name__ == '__main__':
    readfile("train.txt")