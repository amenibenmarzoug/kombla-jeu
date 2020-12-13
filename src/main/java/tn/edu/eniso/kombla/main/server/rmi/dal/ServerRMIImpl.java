/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.server.rmi.dal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import tn.edu.eniso.kombla.main.client.rmi.dal.ClientRMI;
import tn.edu.eniso.kombla.main.server.dal.MainServerDAOListener;
import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

/**
 *
 * @author ameni
 */
public class ServerRMIImpl extends UnicastRemoteObject implements ServerRMI {

    List<ClientRMI> listClients;
    MainServerDAOListener listener;

    public StartGameInfo connect(String playerName, ClientRMI client) throws RemoteException {

        listClients.add(client);
        return listener.onReceivePlayerJoined(playerName);
    }

    public void moveLeft(int playerId) throws RemoteException {
        listener.onReceiveMoveLeft(playerId);
    }

    public void moveUp(int playerId) throws RemoteException {
        listener.onReceiveMoveUp(playerId);
    }

    public void moveRight(int playerId) throws RemoteException {
        listener.onReceiveMoveRight(playerId);
    }

    public void moveDown(int playerId) throws RemoteException {
        listener.onReceiveMoveDown(playerId);
    }

    List<ClientRMI> getClients() {
        return listClients;
    }

    ServerRMIImpl(MainServerDAOListener serverListener) throws RemoteException {
        this.listener = serverListener;
    }

    @Override
    public void sendFire(int playerId) throws RemoteException {
        listener.onReceiveReleaseBomb(playerId);
    }

}
