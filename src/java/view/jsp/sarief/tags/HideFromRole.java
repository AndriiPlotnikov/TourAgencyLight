/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jsp.sarief.tags;

import entities.User;
import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author andre
 */
public class HideFromRole extends SimpleTagSupport{
    private String roles;

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        String[] rolesArray = roles.split(",");
        StringWriter sw = new StringWriter();
        
        PageContext pc = (PageContext)getJspContext();
        HttpServletRequest request = (HttpServletRequest)pc.getRequest();
        HttpSession session = request.getSession(false);
        
        User user = (User)session.getAttribute("user");
        
        String roleOfUser = (user == null) ? "guest" : user.getRole();
        
        for(String role : rolesArray){
            if(role.equals(roleOfUser)){
                getJspBody().invoke(sw);
                getJspContext().getOut().print(sw);
                return;
            }
        }
    }
    
    
}
