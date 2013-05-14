package edu.thangiah.action.maintenanceorder;


import com.opensymphony.xwork2.Preparable;

import edu.thangiah.entity.MaintenanceOrder;
import edu.thangiah.strutsutility.exception.StrutsElementException;

/**
 *This class extends the management controller and implements preparable. It's primary function
 *is to to handle everything related to adding and Maintenance Orders entity 
 * 
 * @author Alex McCracken, Kelly Smith
 */


public class AddAction extends ManagementController implements Preparable{
	
	private static final long serialVersionUID = -5800683108151609064L;
	
	@Override
    public String execute(){
		try{
			initializeSelectedElements();
		}
		catch( StrutsElementException e ){
			addActionError("An unknown error occured.  Plase try reloading the page.");
			return ERROR;
		}
		
		if( this.hasActionErrors() || this.hasFieldErrors() )
			return INPUT;

		
		MaintenanceOrder newMaintenanceOrder = new MaintenanceOrder();
		newMaintenanceOrder.setVehicle(vehicleSelect.getSelectedEntity());
		newMaintenanceOrder.setDriver(driverSelect.getSelectedEntity());
		newMaintenanceOrder.setServiceTechnician(serviceTechnicianSelect.getSelectedEntity());
		
		try{
				maintenanceOrderDao.add(newMaintenanceOrder);				
		}
		catch(Exception e){
			return ERROR;
		}
    	return SUCCESS;
    }
    
    // called automatically
    public void validate(){
    	if( getEntity() != null  )
    	{
    		// TODO - test the vehicle inside so that you can have specific error messages about it.
    		requiredString(getEntity().getRequester().toString(), "maintenanceOrder.requester");
    		requiredString(getEntity().getServiceTechnician().toString(), "maintenanceOrder.serviceTechnician");
    		requiredString(getEntity().getScheduledDate().toString(), "maintenanceOrder.scheduleDate");
    		requiredString(getEntity().getDetails(), "maintenanceOrder.details");
    		requiredString(getEntity().getServiceTypeKey(), "maintenanceOrder.serviceTypeKey");
    		requiredString(getEntity().getCost(), "maintenanceOrder.cost");    	
    		requiredString(getEntity().getStatusKey(), "maintenanceOrder.statusKey");
    		requiredString(getEntity().getVehicle().toString(), "maintenanceOrder.vehicle");
    		requiredString(getEntity().getMaintenanceType(), "maintenanceOrder.maintenanceType");
    	
    	}		
    	else{
    		addActionError("Unknown error.  Please try again.");
    	}

    }


	public MaintenanceOrder getMaintenanceOrder()
	{
		return getEntity();
	}
    
    public void setMaintenanceOrder(MaintenanceOrder maintenanceOrder)
    {
    	this.setEntity(maintenanceOrder);
    }

}
