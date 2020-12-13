/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.client.rmi.dal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import tn.edu.eniso.kombla.main.client.dal.MainClientDAOListener;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;

/**
 *
 * @author ameni
 */
public class ClientRMIImpl extends UnicastRemoteObject implements ClientRMI {
    MainClientDAOListener listener;
    ClientRMIImpl(MainClientDAOListener clientListener) throws RemoteException{
        this.listener=clientListener;
    }

    @Override
    public void modelChanged(DynamicGameModel model) throws RemoteException {
        listener.onModelChanged(model);
    }
}
