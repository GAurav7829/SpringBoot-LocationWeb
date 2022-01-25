package com.grv.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grv.location.entities.Location;
import com.grv.location.repos.LocationRepository;
import com.grv.location.service.LocationService;
import com.grv.location.util.EmailUtil;
import com.grv.location.util.ReportUtil;

@Controller
public class LocationController {
	@Autowired
	private LocationService service;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private LocationRepository repository;
	@Autowired
	private ReportUtil reportUtil;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@PostMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, Model model) {
		Location locationSaved = service.saveLocation(location);
		String msg = "Location Saved with id: " + locationSaved.getId();
		model.addAttribute("msg", msg);
		
		//emailUtil.sendEmail("cybercop.7829@gmail.com", "Location Saved", "Location Saved Succesfully... <br/>Location Id:"+locationSaved.getId()+", Location Code:"+locationSaved.getCode()+", Location Name:"+locationSaved.getName());
		
		return "createLocation";
	}

	@GetMapping("/displayLocations")
	public String displayLocations(Model model) {
		List<Location> allLocation = findAllLocations();
		model.addAttribute("locations", allLocation);
		return "displayLocations";
	}

	@GetMapping("/deleteLocation")
	public String deleteLocation(Model model, @RequestParam int id) {
		// service.deleteLocation(service.getLocationById(id));
		// OR
		Location location = new Location();
		location.setId(id);
		service.deleteLocation(location);
		List<Location> allLocation = findAllLocations();
		model.addAttribute("locations", allLocation);
		return "displayLocations";
	}

	@GetMapping("/showUpdate")
	public String showUpdate(Model model, @RequestParam int id) {
		Location location = service.getLocationById(id);
		model.addAttribute("location", location);
		return "updateLocation";
	}

	@PostMapping("/updateLoc")
	public String updateLocation(Model model, @ModelAttribute("location")Location location) {
		service.updateLocation(location);
		List<Location> allLocation = findAllLocations();
		model.addAttribute("locations", allLocation);
		return "displayLocations";
	}
	
	private List<Location> findAllLocations() {
		List<Location> allLocation = service.getAllLocation();
		return allLocation;
	}
	
	@GetMapping("/generateReport")
	public String generateReport() {
		List<Object[]> data = repository.findTypeAndTypeCount();
		String realPath = servletContext.getRealPath("/");
		reportUtil.generatePieChart(realPath, data);
		reportUtil.generateBarChart(realPath, data);
		return "report";
	}
}
