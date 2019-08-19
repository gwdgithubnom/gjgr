/**
 * 
 */
package edu.contest.thoughtwork;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;

/**
 * @author gwd
 *
2016-06-02 20:00~22:00 7
2016-06-03 09:00~12:00 14
2016-06-04 14:00~17:00 22
2016-06-05 19:00~22:00 3
2016-06-06 12:00~15:00 15
2016-06-07 15:00~17:00 12
2016-06-08 10:00~13:00 19
2016-06-09 16:00~18:00 16
2016-06-10 20:00~22:00 5
2016-06-11 13:00~15:00 11
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BadmintonActivty badmintonActivty=new BadmintonActivty();
		Scanner scanner=new Scanner(System.in);
		String string=null;
		ArrayList<String> list=new ArrayList<String>();
		while(scanner.hasNext()){
			string=scanner.nextLine();
			
			if(string.equals("summary")){
				System.out.println("[Summary]\n");
				Iterator<String> iterator=list.iterator();
				while(iterator.hasNext()){
					System.out.println(iterator.next());
				}
				list=new ArrayList<String>();
				System.out.println("\nTotal Income:"+badmintonActivty.getIncome());
				System.out.println("Total Payment:"+badmintonActivty.getPayment());
				System.out.println("Profit:"+badmintonActivty.getProfit());
				badmintonActivty.setIncome(0);
				badmintonActivty.setPayment(0);
				badmintonActivty.setProfit(0);
			}else{				
				string=badmintonActivty.generateSummary(string);
				list.add(string);
			}
			
			
		}
		
	}

	
}
