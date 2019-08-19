import re
import requests
import os

# http://music.163.com/#/playlist?id=643439691
class PlayList:
    def __init__(self,url):
        self.url = url
        self.dicts = {}
    def generate(self):
        id = re.findall(r'id=(.*)',self.url)[0]
        print(id)
        j = requests.get('https://api.imjad.cn/cloudmusic/?type=playlist&id=' + id).json()
        ll = len(j['privileges'])

        # 歌曲名字列表
        song_name = []
        for i in range(0,ll):
            song_name.append(str(j['playlist']['tracks'][i]['name']))

        #获取歌曲ID
        print('正在获取歌曲id.......')
        song_id = []
        for i in range(0,len(j['privileges'])):
            song_id.append(str(j['privileges'][i]['id']))


        # 获取歌曲列表的URL
        print('整理歌曲列表中......')
        songs_url = []
        for id in song_id:
            songs_url.append('https://api.imjad.cn/cloudmusic/?type=song&id=%s&br=320000' % id)
        return song_name,song_id
def main():
    print('输入网址')
    p = PlayList(url=input())
    song_name,song_id=p.generate()
    print(song_name)
if __name__ == '__main__':
    main()
