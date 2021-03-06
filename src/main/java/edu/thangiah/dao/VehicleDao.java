package edu.thangiah.dao;

import java.util.List;

import edu.thangiah.entity.Driver;
import edu.thangiah.entity.Contractor;
import edu.thangiah.entity.Location;
import edu.thangiah.entity.MaintenanceOrder;
import edu.thangiah.entity.Vehicle;
import edu.thangiah.entity.VehicleType;

/**
 * This codes primary function is to pull the database references for a vehicle to be manipulated by the entity classes
 * 
 * @author Alex McCracken, Kelly Smith
 *
 */


public class VehicleDao extends AbstractDao<Vehicle> {
	
	@SuppressWarnings("unchecked")
	@Override
    public List<Vehicle> findAll() {
		List<Vehicle> list = getHibernateTemplate().find("from Vehicle");
		return list;
    }
	
	@SuppressWarnings("unchecked")
	@Override
    public List<Vehicle> findById(Long id) {
        return getHibernateTemplate().find("from Vehicle where id=?", id);
    }
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> findByVehicleType(VehicleType type){
		return getHibernateTemplate().find("from Vehicle where vehicle_type_id=?", type.getId());
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> findByLocation(Location loc){
		return getHibernateTemplate().find("from Vehicle where location_id=?", loc.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> findByMaintenanceOrder(MaintenanceOrder maintenanceOrder){
		return getHibernateTemplate().find("from Vehicle where maintenanceOrder_id=?", maintenanceOrder.getId());
	}	
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> findByContractor(Contractor contractor){
		return getHibernateTemplate().find("from Vehicle where contractor_id=?", contractor.getId());
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> findByDriver(Driver driver){
		return getHibernateTemplate().find("from Vehicle where contractor_id=?", driver.getId());
	}

}
