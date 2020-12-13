/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.server.rmi.dal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import tn.edu.eniso.kombla.main.client.rmi.dal.ClientRMI;
import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

/**
 *
 * @author ameni
 */
public interface ServerRMI extends Remote {
    //pourquoi passer le client par parametre??
    StartGameInfo connect(String playerName,ClientRMI client) throws RemoteException;
    //pourquoi passer playerId par parametre??
    void moveLeft(int playerId) throws RemoteException;
    void moveUp(int playerId) throws RemoteException;
    void moveRight(int playerId) throws RemoteException;
    void moveDown(int playerId) throws RemoteException;
    void sendFire(int playerId) throws RemoteException;

}
