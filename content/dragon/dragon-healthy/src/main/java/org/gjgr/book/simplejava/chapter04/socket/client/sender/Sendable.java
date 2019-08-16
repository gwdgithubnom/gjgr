package org.gjgr.book.simplejava.chapter04.socket.client.sender;

import java.io.IOException;

import org.gjgr.book.simplejava.chapter04.socket.SocketWrapper;

public interface Sendable {
	
	public byte getSendType();

	public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
