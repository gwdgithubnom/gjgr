package org.gjgr.book.corejava.v2ch11.warehouse1;

import java.rmi.*;
import javax.naming.*;
import org.gjgr.book.corejava.v2ch11.warehouse2.WarehouseImpl;

/**
 * This server program instantiates a remote warehouse object, registers it with the naming
 * service, and waits for clients to invoke methods.
 * @version 1.12 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseServer
{
   public static void main(String[] args) throws RemoteException, NamingException
   {
      System.out.println("Constructing server implementation...");
      org.gjgr.book.corejava.v2ch11.warehouse2.WarehouseImpl centralWarehouse = new WarehouseImpl();

      System.out.println("Binding server implementation to registry...");
      Context namingContext = new InitialContext();
      namingContext.bind("rmi:central_warehouse", centralWarehouse);

      System.out.println("Waiting for invocations from clients...");
   }
}
