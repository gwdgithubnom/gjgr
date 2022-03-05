#! /usr/bin/env python

# Standard library imports.
import sys, os, re, cgi, socket, errno
import BaseHTTPServer
import shutil

from SocketServer import ThreadingMixIn
from BaseHTTPServer import BaseHTTPRequestHandler
from urllib import quote, unquote
from posixpath import normpath
from cStringIO import StringIO
from mimetypes import MimeTypes
from sys import path

class ThreadingHTTPServer(ThreadingMixIn, BaseHTTPServer.HTTPServer):
    pass

class RequestHandler(BaseHTTPRequestHandler):
    """ Handler to handle POST requests for actions.
    """
    serve_path = os.getcwd()
    def do_GET(self):
        """ Overridden to handle HTTP Range requests. """
        f = self.get_method_handle()
        if f:
            self.copy_file_range(f, self.wfile, 1024)
            f.close()
        else:
            self.send_error(404, "can't get path " + self.path)
            
    def do_POST(self):
        """Serve a POST request."""
        r, info = self.deal_post_data()
        f = StringIO()
        f.write('<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">')
        f.write("<html>\n<title>Upload Result Page</title>\n")
        f.write("<body>\n<h2>Upload Result Page</h2>\n")
        f.write("<hr>\n")
        if r:
            f.write("<strong>Success:</strong>")
        else:
            f.write("<strong>Failed:</strong>")
        f.write(info)
        f.write("<br><a href=\"%s\">back</a>" % self.headers['referer'])
        f.write("<hr><small>Powered By: bones7456, check new version at ")
        f.write("<a href=\"http://li2z.cn/?s=SimpleHTTPServerWithUpload\">")
        f.write("here</a>.</small></body>\n</html>\n")
        length = f.tell()
        f.seek(0)
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.send_header("Content-Length", str(length))
        self.end_headers()
        if f:
            shutil.copyfileobj(f, self.wfile)
            f.close()
            
    def deal_post_data(self):
        boundary = self.headers.plisttext.split("=")[1]
        remainbytes = int(self.headers['content-length'])
        line = self.rfile.readline()
        remainbytes -= len(line)
        if not boundary in line:
            return (False, "Content NOT begin with boundary")
        line = self.rfile.readline()
        remainbytes -= len(line)
        fn = re.findall(r'Content-Disposition.*name="file"; filename="(.*)"', line)
        if not fn:
            return (False, "Can't find out file name...")
        path = self.translate_path(self.path)
        fn = os.path.join(path, fn[0])
        while os.path.exists(fn):
            os.remove(fn)
        line = self.rfile.readline()
        remainbytes -= len(line)
        line = self.rfile.readline()
        remainbytes -= len(line)
        try:
            out = open(fn, 'wb')
        except IOError:
            return (False, "Can't create file to write, do you have permission to write?")
                
        preline = self.rfile.readline()
        remainbytes -= len(preline)
        while remainbytes > 0:
            line = self.rfile.readline()
            remainbytes -= len(line)
            if boundary in line:
                preline = preline[0:-1]
                if preline.endswith('\r'):
                    preline = preline[0:-1]
                out.write(preline)
                out.close()
                return (True, "File '%s' upload success!" % fn)
            else:
                out.write(preline)
                preline = line
        return (False, "Unexpect Ends of data.")
    def copy_file_range(self, in_file, out_file, buffer_size = 1024):
        """ Copy only the range in self.range_from/to. """
        in_file.seek(0)
        left_to_copy = os.fstat(in_file.fileno())[6] if type(in_file) is file else len(in_file.getvalue())
        # Add 1 because the range is inclusive
        bytes_copied = 0
        while bytes_copied < left_to_copy:
            read_buf = in_file.read(min(buffer_size, left_to_copy - bytes_copied))
            if len(read_buf) == 0:
                break
            out_file.write(read_buf)
            bytes_copied += len(read_buf)
        return bytes_copied
    
    def get_method_handle(self):
        """Common code for GET and HEAD commands.
        This sends the response code and MIME headers.
        Return value is either a file object (which has to be copied
        to the outputfile by the caller unless the command was HEAD,
        and must be closed by the caller under all circumstances), or
        None, in which case the caller has nothing further to do.
        """
        path = self.translate_path(self.path)
        if os.path.isdir(path):
            if not self.path.endswith('/'):
                return self.__redirect(path)
            return self.__list_directory(path)
        elif os.path.isfile(path):
            return self.__send_file(path);
        return None
    
    def __send_file(self, path):
        mimeTypes = MimeTypes((path, ))
        ctype = mimeTypes.guess_type(path)
        try:
            f = open(path, 'rb')
            self.send_response(200)
            self.send_header("Content-type", ctype)
            fs = os.fstat(f.fileno())
            self.send_header("Content-Length", str(fs[6]))
            self.send_header("Last-Modified", self.date_time_string(fs.st_mtime))
            self.end_headers()
            return f
        except Exception, e:
            raise
        return None
    
    def __redirect(self, path):
        self.send_response(301)
        self.send_header("Location", os.path.basename(path) + "/")
        self.end_headers()
        return None
    
    def __list_directory(self, path):
        """
        Return value is either a file object, or None (indicating an
        error).  In either case, the headers are sent, making the
        interface the same as for send_head().
        """
        try:
            dir_list = os.listdir(path)
        except os.error:
            self.send_error(404, "No permission to dir_list directory")
            return None
        dir_list.sort(key=lambda a: a.lower())
        f, old_stdout = StringIO(), sys.stdout
        sys.stdout = f
        displaypath = cgi.escape(unquote(self.path))
        print '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">'
        print "<html><title>Directory listing for %s</title>" % displaypath
        print "<body><h2>Directory listing for %s</h2>" % displaypath
        print "<hr>"
        print "<form ENCTYPE='multipart/form-data' method='post'>"
        print "<input name='file' type='file'/>"
        print "<input type='submit' value='upload'/>\n</form>"
        print "<hr>\n<ul>"
        for name in dir_list:
            fullname = os.path.join(path, name)
            displayname = linkname = name
            # Append / for directories or @ for symbolic links
            if os.path.isdir(fullname):
                displayname = name + "/"
                linkname = name + "/"
            if os.path.islink(fullname):
                displayname = name + "@"
                # Note: a link to a directory displays with @ and links with /
            print '<li><a href="%s">%s</a>\n' % (quote(linkname), cgi.escape(displayname))
        print "</ul>\n<hr>\n</body>\n</html>\n"
        sys.stdout = old_stdout
        self.send_response(200)
        encoding = sys.getfilesystemencoding()
        self.send_header("Content-type", "text/html; charset=%s" % encoding)
        self.send_header("Content-Length", str(f.tell()))
        self.end_headers()
        return f
    
    def translate_path(self, path):
        """ Override to handle redirects.
        """
        path = path.split('?',1)[0]
        path = path.split('#',1)[0]
        path = normpath(unquote(path))
        words = filter(lambda a : a != '' and a not in (os.curdir, os.pardir), path.split('/'))
        return os.path.join(self.serve_path, * words)

    # Private interface ######################################################

    def _get_range_header(self):
        """ Returns request Range start and end if specified.
        If Range header is not specified returns (None, None)
        """
        range_header = self.headers.getheader("Range")
        if range_header is None:
            return (None, None)
        if not range_header.startswith("bytes="):
            print "Not implemented: parsing header Range: %s" % range_header
            return (None, None)
        regex = re.compile(r"^bytes=(\d+)\-(\d+)?")
        rangething = regex.search(range_header)
        if rangething:
            from_val = int(rangething.group(1))
            if rangething.group(2) is not None:
                return (from_val, int(rangething.group(2)))
            else:
                return (from_val, None)
        else:
            print 'CANNOT PARSE RANGE HEADER:', range_header
            return (None, None)


def get_server(port=8000, next_attempts=0, serve_path=None):
    Handler = RequestHandler
    if serve_path:
        Handler.serve_path = serve_path
    while next_attempts >= 0:
        try:
            httpd = ThreadingHTTPServer(("", port), Handler)
            return httpd
        except socket.error as e:
            if e.errno == errno.EADDRINUSE:
                next_attempts -= 1
                port += 1
            else:
                raise

def main(args=None):
    if args is None:
        args = sys.argv[1:]

    PORT = 8000
    if len(args)>0:
        PORT = int(args[-1])
    serve_path = os.getcwd()
    if len(args) > 1:
        serve_path = os.path.abspath(args[-2])

    httpd = get_server(port=PORT, serve_path=serve_path)

    print "serving at port", PORT
    httpd.serve_forever()

if __name__ == "__main__" :
    main()
