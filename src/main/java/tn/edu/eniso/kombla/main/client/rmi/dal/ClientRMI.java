/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.client.rmi.dal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;

/**
 *
 * @author ameni
 */
public interface ClientRMI extends Remote {
    void modelChanged(DynamicGameModel model) throws RemoteException;

}
