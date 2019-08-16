package org.gjgr.exam.contest.dj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        if (n <= 0) {
            return;
        }
        TreeMap<String, List<Date>> map = new TreeMap();
        SimpleDateFormat sdf0 = new SimpleDateFormat("MM.dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        for (int i = 0; i < n; i++) {
            String ds = sc.nextLine();
            String[] dakai = ds.split(" ");
            String data0 = dakai[0];
            String data1 = dakai[1];
            Date date = null;
            try {
                date = sdf.parse("2017." + ds);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                if (data1.compareTo("03:00:00") < 0) {
                    Date tmp = new Date(date.getTime() - 1000 * 60 * 60 * 24);
                    data0 = sdf0.format(tmp);
                }
                List<Date> list = map.get(data0);
                if (list == null) {
                    list = new ArrayList<>();
                    list.add(date);
                    map.put(data0, list);
                } else {
                    list.add(date);
                }
            }
        }
        String nowork1 = "12:30:00";
        String nowork2 = "13:59:59";
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        for (Map.Entry<String, List<Date>> entry : map.entrySet()) {
            if (entry.getValue().size() < 2) {
                continue;
            }
            List<Date> dates = entry.getValue();
            Collections.sort(dates);
            Date start = dates.get(0);
            Date end = dates.get(dates.size() - 1);
            String start1 = sdf2.format(start);
            String end1 = sdf2.format(end);
            if (start1.compareTo(nowork1) < 0 && (end1.compareTo(nowork1) >= 0 && end1.compareTo(nowork2) <= 0)) {
                try {
                    end = sdf.parse("2017." + sdf0.format(end) +" "+ nowork1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long value = (end.getTime() - start.getTime()) / 1000;
                System.out.println(entry.getKey() + " " + value);
            } else if (start1.compareTo(nowork1) >= 0 && (end1.compareTo(nowork1) >= 0 && end1.compareTo(nowork2) <= 0)) {
                continue;
            } else if (end1.compareTo(nowork2) > 0 && (start1.compareTo(nowork1) >= 0 && start1.compareTo(nowork2) <= 0)) {
                try {
                    start = sdf.parse("2017." + sdf0.format(start) +" "+ nowork2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long value = (end.getTime() - start.getTime()) / 1000;
                System.out.println(entry.getKey() + " " + value);
            } else if (start1.compareTo(nowork1) < 0 && end1.compareTo(nowork2) > 0) {
                long value = ((end.getTime() - start.getTime()) / 1000) - 3600 - 1800;
                System.out.println(entry.getKey() + " " + value);
            } else {
                long value = ((end.getTime() - start.getTime()) / 1000);
                System.out.println(entry.getKey() + " " + value);
            }
        }
    }
}