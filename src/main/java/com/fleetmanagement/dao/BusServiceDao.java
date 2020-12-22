package com.fleetmanagement.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fleetmanagement.domain.input.BusDetailsInput;
import com.fleetmanagement.domain.output.BusDetails;

public interface BusServiceDao {

	public BusDetails getBusDetails(String busId);

	public List<BusDetails> getAllBusDetails();

	public int reviseBusDetails(String busId, BusDetailsInput busDetails, MultipartFile busImage);

	public int removeBusDetails(String busId);
	
	public Boolean insertBusDetails(BusDetailsInput busDetails, MultipartFile busImage);
	
	
}
