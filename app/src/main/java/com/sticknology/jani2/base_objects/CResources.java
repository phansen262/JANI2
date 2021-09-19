package com.sticknology.jani2.base_objects;

public class CResources {

    public enum WeekDay {
        MONDAY(0, "Mon"),
        TUESDAY(1, "Tue"),
        WEDNESDAY(2, "Wed"),
        THURSDAY(3, "Thu"),
        FRIDAY(4, "Fri"),
        SATURDAY(5, "Sat"),
        SUNDAY(6, "Sun");
        public final int weekIndex;
        public final String abbreviation;
        WeekDay(int index, String abbreviation){
            this.weekIndex = index;
            this.abbreviation = abbreviation;
        }
    }

    //TODO:  Add leap year functionality before 2024
    public enum Month {
        JANUARY(0, 31, "Jan"),
        FEBRUARY(1, 28, "Feb"),
        MARCH(2, 31, "Mar"),
        APRIL(3, 30, "Apr"),
        MAY(4, 31, "May"),
        JUNE(5, 30, "Jun"),
        JULY(6, 31, "Jul"),
        AUGUST(7, 31, "Aug"),
        SEPTEMBER(8, 30, "Sep"),
        OCTOBER(9, 31, "Oct"),
        NOVEMBER(10, 30, "Nov"),
        DECEMBER(11, 31, "Dec");
        public final int monthIndex;
        public final int daysInMonth;
        public final String abbreviation;
        Month(int index, int days, String abbreviation){
            this.monthIndex = index;
            this.daysInMonth = days;
            this.abbreviation = abbreviation;
        }
    }

    //Get correct day from three letter abbreviation
    public WeekDay getDay(String abbreviation){
        for(WeekDay weekDay : WeekDay.values()){
            if(weekDay.abbreviation.equals(abbreviation)){
                return weekDay;
            }
        }
        throw new NullPointerException("No weekday found for abbreviation");
    }

    //Get correct month from three letter abbreviation
    public Month getMonth(String abbreviation){
        for(Month month : Month.values()){
            if(month.abbreviation.equals(abbreviation)){
                return month;
            }
        }
        throw new NullPointerException("No month found for abbreviation");
    }

    //Get correct day from week index
    public WeekDay getDay(int index){
        for(WeekDay weekDay : WeekDay.values()){
            if(weekDay.weekIndex == index){
                return weekDay;
            }
        }
        throw new NullPointerException("No weekday found for index");
    }

    //Get correct month from three letter abbreviation
    public Month getMonth(int index){
        for(Month month : Month.values()){
            if(month.monthIndex == index){
                return month;
            }
        }
        throw new NullPointerException("No month found for index");
    }
}
