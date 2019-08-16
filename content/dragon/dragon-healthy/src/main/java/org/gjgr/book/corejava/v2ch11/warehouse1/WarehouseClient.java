package org.gjgr.book.corejava.v2ch11.warehouse1;

import java.rmi.*;
import java.util.*;
import javax.naming.*;
import org.gjgr.book.corejava.v2ch11.warehouse2.Warehouse;

/**
 * A client that invokes a remote method.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
      Context namingContext = new InitialContext();
      
      System.out.print("RMI registry bindings: ");
      Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
      while (e.hasMoreElements())
         System.out.println(e.nextElement().getName());
      
      String url = "rmi://localhost/central_warehouse";      
      org.gjgr.book.corejava.v2ch11.warehouse2.Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      
      String descr = "Blackwell Toaster";
      double price = centralWarehouse.getPrice(descr);
      System.out.println(descr + ": " + price);
   }
}
