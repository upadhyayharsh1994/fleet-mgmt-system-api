package com.fleetmanagement.util;

public class Queries {

	public static final String BUS_DETAILS_QUERY="SELECT * FROM fleetmanagementsystem.busdetails where busId = :busId";

	public static final String ALL_BUS_DETAILS="Select * FROM fleetmanagementsystem.busdetails order by busId DESC";

    public static final String REVSISE_BUS_DETAILS="Update fleetmanagementsystem.busdetails set makeYear=:makeYear,"
    		+ "numberOfWheel=:numberOfWheel,odometerReading=:odometerReading,scheduledDate=:scheduledDate,busName=:busName,isAirConditioned=:isAirConditioned,capacity=:capacity,bus_stat=:status,permitStatus=:permitStatus,"
    		+ "garageId=:garageId where busId=:busId";
    
    public static final String REMOVE_BUS_DETAIL="DELETE FROM fleetmanagementsystem.busdetails where busId = :busId";

    public static final String INSERT_BUS_DETAILS="insert\r\n" + 
    		"	into\r\n" + 
    		"	fleetmanagementsystem.busdetails (makeYear,\r\n" + 
    		"	numberOfWheel,\r\n" + 
    		"	odometerReading,\r\n" + 
    		"	isAirConditioned,\r\n" + 
    		"	capacity,\r\n" + 
    		"	bus_stat,\r\n" + 
    		"	scheduledDate,\r\n" + 
    		"	busName,\r\n" +
    		"	registrationNumber, \r\n" + 
    		"	permitStatus,\r\n" + 
    		"	garageId \r\n" + 
    		"	)\r\n" + 
    		"values (:makeYear,\r\n" + 
    		":numberOfWheel,\r\n" + 
    		":odometerReading,\r\n" + 
    		":isAirConditioned,\r\n" + 
    		":capacity,\r\n" + 
    		":valStat,\r\n" + 
    		":scheduledDate,\r\n" + 
    		":busName,\r\n" + 
    		":registrationNumber,\r\n" + 
    		":permit,\r\n" + 
    		":garageId\r\n" + 
    		")";
}

