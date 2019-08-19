/**
 * 
 */
package edu.contest.thoughtwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author gwd
 *
 */
public class BadmintonerBuilder {

	private int badmintoner;
	private Date startDate;
    private Date endDate;
    private String builderInfo;
    private static SimpleDateFormat simpleDateFormat =   new SimpleDateFormat("yyyy-MM-ddHH:mm");
	/**
	 * 
	 */
	public BadmintonerBuilder() {
		// TODO Auto-generated constructor stub
	}
	//2016-06-02 20:00~22:00 7
	public void builder(String str){
		
		String[] strings=str.split(" ");
		String[] times=strings[1].split("~");
		builderInfo=strings[0]+" "+strings[1];
		try {
			startDate=simpleDateFormat.parse(strings[0]+times[0]);
			endDate=simpleDateFormat.parse(strings[0]+times[1]);
			badmintoner=Integer.parseInt(strings[2]);
			//log(times[1]+"#"+times[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log("Exception, for format or parse");
			e.printStackTrace();
		}
		
	}
	
	public String getBuilderInfo() {
		return builderInfo;
	}
	public void setBuilderInfo(String builderInfo) {
		this.builderInfo = builderInfo;
	}
	public int getBadmintoner() {
		return badmintoner;
	}
	public void setBadmintoner(int badmintoner) {
		this.badmintoner = badmintoner;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Test
	public void test(){
	     Scanner scanner=new Scanner(System.in);
	     builder(scanner.nextLine());
	}
	
	public void log(Object object){
		System.out.println("BadmintonerBuilder "+new Date()+" : "+object);
	}
	
	
}
