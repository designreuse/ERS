package edu.thangiah.user.action;

import com.opensymphony.xwork2.Preparable;


public class LogoutAction extends UserAction implements Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5723248017386879427L;
	
    @Override
    public String execute() throws Exception{
    	
    	this.logoutUser();
       
    	return SUCCESS;

    }
    
}