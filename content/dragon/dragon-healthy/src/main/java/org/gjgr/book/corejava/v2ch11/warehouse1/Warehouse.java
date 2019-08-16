package org.gjgr.book.corejava.v2ch11.warehouse1;

import java.rmi.*;

/**
   The remote interface for a simple warehouse.
   @version 1.0 2007-10-09
   @author Cay Horstmann
*/
public interface Warehouse extends Remote
{  
   double getPrice(String description) throws RemoteException;
}
