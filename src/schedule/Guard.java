/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import static GUI.Home_Frame.RemoveSpace;

/**
 *
 * @author omer
 */
public class Guard {
	
	private String name;
	private int morning,after_noon,night,weeknight,sat_morning,sat_night,sat_night_week,sat_morning_week,shifts_week1,shifts_week2,mornin_week1,noon_week1;
        private int temp_sat_morning = 0,temp_sat_night = 0,temp_night = 0;
	private boolean priority,authority;
        private boolean[] min_serv;
        private int[] serv_morning,serv_noon,morning_min,noon_min,res_morning,res_noon;
	public Guard() {
		this.name = "";
		this.morning = 0;
		this.after_noon = 0;
		this.weeknight = 0;
                this.night = 0;
		this.sat_night = 0;
		this.sat_morning = 0;
		this.sat_night_week =0;
		this.sat_morning_week =0;
                this.shifts_week1 = 0;
                this.shifts_week2 = 0;
                this.mornin_week1 = 0;
                this.noon_week1 = 0;
		this.priority = false;
		this.authority = false;
                this.serv_noon = new int[2];
                serv_noon = resetArray(serv_noon);
                this.serv_morning = new int[2];
                serv_morning = resetArray(serv_morning);
                this.morning_min = new int[2];
                morning_min = resetArray(morning_min);
                this.noon_min = new int[2];
                noon_min = resetArray(noon_min);
                this.res_morning =  new int[2];
                this.res_noon = new int[2];
                this.min_serv = new boolean[2];
                for(int i=0;i<2;i++){
                    res_morning[i] = -1;
                    res_noon[i] = -1;
                    min_serv[i] = false;
                }
	}
	public Guard(String name, int night){
		this.name = name;
		this.night = night;
		this.morning = 0;
		this.after_noon = 0;
		this.weeknight = 0;
		this.sat_night = 0;
		this.sat_morning = 0;
		this.sat_night_week =0;
		this.sat_morning_week =0;
                this.shifts_week1 = 0;
                this.shifts_week2 = 0;
                this.mornin_week1 = 0;
                this.noon_week1 = 0;
		this.priority = false;
		this.authority = false;
                this.serv_noon = new int[2];
                serv_noon = resetArray(serv_noon);
                this.serv_morning = new int[2];
                serv_morning = resetArray(serv_morning);
                this.morning_min = new int[2];
                morning_min = resetArray(morning_min);
                this.noon_min = new int[2];
                noon_min = resetArray(noon_min);
                this.res_morning =  new int[2];
                this.res_noon = new int[2];
                this.min_serv = new boolean[2];
                for(int i=0;i<2;i++){
                    res_morning[i] = -1;
                    res_noon[i] = -1;
                    min_serv[i] = false;
                }
	}
    public void resetGuard(){
        this.name = "";
        this.night = 0;
	this.morning = 0;
	this.after_noon = 0;
	this.weeknight = 0;
	this.sat_night = 0;
	this.sat_morning = 0;
	this.sat_night_week =0;
	this.sat_morning_week =0;
        this.shifts_week1 = 0;
        this.shifts_week2 = 0;
        this.mornin_week1 = 0;
        this.noon_week1 = 0;
	this.priority = false;
	this.authority = false;
        this.serv_noon = new int[2];
        serv_noon = resetArray(serv_noon);
        this.serv_morning = new int[2];
        serv_morning = resetArray(serv_morning);
        this.morning_min = new int[2];
        morning_min = resetArray(morning_min);
        this.noon_min = new int[2];
        noon_min = resetArray(noon_min);
    }
    public void reCount(String[][] organized){
        int[] tempCount = new int[5];
        tempCount = resetArray(tempCount);
         for(int i=0;i<14;i++){
               for(int j=0;j<3;j++){
                   if(i<5 || (i > 6 && i < 12)){
                       if(isInOrganized(organized[i][j], name))
                           tempCount[j]++;
                   }
                   if(i == 5 || i == 12){
                       if(isInOrganized(organized[i][j], name))
                           tempCount[3]++;
                   }
                   if(i == 6 || i ==13){
                       if(j == 0){
                           if(isInOrganized(organized[i][j], name))
                           tempCount[4]++;
                       }
                       else{
                           if(isInOrganized(organized[i][j], name))
                           tempCount[3]++;
                       }
                   }
               }
         }
         morning = tempCount[0];
         after_noon = tempCount[1];
         weeknight = tempCount[2];
         sat_night_week = tempCount[3];
         sat_morning_week = tempCount[4];
    }
    public void countGot(String[][] organized){
        noon_min = resetArray(noon_min);
        morning_min = resetArray(morning_min);
           for(int i=0;i<5;i++){
               for(int j=0;j<2;j++){
                   if(j==0){
                       if(isInOrganized(organized[i][j], name)){
                           morning_min[0]++;
                       }
                   }
                   else{
                       if(isInOrganized(organized[i][j], name)){
                            noon_min[0]++;
                       }
                   }
               }
           }
           for(int i=7;i<12;i++){
               for(int j=0;j<2;j++){
                   if(j==0){
                       if(isInOrganized(organized[i][j], name)){
                           morning_min[1]++;
                       }
                   }
                   else{
                       if(isInOrganized(organized[i][j], name)){
                           noon_min[1]++;
                       }
                   }
               }
           }
    }
    public static boolean isInOrganized(String day,String name){
            day = RemoveSpace(day);
            if(!day.equals("")){
            String[] split = day.split(",");
            name = RemoveSpace(name);
            for(int i=0;i<split.length;i++){
                split[i] =RemoveSpace(split[i]);
                if(split[i].equals(name))
                    return true;
            }
            }
            return false;
        }
    public void countServed(String[][] days){
        boolean skip = false;
           for(int i=0;i<5;i++){
               skip = false;
               for(int j=0;j<2;j++){
                   if(j==0){
                       if(isInDay(days[i][j], name)){
                           serv_morning[0]++;
                           shifts_week1++;
                           skip = true;
                       }
                   }
                   else{
                       if(isInDay(days[i][j], name)){
                           if(!skip)
                               shifts_week1++;
                            serv_noon[0]++;
                       }
                   }
               }
           }
           if(shifts_week1 < 3)
               min_serv[0] = true;
           for(int i=7;i<12;i++){
               skip = false;
               for(int j=0;j<2;j++){
                   if(j==0){
                       if(isInDay(days[i][j], name)){
                           serv_morning[1]++;
                           shifts_week2++;
                           skip = true;
                       }
                   }
                   else{
                       if(isInDay(days[i][j], name)){
                           if(!skip)
                               shifts_week2++;
                           serv_noon[1]++;
                       }
                   }
               }
           }
           if(shifts_week2 < 3)
               min_serv[1] = true;
    }
    public static boolean isInDay(String day,String name){
            String[] split = day.split(System.getProperty("line.separator")); 
            //String[] split = day.split("\n");
            name = RemoveSpace(name);
            for(int i=0;i<split.length;i++){
                split[i] =RemoveSpace(split[i]);
                if(split[i].equals(name))
                    return true;
            }
            return false;
    }

    public int getMornin_week1() {
        return mornin_week1;
    }

    public void setMornin_week1(int mornin_week1) {
        this.mornin_week1 = mornin_week1;
    }

    public int getNoon_week1() {
        return noon_week1;
    }

    public void setNoon_week1(int noon_week1) {
        this.noon_week1 = noon_week1;
    }
    public int getShifts_week1() {
        return shifts_week1;
    }

    public void setShifts_week1(int shifts_week1) {
        this.shifts_week1 = shifts_week1;
    }

    public int getShifts_week2() {
        return shifts_week2;
    }

    public void setShifts_week2(int shifts_week2) {
        this.shifts_week2 = shifts_week2;
    }
    public void setMin_serv(int index,boolean is){
            min_serv[index] = is;
        }
        public boolean getMin_serv(int index){
            return min_serv[index];
        }
        public void setNum_res_morning(int index,int num){
            res_morning[index] = num;
        }
        public void setNum_res_noon(int index,int num){
            res_noon[index] = num;
        }
        public int getNum_res_morning(int index){
            return res_morning[index];
        }
        public int getNum_res_noon(int index){
            return res_noon[index];
        }
        public int getNum_Serv_morning(int index){
            return serv_morning[index];
        }
        public int getNum_noon_min(int index){
            return noon_min[index];
        }
        public int getNum_morning_min(int index){
            return morning_min[index];
        }
        public int getNum_Serv_noon(int index){
            return serv_noon[index];
        }
        public void addToServ_noon(int index,int add){
            serv_noon[index] += add;
        }
        public void addToMorning_min(int index,int add){
            morning_min[index] += add;
        }
        public void addToServ_morning(int index,int add){
            serv_morning[index] += add;
        }
        public void addToNoon_min(int index,int add){
            noon_min[index] += add;
        }
        private int[] resetArray(int[] array){
            for(int i=0;i<array.length;i++)
                array[i] = 0;
            return array;
        }
	public int getSat_night_week() {
		return sat_night_week;
	}
	public void setSat_night_week(int sat_night_week) {
		this.sat_night_week = sat_night_week;
	}
	public int getSat_morning_week() {
		return sat_morning_week;
	}
	public void setSat_morning_week(int sat_morning_week) {
		this.sat_morning_week = sat_morning_week;
	}
	public boolean isAuthority() {
		return authority;
	}
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMorning() {
		return morning;
	}
	public void setMorning(int morning) {
		this.morning = morning;
	}
	public int getAfter_noon() {
		return after_noon;
	}
	public void setAfter_noon(int after_noon) {
		this.after_noon = after_noon;
	}
	public int getNight() {
		return night;
	}
	public void setNight(int night) {
		this.night = night;
	}
	public boolean isPriority() {
		return priority;
	}
	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	public int getWeeknight() {
		return weeknight;
	}
	public void setWeeknight(int weeknight) {
		this.weeknight = weeknight;
	}
	public int getSat_morning() {
		return sat_morning;
	}
	public void setSat_morning(int sat_morning) {
		this.sat_morning = sat_morning;
	}
	public int getSat_night() {
		return sat_night;
	}
	public void setSat_night(int sat_night) {
		this.sat_night = sat_night;
	}
}
