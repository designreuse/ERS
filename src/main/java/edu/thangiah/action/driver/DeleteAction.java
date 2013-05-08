package edu.thangiah.action.driver;

import edu.thangiah.entity.Driver;

/**
 * This class extends the management controller. It's primary function is to handle everything
 * related to deleting a Driver entity
 * 
 * @author Alex McCracken, Kelly Smith
 *
 */

public class DeleteAction extends ManagementController
{

	private static final long serialVersionUID = 8689202186518590230L;

	@Override
	public void prepare() throws Exception {
		this.mode = Modes.DELETE;
		super.prepare();
	}
	
	
	@Override
    public String execute(){
		// Errors could be thrown by the ManagementController prepare methods.
    	if( this.hasControllerErrors() ){
    		return ERROR;
    	}
    	
    	if( id <= 0 ){
    		this.addActionError("Must specify which driver you would like to delete.");
    		return INPUT;
    	}
    	
    	Driver fromDb = this.retrieveEntityById(driverDao, id);
    	if( fromDb == null ){
    		this.addActionError("This driver does not exist.  Please try again.");
    		return INPUT;
    	}
    	
    	try{
    		if(fromDb != null && maintenanceOrderDao.findByRequester(fromDb) == null) {
    			driverDao.delete(fromDb);    			
    		}
    		else {
    			this.addActionError("Dependencies exist.  It cannot be deleted.");
    			return INPUT;
    		}
    	}
    	catch( Exception e ){
    		this.addActionError("And error has occurred. Please try again if this persists contact your system admin");
    		return INPUT;
    	}
    	
    	return SUCCESS;
    }
}
