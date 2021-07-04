package contests;

import java.util.HashMap;
import java.util.Map;

public class ReformatDate {
	
	public static String reformatDate(String date) {
		String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String,String> monthMap = new HashMap<>();
        for(int i=0;i<monthArray.length;i++) {
        	String prefix= (i+1<10) ? "0" : "";
        	monthMap.put(monthArray[i], prefix+(i+1));
        }
		StringBuilder sb = new StringBuilder();
        int dateLength = date.length();
        sb.append(date.substring(dateLength-4)).append("-");
        String month = date.substring(dateLength-8, dateLength-5);
        sb.append(monthMap.get(month)).append("-");
        CharSequence day = date.subSequence(0, 2);
        if(day.charAt(1)>=48 && day.charAt(1)<=57) {
        	sb.append(day);
        } else {
        	sb.append("0").append(day.charAt(0));
        }
        return sb.toString();
    }

	public static void main(String[] args) {
		String date="20th Oct 2052";
		System.out.println(ReformatDate.reformatDate(date));
		String date1="6th Jun 1933";
		System.out.println(ReformatDate.reformatDate(date1));
		String date2="26th May 1960";
		System.out.println(ReformatDate.reformatDate(date2));
	}

}
