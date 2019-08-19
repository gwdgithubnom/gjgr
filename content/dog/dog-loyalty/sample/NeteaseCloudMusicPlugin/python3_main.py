import re
import requests
import json
import urllib.request
import urllib.error
import os
import sys
import httplib2


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

minimumsize = 10
if(len(sys.argv)<2):
    debug_v='https://music.163.com/#/playlist?id=997781747'
else:
    print("fetching msg from %s \n" % sys.argv[1])
    debug_v=sys.argv[1]
url = re.sub("#/", "", debug_v)
p = PlayList(debug_v)
song_name,song_id=p.generate()
r = requests.get(url)
contents = r.text
res = r'<ul class="f-hide">(.*?)</ul>'
mm = re.findall(res, contents, re.S | re.M)
CURRENT_PATH = os.path.dirname(os.path.realpath(__file__))
if(mm):
    contents = mm[0]
else:
    print('Can not fetch information form URL. Please make sure the URL is right.\n')
    os._exit(0)
res = r'<li><a .*?>(.*?)</a></li>'
mm = re.findall(res, contents, re.S | re.M)
print("#######debug#######")
print(mm)
mm=song_name
print("#######debug#######")
for value in mm:
    url = 'http://sug.music.baidu.com/info/suggestion'
    payload = {'word': value, 'version': '2', 'from': '0'}
    print(value)

    r = requests.get(url, params=payload)
    contents = r.text
    d = json.loads(contents, encoding="utf-8")
    if d is not None and 'data' not in d:
        continue
    songid = d["data"]["song"][0]["songid"]
    print("find songid: %s" % songid)

    url = "http://music.baidu.com/data/music/fmlink"
    payload = {'songIds': songid, 'type': 'flac'}
    r = requests.get(url, params=payload)
    contents = r.text
    d = json.loads(contents, encoding="utf-8")
    if('data' not in d) or d['data'] == '':
        continue
    songlink = d["data"]["songList"][0]["songLink"]
    print("find songlink: ")
    if(len(songlink) < 10):
        print("\tdo not have flac\n")
        continue
    print(songlink)

    songdir = "songs_dir"
    if not os.path.exists(songdir):
        os.makedirs(songdir)

    songname = d["data"]["songList"][0]["songName"]
    artistName = d["data"]["songList"][0]["artistName"]
    filename = ("%s/%s/%s-%s.flac" %
                (CURRENT_PATH, songdir, songname, artistName))

    f = urllib.request.urlopen(songlink)
    # conn = http.client.HTTPConnection(songlink)
    # conn.request('GET', '/')
    # r1 = conn.getresponse()
    # page = r1.read().decode('utf-8')
    headers = requests.head(songlink).headers
    if 'Content-Length' in headers:
        size = round(int(headers['Content-Length']) / (1024 ** 2), 2)
    else:
        continue

    #Download unfinished Flacs again.
    if not os.path.isfile(filename) or os.path.getsize(filename) < minimumsize: #Delete useless flacs
        print("%s is downloading now ......\n\n" % songname)
        if size >= minimumsize:
            with open(filename, "wb") as code:
                    try:
                        ff=f.read()
                    except (Exception) as e:
                        ff = e.partial
                    code.write(ff)
                
        else:
            print("the size of %s (%r Mb) is less than 10 Mb, skipping" %
                  (filename, size))
    else:
        print("%s is already downloaded. Finding next song...\n\n" % songname)


print("\n================================================================\n")
print("Download finish!\nSongs' directory is %s/songs_dir" % os.getcwd())
