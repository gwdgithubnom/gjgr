import os
import sys
import time

from bcloud.App import App

LOG_INTERVAL = 30 * 86400  # 30 days

def main():
	# handle errors
	logfile = os.path.expanduser('~/.config/bcloud/bcloud-stderr.log')
	dirname = os.path.dirname(logfile)
	if not os.path.exists(dirname):
		try:
			os.makedirs(dirname)
		except Exception:
			sys.exit(1)
	if (os.path.exists(logfile) and
					time.time() - os.stat(logfile).st_ctime > LOG_INTERVAL):
		os.remove(logfile)
	sys.stderr = open(logfile, 'a')
	app = App()
	app.run(sys.argv)

if __name__ == '__main__':
	main()
