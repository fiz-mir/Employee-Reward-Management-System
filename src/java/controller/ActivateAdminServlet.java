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
public class ActivateAdminServlet extends AdminStatusServlet {
    @Override
    protected int getNewAdminStatus(int adminStatus){
        return (adminStatus == 51) ? 50 : 50;
    }
}
