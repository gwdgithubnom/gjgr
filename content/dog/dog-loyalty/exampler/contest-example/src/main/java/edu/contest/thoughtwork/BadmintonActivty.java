package edu.contest.thoughtwork;

import java.awt.print.Printable;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

public class BadmintonActivty {
	private static Expense expense=new Expense();
	private BadmintonerBuilder badmintonerBuilder;
	private int income=0;
	private int payment=0;
	private int profit=0;
/*	public boolean startActivity() {
		Scanner scanner=new Scanner(System.in);
		String string=scanner.nextLine();
		if(badmintonerBuilder==null)
			badmintonerBuilder=new BadmintonerBuilder();
		badmintonerBuilder.builder(string);
		return true;
	}

	public boolean compute() {
		double sum=expense.getExpense(badmintonerBuilder.getStartDate(), badmintonerBuilder.getEndDate());
		print(((int)sum));
		return true;
	}*/
	
	public String simulate(){
		String str=null;
		int count=0;
		int M=badmintonerBuilder.getBadmintoner();
		int X=M%6;
		int T=M/6;	
		if(T==0&&X<4){
			count=0;
		}else if(T==0&&X>=4){
			count=1;
		}else if(T==1){
			count=2;
		}else if((T==2||T==3)&&X>=4){
			count=T+1;
		}else if((T==2||T==3)&&X<4){
			count=T;
		}else if(T>3){
			count=T;
		}
		if(count>0){
			int sum=(int)expense.getExpense(badmintonerBuilder.getStartDate(), badmintonerBuilder.getEndDate());		
			str=badmintonerBuilder.getBuilderInfo()+" "+String.format("%+d",30*M)+" "+String.format("%+d",sum*count*-1)+" "+String.format("%+d",30*M-sum*count*1);
			income+=30*M;
			payment+=sum*count;
			profit+=30*M-sum*count;
		}
		else{
			str=badmintonerBuilder.getBuilderInfo()+" +0 -0 0";
		}
		
	    return str;
	}
	
	
	
	
	public String generateSummary(String input){
		if(badmintonerBuilder==null)
			badmintonerBuilder=new BadmintonerBuilder();
		badmintonerBuilder.builder(input);
		return simulate();
	}
	
	
	public int getIncome() {
		return income;
	}




	public void setIncome(int income) {
		this.income = income;
	}




	public int getPayment() {
		return payment;
	}




	public void setPayment(int payment) {
		this.payment = payment;
	}




	public int getProfit() {
		return profit;
	}




	public void setProfit(int profit) {
		this.profit = profit;
	}




	public boolean print(int sum){
		return true;
	}
	
	@Test
	public void test(){
		log("start test");
		Scanner scanner=new Scanner(System.in);
		while(true){
			String string=scanner.nextLine();
			string=generateSummary(string);
			log(string);
		}
		//System.out.println(generateSummary(string));
	}
	
	public void log(Object object){
		System.out.println(new Date()+" BadmintonActivty "+" : "+object);
	}
}
