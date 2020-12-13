/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.server.rmi.dal;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.edu.eniso.kombla.main.client.dal.MainClientDAOListener;
import tn.edu.eniso.kombla.main.client.rmi.dal.ClientRMI;
import tn.edu.eniso.kombla.main.client.rmi.dal.ClientRMIImpl;
import tn.edu.eniso.kombla.main.server.dal.MainServerDAO;
import tn.edu.eniso.kombla.main.server.dal.MainServerDAOListener;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;

/**
 *
 * @author ameni
 */
public class MainServerRMIDAO implements MainServerDAO {
    List<ClientRMI> listClient;
    private MainServerDAOListener listener;
    ServerRMIImpl serverRMIImpl;
    int serverPort;
    @Override
    public void start(MainServerDAOListener listener, Map<String, Object> properties) {
        try {
            this.listener=listener;
            serverPort=(int) properties.get("serverPort");
            Registry r=LocateRegistry.getRegistry(serverPort);
            serverRMIImpl = new ServerRMIImpl(listener);
            r.bind("Le serveur RMI", serverRMIImpl);
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(MainServerRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        try {
        List<ClientRMI> clients=serverRMIImpl.getClients();
        //int nbCLients = serverRMIImpl.getClients().size();
        for (ClientRMI c : clients) {
            
                c.modelChanged(dynamicGameModel);
           
        }
         } catch (RemoteException ex) {
                Logger.getLogger(MainServerRMIDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
