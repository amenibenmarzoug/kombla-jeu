/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.client.rmi.dal;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.eniso.kombla.main.client.dal.MainClientDAO;
import tn.edu.eniso.kombla.main.client.dal.MainClientDAOListener;
import tn.edu.eniso.kombla.main.server.dal.MainServerDAO;
import tn.edu.eniso.kombla.main.server.dal.MainServerDAOListener;
import tn.edu.eniso.kombla.main.server.rmi.dal.ServerRMI;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;
import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

/**
 *
 * @author ameni
 */
public class MainClientRMIDAO implements MainClientDAO {

    private MainClientDAOListener listener;
    ClientRMIImpl clientRMIImpl;
    ServerRMI serverRMI;
    StartGameInfo s;
    int playerId;
    int[][] maze;
    int clientPort;
    String serverAddress;
    int serverPort;
    //String playerName;

    @Override
    public void start(MainClientDAOListener listener, Map<String, Object> properties) {
        try {
            this.listener=listener;
            serverAddress=(String) properties.get("serverAddress");
            serverPort=(int) properties.get("serverPort");
            Registry r=LocateRegistry.getRegistry(serverAddress,serverPort);
            clientRMIImpl= new ClientRMIImpl(listener);
            r.bind("Le client RMI", clientRMIImpl);
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public StartGameInfo connect() {
        try {
            clientRMIImpl = new ClientRMIImpl(listener);
            Registry r = LocateRegistry.getRegistry("localhost",1234);
            serverRMI = (ServerRMI) r.lookup("server");
            serverRMI.connect("nom du joueur", clientRMIImpl);
            playerId = s.getPlayerId();
            maze=s.getMaze();

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new StartGameInfo(playerId,maze);

    }

    @Override
    public void sendMoveLeft() {
        try {
            serverRMI.moveLeft(playerId);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveRight() {
        try {
            serverRMI.moveRight(playerId);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveUp() {
        try {
            serverRMI.moveUp(playerId);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveDown() {
        try {
            serverRMI.moveDown(playerId);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendFire() {
        try {
            serverRMI.sendFire(playerId);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClientRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
