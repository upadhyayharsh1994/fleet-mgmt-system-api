package com.fleetmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fleetmanagement.dao.BusServiceDao;
import com.fleetmanagement.domain.input.BusDetailsInput;
import com.fleetmanagement.domain.output.BusDetails;
import com.fleetmanagement.service.BusService;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusServiceDao busServiceDao;
	
	@Override
	public BusDetails getBusDetails(String busId) {
		return busServiceDao.getBusDetails(busId);
	}

	@Override
	public List<BusDetails> getAllBusDetails() {
		return busServiceDao.getAllBusDetails();
	}

	@Override
	public int reviseBusDetails(String busId, BusDetailsInput busDetails,MultipartFile busImage) {
		// TODO Auto-generated method stub
		return busServiceDao.reviseBusDetails(busId, busDetails,busImage);
	}

	@Override
	public int removeBusDetails(String busId) {
		// TODO Auto-generated method stub
		return busServiceDao.removeBusDetails(busId);
	}

	@Override
	public Boolean insertBusDetails(BusDetailsInput busDetails,MultipartFile busImage) {
		// TODO Auto-generated method stub
		return busServiceDao.insertBusDetails(busDetails,busImage);
	}

	@Override
	public double getResaleValue(String busId) {
		// TODO Auto-generated method stub
		BusDetails busDetails = busServiceDao.getBusDetails(busId);
		
		double startingPriceOf24wheels=120000;
		double startingPriceOf36wheels=160000;
		boolean isNumberOfWheels24 = false;

		if(Integer.valueOf(busDetails.getMakeYear())<=1972)

		{

			startingPriceOf24wheels+= startingPriceOf24wheels*0.34;

			startingPriceOf36wheels+= startingPriceOf36wheels*0.34;

		}

		if(busDetails.getAirConditioner().equals("true"))

		{

			startingPriceOf24wheels+= startingPriceOf24wheels*0.03;

			startingPriceOf36wheels+= startingPriceOf36wheels*0.03;

		}

		

		if(busDetails.getStatus().equalsIgnoreCase("readyToUse") && busDetails.getOdometerReading() > 100000)

		{

			if(busDetails.getCapacity()== "24" || busDetails.getCapacity()=="36") 

			{

				long numberOfMiles=busDetails.getOdometerReading();

				numberOfMiles=	numberOfMiles-100000;

				double actualPriceOfBusAfterMilesCost=numberOfMiles*0.10;
				
				if(busDetails.getCapacity()== "24") {
					actualPriceOfBusAfterMilesCost=startingPriceOf24wheels-actualPriceOfBusAfterMilesCost;	
				}
				else {
					actualPriceOfBusAfterMilesCost=startingPriceOf36wheels-actualPriceOfBusAfterMilesCost;	
				}


				return actualPriceOfBusAfterMilesCost;

			}
						
			
			}
		else if(busDetails.getStatus().equalsIgnoreCase("readyToUse")){
			if(busDetails.getCapacity()== "24") {
				return startingPriceOf24wheels;
			}
			return startingPriceOf36wheels;
		}
		return 0.0;
		}

}
	
