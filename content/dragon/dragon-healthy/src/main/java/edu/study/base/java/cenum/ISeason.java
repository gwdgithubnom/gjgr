package edu.study.base.java.cenum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gwd on 9/2/2016.
 * a enum test
 */
public enum ISeason {

    /**
     * These enum code should be at first line, or it would call error.
     */
    SPRING(1, 3){
        @Override
        public int getNote(){
            return 0;
        }
    },
    SUMMER(4,6){
        @Override
        public int getNote(){
            return 11;
        }
    },
    AUTUMN(7,9){
        @Override
        public int getNote(){
            return 22;
        }
    },
    WINTER(10,12){
        @Override
        public int getNote(){
            return 33;
        }
    };


    private final int startMonth ;// for the SPRING(?,?) attribute?

    private final int endMonth;

    public abstract int getNote();

    private static final Map<Integer,ISeason> monthLookup=new HashMap<>();


    static {
        for(ISeason s: EnumSet.allOf(ISeason.class))
            monthLookup.put(s.startMonth-1,s);
    }

    private ISeason(int startMonth, int endMonth){
        this.startMonth=startMonth;
        this.endMonth=endMonth;
    }

    public static ISeason get(int month){
        if((month>12)||(month<1))
            throw new IndexOutOfBoundsException();
        return monthLookup.get(((month-1)/3)*3);
    }

    /** setter getter method**/
    public int getStartMonth(){
        return startMonth;
    }

    public int getEndMonth(){
        return endMonth;
    }
}
