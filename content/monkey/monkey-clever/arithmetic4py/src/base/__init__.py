import logging
import logging.handlers
import logging.config
log_file='log.log'

# to define a logHander to start logging something
logHandler=logging.handlers.RotatingFileHandler(log_file,maxBytes=1024*1024,backupCount=5)

formatString='%(asctime)s - %(levelname)s:%(lineo)s - %(module)s - %(name)s -%(message)s'
logformatter=logging.Formatter()
logHandler.setFormatter(formatString)

logger=logging.getLogger('init')
logHandler.addFilter(logHandler)
logger.setLevel(logging.DEBUG)

logger.debug("pring some info about flag... ")

############page line###############
logging.config.fileConfig("logging.conf")
# start a logger
logger=logging.getLogger("abc")
logger.debug("this is debug info...")