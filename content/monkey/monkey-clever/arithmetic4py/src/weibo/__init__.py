#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Date    : 2015-07-19
# @Author  : DLAndrews (dleoandrews@outlook.com)
# @Link    : https://github.com/DLAndrews
# @Version : $Id$

import requests
from bs4 import BeautifulSoup

userNM='YourWeiboMailAddress'
password = 'YourWeiboPassword'
pageMaxNum = 20
Search_Word = 'WORD'
Search_Persom = 'PersonName'

class WeiBo(object):
    def __init__(self,mail,password):
        self.session = requests.session()
        WEIBO_RAND_URL = 'http://login.weibo.cn/login/'
        WEIBO_LOGIN_PREFIX = 'http://login.weibo.cn/login/'

        h = self.session.post(WEIBO_RAND_URL,data = {})
        page = h.text
        soup = BeautifulSoup(page,"lxml")
        rand = soup.form["action"]
        self.url = WEIBO_LOGIN_PREFIX+rand
        for v in soup.select('input[name="vk"]'):
            vk = v["value"]
        for p in soup.select('input[type="password"]'):
            passwordrand = p["name"]

        self.data = {'mobile': mail,
                passwordrand: password,
                'remember': 'on',
                'backURL': 'http://weibo.cn/',
                'backTitle': '新浪微博',
                'vk': vk,
                'submit': '登录',
                'encoding': 'utf-8'}
        page = self.session.post(self.url,self.data)
        soup = BeautifulSoup(page.text,"lxml")
        # print '----'
        # text = soup.get_text()
        self.id = soup.find("div","tip2").a["href"].split('/')[1]

    def searchWord(self,keyWord):
        url_p1 = 'http://weibo.cn/search/mblog?hideSearchFrame=&keyword='
        url_p2 = '&page='
        for i in range(1,pageMaxNum):
            print(u'==========微博=====page'+str(i)+'=======================')
            url = url_p1 + keyWord+url_p2+str(i)
            page1 = self.session.get(url)
            soup = BeautifulSoup(page1.text ,"lxml")
            count = 0
            for e_line in soup.find_all('span'):
                count+=1
                print( '-------'+str(count))
                souptmp= BeautifulSoup(str(e_line).decode('utf-8'),'lxml')
                print (souptmp.get_text())
                # print str(e_line).decode('utf-8')

    def searchPerson(self, personNM):
        url_p1 = 'http://weibo.cn/search/user/?keyword='
        url_p2 = '&page='
        for i in range(1, pageMaxNum):
        # for i in xrange(1, 2):
            print(u'==========找人=====page'+str(i)+'=======================');
            url = url_p1 + personNM+url_p2+str(i)
            page1 = self.session.get(url)
            soup = BeautifulSoup(page1.text ,"lxml")
            count = 0
            for e_line in soup.find_all('tr'):
                count+=1
                print( '-------'+str(count))
                userInfo = e_line.findChildren('td')[1]
                userNM = userInfo.findChildren('a')[0].get_text()
                print (userNM)

if __name__ == '__main__':
    weibo = WeiBo(userNM, password)
    weibo.searchPerson(Search_Persom)
    weibo.searchWord(Search_Word)