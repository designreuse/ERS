package edu.thangiah.user.action;

import java.util.List;

import org.apache.log4j.Logger;

import edu.thangiah.action.BaseManagementController;
import edu.thangiah.permission.Role;
import edu.thangiah.user.entity.User;

public class ManagementController extends BaseManagementController{
	protected static final Logger LOGGER = Logger.getLogger(UserAction.class.getName());
	private static final long serialVersionUID = -1725293630396874653L;
	
	private static final String ROLE_NULL_MESSAGE = "Unable to get the list of user roles from the database. Please try again.";
	private static final String USERS_NULL_MESSAGE = "Unable to connect to the users database.  Please try again.";
	
	private List<User> users;
	
	protected User user;
	
	protected List<Role> roles;
	
	@Override
	public void prepare() throws Exception {
		super.prepare();
		
		if( initializeRoles() != SUCCESS ){
			addControllerError(ErrorCode.FATAL, ROLE_NULL_MESSAGE);
		}
		if( initializeUserList() != SUCCESS ){
			addControllerError(ErrorCode.FATAL, USERS_NULL_MESSAGE);
		}
	}
	
	@Override
	public String execute(){
		String result = initialize();
		if( !result.equals(SUCCESS) ){
			return result;
		}
		
        if( mode == Modes.EDIT ){
        	if( initializeEditUser() != SUCCESS ){
    			return ERROR;
    		}
        }
        else{
        	user = null;
        }
        
        return SUCCESS;
	}

	protected String initializeUserList() {
		if (userBo == null) {
			users = null;
            return ERROR;
        }
		
		users = userBo.findAll();
        LOGGER.debug("Users number = " + users.size());
        
        return SUCCESS;
	}

	protected String initializeEditUser() {
		List<User> fromDb = userBo.findById((int)id);
		if( fromDb == null || fromDb.size() == 0 ){
			entityNotFoundError();
			return SUCCESS; // Error will be displayed.
		}
		
		user = fromDb.get(0);
		
		return SUCCESS;
	}

	protected String initializeRoles() {
		if( roleDao == null ){
			LOGGER.debug("No instance of RoleDao was initialized.");
			roles = null;
			return ERROR;
		}
		roles = roleDao.findAll();
		if( roles == null || roles.size() == 0 ){
			this.addActionError(ROLE_NULL_MESSAGE);
			LOGGER.debug("No Roles were able to be retrieved from the database.");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}