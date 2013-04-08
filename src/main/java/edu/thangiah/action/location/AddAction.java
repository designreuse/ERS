package edu.thangiah.action.location;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import edu.thangiah.dao.ContractorDao;
import edu.thangiah.dao.VehicleDao;
import edu.thangiah.entity.Contractor;
import edu.thangiah.entity.Location;
import edu.thangiah.entity.Vehicle;

public class AddAction extends LocationAction implements Preparable{

	private static final long serialVersionUID = -1708978099566079365L;
	private Location location;
	private Contractor contractor;
	private Vehicle vehicle;
	
	@Autowired
	protected VehicleDao vehicleDao;
	
	@Override
    public String execute() throws Exception
    {
		if (locationDao == null || location == null) 
		{
            this.addActionError(DB_ERROR_MESSAGE);
        }
		
		contractorDao.add(contractor);
		vehicleDao.add(vehicle);
		//location.setVehicles(vehicle);
		location.setContractor(contractor);
		
		LOGGER.debug("Adding new location: " + location.toString());
		locationDao.add(location);
		
    	return SUCCESS;
    }
    
    // called automatically
    public void validate(){
    	if( location != null && contractor != null )
    	{
    		requiredString(location.getName(), "location.name");
    		requiredString(location.getLatitude(), "location.latitude");    		
    		requiredString(location.getLongitude(), "location.longitude");
    	}		
    	else{
    		addActionError("Unknown error.  Please try again.");
    	}

    }

	/**
	 * @return the contact
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	/**
	 * @return the contact
	 */
	public Contractor getContractor() {
		return contractor;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	public ContractorDao getContractorDao() {
		return contractorDao;
	}

	public void setContractorDao(ContractorDao contractorDao) {
		this.contractorDao = contractorDao;
	}

	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
}