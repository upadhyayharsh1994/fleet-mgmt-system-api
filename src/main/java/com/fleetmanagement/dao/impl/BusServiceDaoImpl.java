package com.fleetmanagement.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fleetmanagement.dao.BusServiceDao;
import com.fleetmanagement.domain.input.BusDetailsInput;
import com.fleetmanagement.domain.output.BusDetails;
import com.fleetmanagement.exception.ExceptionEnum;
import com.fleetmanagement.exception.FleetException;
import com.fleetmanagement.util.Queries;

@Repository
public class BusServiceDaoImpl implements BusServiceDao{

	private static final Logger log = LoggerFactory.getLogger(BusServiceDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public BusDetails getBusDetails(String busId) {	
		List<BusDetails> busDetails = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("busId", busId);
		try {
			List<Map<String, Object>> busDetailsMap = namedParameterJdbcTemplate.queryForList(Queries.BUS_DETAILS_QUERY, paramMap);
			if(ObjectUtils.isEmpty(busDetailsMap)) {
				
				return new BusDetails();
			}
			return generateBusResponse(busDetailsMap).get(0);
		} catch (Exception e) {
			log.error("Error in Retreiving details from DB");
			throw new FleetException("Exception : RemoveBusDetails:::", e);
		}
	}
	
	@Override
	public List<BusDetails> getAllBusDetails() {	
		List<BusDetails> busDetails = new ArrayList<>();
		try {
			List<Map<String, Object>> busDetailsMap = namedParameterJdbcTemplate.queryForList(Queries.ALL_BUS_DETAILS, new HashMap<>());
			if(ObjectUtils.isEmpty(busDetailsMap)) {
				return busDetails;
			}
			return generateBusResponse(busDetailsMap);
		} catch (DataAccessException dex) {
			log.error("Error in Retreiving details from DB");
			throw dex;
		}
		
	}
	
	@Override
	public int reviseBusDetails(String busId, BusDetailsInput busDetails) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("busId", busId);
		paramMap.put("busName", busDetails.getBusName());
		paramMap.put("status", busDetails.getStatus());
		paramMap.put("capacity",busDetails.getCapacity());
		paramMap.put("odometerReading", busDetails.getOdometerReading());
		paramMap.put("makeYear",busDetails.getMakeYear());
		paramMap.put("numberOfWheel",busDetails.getNumberOfWheels());
		paramMap.put("isAirConditioned",busDetails.getAirConditioner());
		paramMap.put("permitStatus", busDetails.getPermitStatus());
		paramMap.put("garageId",busDetails.getGarageId());
		paramMap.put("scheduledDate",busDetails.getScheduledDate());
		int rowsAffected=0;
		try {
			rowsAffected = namedParameterJdbcTemplate.update(Queries.REVSISE_BUS_DETAILS, paramMap);
			return rowsAffected;
		} catch (Exception e){
			throw new FleetException("Exception in reviseBusDetails:: ", e);
		}
	}
	
	@Override
	public int removeBusDetails(String busId) {	
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("busId", busId);
		try {
			int rowsAffected = namedParameterJdbcTemplate.update(Queries.REMOVE_BUS_DETAIL, paramMap);	
			return rowsAffected;
		} catch (Exception e) {
			log.error("Error in Removing details from DB");
			throw new FleetException("Exception : RemoveBusDetails:::", e);
		}   
	}

	private List<BusDetails> generateBusResponse(List<Map<String, Object>> detailsMap) {
		List<BusDetails> listOfBusDetails = new ArrayList<BusDetails>();
		
		for(Map<String, Object> busRecord : detailsMap) {
			BusDetails busDetails=new BusDetails();
			if(busRecord.get("busId") != null )
				busDetails.setBusId(Integer.parseInt(busRecord.get("busId").toString()));
			if(busRecord.get("makeYear") != null )
				busDetails.setMakeYear(busRecord.get("makeYear").toString());
			if(busRecord.get("numberOfWheel") != null )
				busDetails.setNumberOfWheels(Integer.parseInt(busRecord.get("numberOfWheel").toString()));
			if(busRecord.get("odometerReading") != null )
				busDetails.setOdometerReading(Long.valueOf(busRecord.get("odometerReading").toString()));
			if(busRecord.get("isAirConditioned") != null )
				busDetails.setAirConditioner(busRecord.get("isAirConditioned").toString());
			if(busRecord.get("capacity") != null )
				busDetails.setCapacity(busRecord.get("capacity").toString());
			if(busRecord.get("bus_stat") != null )
				busDetails.setStatus(busRecord.get("bus_stat").toString());
			if(busRecord.get("registrationNumber") != null )
				busDetails.setRegistrationNumber(busRecord.get("registrationNumber").toString());
			if(busRecord.get("PermitStatus") != null )
				busDetails.setPermitStatus(busRecord.get("PermitStatus").toString());
			if(busRecord.get("garageId") != null )
				busDetails.setGarageId(Integer.parseInt(busRecord.get("garageId").toString()));
			if(busRecord.get("busName") != null )
				busDetails.setBusName((String)busRecord.get("busName"));
			if(busRecord.get("scheduledDate") != null )
				busDetails.setScheduledDate((Date)busRecord.get("scheduledDate"));
			listOfBusDetails.add(busDetails);
		}
	
		return listOfBusDetails;
		
	}

	@Override
	public Boolean insertBusDetails(BusDetailsInput busDetails) {
		log.info("Inserting the following nus details:: {}", busDetails);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("isAirConditioned", StringUtils.isEmpty(busDetails.getAirConditioner()) ? "" : busDetails.getAirConditioner());
		paramMap.put("capacity", StringUtils.isEmpty(busDetails.getCapacity()) ? "" : busDetails.getCapacity());
		paramMap.put("garageId", ObjectUtils.isEmpty(busDetails.getGarageId()) ? "" : busDetails.getGarageId());
		paramMap.put("makeYear", StringUtils.isEmpty(busDetails.getMakeYear()) ? "" : busDetails.getMakeYear());
		paramMap.put("numberOfWheel", ObjectUtils.isEmpty(busDetails.getNumberOfWheels()) ? 0 : busDetails.getNumberOfWheels());
		paramMap.put("odometerReading", ObjectUtils.isEmpty(busDetails.getOdometerReading()) ? 0L : busDetails.getOdometerReading());
		paramMap.put("valStat", StringUtils.isEmpty(busDetails.getStatus()) ? "" : busDetails.getStatus());
		paramMap.put("registrationNumber", StringUtils.isEmpty(busDetails.getRegistrationNumber()) ? "" : busDetails.getRegistrationNumber());
		paramMap.put("permit", StringUtils.isEmpty(busDetails.getPermitStatus()) ? "" : busDetails.getPermitStatus());
		paramMap.put("busName", StringUtils.isEmpty(busDetails.getBusName()) ? "" : busDetails.getBusName());
		paramMap.put("scheduledDate", ObjectUtils.isEmpty(busDetails.getScheduledDate()) ? "" : busDetails.getScheduledDate());
		try {
			int count = namedParameterJdbcTemplate.update(Queries.INSERT_BUS_DETAILS, paramMap);
			if(count > 0) {
				return true;
			}
		} catch(Exception e) {
			throw new FleetException(ExceptionEnum.INPUT_VALIDATION_EXCEPTION, "Input Failed");
		//	throw new FleetException("Exception : InsertBusDetails:::", e);
		}
		return false;
	}


}
