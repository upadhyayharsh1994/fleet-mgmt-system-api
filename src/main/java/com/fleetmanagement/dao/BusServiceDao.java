package com.fleetmanagement.dao;

import java.util.List;

import com.fleetmanagement.domain.input.BusDetailsInput;
import com.fleetmanagement.domain.output.BusDetails;

public interface BusServiceDao {

	public BusDetails getBusDetails(String busId);

	public List<BusDetails> getAllBusDetails();

	public int reviseBusDetails(String busId, BusDetailsInput busDetails);

	public int removeBusDetails(String busId);
	
	public Boolean insertBusDetails(BusDetailsInput busDetails);
	
	
}