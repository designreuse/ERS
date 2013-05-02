package edu.thangiah.action.route;

import edu.thangiah.entity.Route;
import edu.thangiah.entity.Shipment;
import edu.thangiah.strutsutility.exception.StrutsElementException;

/**
 * This class extends the management controller. Its primary function
 * is to handle everything related to updating and Route entity.
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
    	
    	Route fromDb = this.retrieveEntityById(routeDao, id);
    	if( fromDb == null){
    		this.addActionError("This route does not exist.  Please try again.");
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
    	
		
		String result = this.parseShipmentList();
		if( !result.equals(SUCCESS) )
			return result;
		
		fromDb.setVehicle(vehicleSelect.getSelectedEntity());
		
		try{
			if( this.getParsedShipments() != null ){
				
				for( Shipment ship : fromDb.getOrderedShipments() ){
					if( !getParsedShipments().contains(ship) ){
						fromDb.removeShipment(ship, shipmentDao);
					}
				}
				
				for( Shipment ship : getParsedShipments() ){
					if( !fromDb.getOrderedShipments().contains(ship) )
						fromDb.addShipment(ship, shipmentDao);
				}
			}
			routeDao.update(fromDb);
		}
		catch( Exception e ){
			return ERROR;
		}
    	
    	
    	return SUCCESS;
	}
}