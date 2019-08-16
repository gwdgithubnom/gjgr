package org.gjgr.book.corejava.v1ch09.comboBox;

import java.awt.*;
import javax.swing.*;

/**
 * @version 1.34 2012-05-06
 * @author Cay Horstmann
 */
public class ComboBoxTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new ComboBoxFrame();
               frame.setTitle("ComboBoxTest");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}