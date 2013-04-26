package edu.thangiah.action.servicetechnician;

import edu.thangiah.entity.ServiceTechnician;
import edu.thangiah.strutsutility.exception.StrutsElementException;

/**
 * This class extends the management controller. Its primary function
 * is to handle everything related to updating and Service Technician entity.
 * 
 * 
 * 
 * @author Alex McCracken, Kelly Smith
 *
 */


public class UpdateAction extends ManagementController{

	private static final long serialVersionUID = 4634172821230672694L;

	@Override
	public void prepare() throws Exception {
		this.mode =  Modes.EDIT;
		super.prepare();
	}
	
	
	@Override
    public String execute(){
		// Errors could be thrown by the ManagementController prepare methods.
    	if( this.hasControllerErrors() ){
    		return ERROR;
    	}
    	
    	ServiceTechnician fromDb = this.retrieveEntityById(serviceTechnicianDao, id);
    	if( fromDb == null){
    		this.addActionError("This contractor does not exist.  Please try again.");
    		return INPUT;
    	}
    	
    	// Get the selected values from the form.
    	try{
			initializeSelectedElements();
		}
		catch( StrutsElementException e ){
			addActionError("An unknown error occured.  Plase try reloading the page.");
			return ERROR;
		}
		
		if( this.hasActionErrors() || this.hasFieldErrors() )
			return INPUT;
    	
		
		fromDb.setContact(contactSelect.getSelectedEntity());
		
		try{
			serviceTechnicianDao.update(fromDb);
		}
		catch( Exception e ){
			return ERROR;
		}
    	
    	
    	return SUCCESS;
	}
}