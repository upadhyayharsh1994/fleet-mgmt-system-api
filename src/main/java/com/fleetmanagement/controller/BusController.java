package com.fleetmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fleetmanagement.constants.FleetManagementConstant;
import com.fleetmanagement.domain.input.BusDetailsInput;
import com.fleetmanagement.domain.output.BusDetails;
import com.fleetmanagement.exception.ExceptionEnum;
import com.fleetmanagement.exception.FleetException;
import com.fleetmanagement.service.BusService;
import com.fleetmanagement.util.Validation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value= FleetManagementConstant.FLEET_URL)
public class BusController {

	private static final Logger logger = LoggerFactory.getLogger(BusController.class);
	
	@Autowired
	private Validation validationUtil;
	
	@Autowired
	private BusService busService;
			
	@GetMapping(value= FleetManagementConstant.GET_DETAILS+"/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BusDetails.class)})
	@ApiOperation(value = "API to get Bus Details")
	@CrossOrigin
	public BusDetails getBusDetails(@PathVariable("id") String busId) {
		logger.info("Retreiving Details for BusId {}", busId);
		if(ObjectUtils.isEmpty(busId)) {
			System.out.println("Errrooooo");
			throw new FleetException(ExceptionEnum.INPUT_VALIDATION_EXCEPTION, "Input Failed");
		}
		return busService.getBusDetails(busId);
	}
	
	@GetMapping(value= FleetManagementConstant.GET_RESALE_VALUE+"/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BusDetails.class)})
	@ApiOperation(value = "API to get Bus Resale Value")
	@CrossOrigin
	public Double getBusResalePrice(@PathVariable("id") String busId) {
		logger.info("Retreiving Details for BusId {}", busId);
		if(ObjectUtils.isEmpty(busId)) {
			System.out.println("Errrooooo");
			throw new FleetException(ExceptionEnum.INPUT_VALIDATION_EXCEPTION, "Input Failed");
		}
		return busService.getResaleValue(busId);
	}
	
	@GetMapping(value= FleetManagementConstant.GET_ALL_DETAILS)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = BusDetails.class)})
	@ApiOperation(value = "API to get All Bus Details")
	@CrossOrigin
	public List<BusDetails> getAllBusDetails() {
		logger.info("Retreiving all Bus details");
		return busService.getAllBusDetails();
	}
	
	@PostMapping(value= FleetManagementConstant.REVISE_BUS_DETAIL, consumes = {"multipart/form-data","application/octet-stream"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
	@ApiOperation(value = "API to edit bus details based on Id")
	@ResponseStatus
	@CrossOrigin
	public ResponseEntity<?> reviseBusDetails(@RequestPart("busDetails") BusDetailsInput busDetails,
			@RequestPart(value="busImage", required=false) MultipartFile busImage) {
		String busId = Integer.toString(busDetails.getBusId());
		logger.info("Revise Bus details for id {}",busId);
		int rowsAffected=busService.reviseBusDetails(busId,busDetails,busImage);
		if(rowsAffected>0)
		    return new ResponseEntity<>("Status: Update Successfull", HttpStatus.OK);
		else if(rowsAffected==-1)
			return new ResponseEntity<>("Status: Please pass valid Garage Id", HttpStatus.INTERNAL_SERVER_ERROR);	
		else
		    return new ResponseEntity<>("Status: Please try again", HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@PostMapping(value= FleetManagementConstant.REMOVE_BUS_DEATILS+"/{id}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
	@ApiOperation(value = "API to edit bus details based on Id")
	@ResponseStatus
	@CrossOrigin
	public ResponseEntity<?> removeBusDetails(@PathVariable("id") String busId) {
		logger.info("Revise Bus details for id {}",busId);
		if(validationUtil.isEmpty(busId)) {
			throw new FleetException(ExceptionEnum.INPUT_VALIDATION_EXCEPTION, "Input Failed");
		}
		int rowsAffected=busService.removeBusDetails(busId);
		if(rowsAffected>0)
		    return new ResponseEntity<>("Status: Update Successfull", HttpStatus.OK);
		else 
		    return new ResponseEntity<>("Status: Please try again", HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	@PostMapping(value= FleetManagementConstant.INSERT_BUS_DETAILS,consumes = {"multipart/form-data","application/octet-stream"})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class)})
	@ApiOperation(value = "API to edit bus details based on Id")
	@ResponseStatus
	@CrossOrigin
	public ResponseEntity<?> insertBusDetails(@RequestPart("busDetails") BusDetailsInput busDetails,
			@RequestPart("busImage") MultipartFile busImage) {	
		logger.info("Inserting Bus details {}",busDetails);
		if(busDetails == null) {
			System.out.println("insert____");
			throw new FleetException(ExceptionEnum.INPUT_VALIDATION_EXCEPTION, "Input Failed");
		}
		Boolean rowsAffected = busService.insertBusDetails(busDetails,busImage);
		if(rowsAffected)
		    return new ResponseEntity<>("Status: Update Successfull", HttpStatus.OK);
		else 
		    return new ResponseEntity<>("Status: Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
