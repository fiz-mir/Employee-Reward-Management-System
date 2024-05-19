/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author User
 */
public class DeactivateAdminServlet extends AdminStatusServlet {
    @Override
    protected int getNewAdminStatus(int adminStatus){
        return (adminStatus == 50) ? 51 : 51;
    }
}



