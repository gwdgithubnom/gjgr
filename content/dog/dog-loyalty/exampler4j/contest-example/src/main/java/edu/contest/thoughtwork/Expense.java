package edu.contest.thoughtwork;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class Expense {
	
	private static Calendar calendar=Calendar.getInstance();

	public Expense() {
		// TODO Auto-generated constructor stub	 
	}
	private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE");
	
	public double getExpense(Date date,Time startTime,Time endTime){
		Calendar cal = Calendar.getInstance();  
		double sum=0;
	    cal.setTime(date);  	    
	    int week=cal.get(Calendar.DAY_OF_WEEK);
	    //log(startTime+"->"+getTimeToNumber(startTime)+"#"+endTime+"->"+getTimeToNumber(endTime));
	    if(week==Calendar.SATURDAY||week==Calendar.SUNDAY){   	
	      	if(getTimeToNumber(startTime)>=9&&getTimeToNumber(startTime)<=12){
	      		if(getTimeToNumber(endTime)<=12){
	      			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*40;
	      		}
	      	    else if(getTimeToNumber(endTime)<=18){
	    			sum=(12-getTimeToNumber(startTime))*40+(getTimeToNumber(endTime)-12)*50;
	    		}else if(getTimeToNumber(endTime)<=22){
	    			sum=(12-getTimeToNumber(startTime))*40+6*50+(getTimeToNumber(endTime)-18)*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else if(getTimeToNumber(startTime)>=12&&getTimeToNumber(startTime)<=18){
	    		if(getTimeToNumber(endTime)<=18){
	    			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*50;
	    		}else if(getTimeToNumber(endTime)<=22){
	    			sum=(18-getTimeToNumber(startTime))*50+(getTimeToNumber(endTime)-18)*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else if(getTimeToNumber(startTime)>=18&&getTimeToNumber(startTime)<=22){
	    		if(getTimeToNumber(endTime)<=22){
	    			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else{
	    		sum=0;
	    	}
	    
	    
	    }else{
	    	
	    	if(getTimeToNumber(startTime)>=9&&getTimeToNumber(startTime)<=12){
	      		if(getTimeToNumber(endTime)<=12){
	      			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*30;
	      		}
	      	    else if(getTimeToNumber(endTime)<=18){
	    			sum=(12-getTimeToNumber(startTime))*30+(getTimeToNumber(endTime)-12)*50;
	    		}else if(getTimeToNumber(endTime)<=20){
	    			sum=(12-getTimeToNumber(startTime))*30+6*50+(getTimeToNumber(endTime)-18)*80;
	    		}else if(getTimeToNumber(endTime)<=22){
	    			sum=(12-getTimeToNumber(startTime))*30+6*50+2*80+(getTimeToNumber(endTime)-20)*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else if(getTimeToNumber(startTime)>=12&&getTimeToNumber(startTime)<=18){
	    		if(getTimeToNumber(endTime)<=18){
	    			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*50;
	    		}else if(getTimeToNumber(endTime)<=20){
	    			sum=(12-getTimeToNumber(startTime))*50+(getTimeToNumber(endTime)-18)*80;
	    		}else if(getTimeToNumber(endTime)<=22){
	    			sum=6*50+2*80+(getTimeToNumber(endTime)-20)*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else if(getTimeToNumber(startTime)>=18&&getTimeToNumber(startTime)<=20){
	    		if(getTimeToNumber(endTime)<=20){
	    			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*80;
	    		}else if(getTimeToNumber(endTime)<=22){
	    			sum=(20-getTimeToNumber(startTime))*80+(getTimeToNumber(endTime)-20)*60;
	    		}else{
	    			sum=0;
	    		}
	    	}else if(getTimeToNumber(startTime)>=20&&getTimeToNumber(startTime)<=22){
	    		if(getTimeToNumber(endTime)<=22){
	    			sum=(getTimeToNumber(endTime)-getTimeToNumber(startTime))*80;
	    		}else{
	    			sum=0;
	    		}
	    	}	    	
	    	else{
	    		sum=0;
	    	}
	    	
	    	
	    }
		return sum;
	}
	
	public double getTimeToNumber(Time time){
		return time.getHours()+time.getMinutes()/60;
	}
	
	public double getExpense(Date startDate,Date endDate){
		Time startTime=new Time(startDate.getTime());
		Time endTime=new Time(endDate.getTime());
		return getExpense(startDate,startTime, endTime);
	}
	
	public void getTime(){
		Time time=new Time((new Date()).getTime());
		log(time.getHours());

	}

	@Test
	public void test(){
		getTime();
	}
	
	public void log(Object object){
		System.out.println("Expense "+new Date()+" : "+object);
	}

}
