/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.server.dal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.vpc.gaming.atom.model.ModelPoint;
import net.vpc.gaming.atom.model.Player;
import net.vpc.gaming.atom.model.Sprite;
import tn.edu.eniso.kombla.main.shared.dal.ProtocolConstants;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;
import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

/**
 *
 * @author ameni
 */
public class SocketMainServerDAO implements MainServerDAO{
    
    private Map<Integer, ClientSession> playerToSocketMap = new HashMap<>() ;
    MainServerDAOListener listener;
    int serverPort;
    DataOutputStream outClient;
    DataInputStream inClient;
        ModelPoint model;

    @Override
    public void start(MainServerDAOListener listener, Map<String, Object> properties) {
        
        try {
            this.listener=listener;
            serverPort=(int) properties.get("serverPort");
            ServerSocket ss = new ServerSocket(serverPort);
            new Thread(() -> { 
            while(true){
                try {
                    Socket client =   ss.accept();
                    new Thread(() -> {
                        processClient(new ClientSession(-1,client,null,null));
                    }).start();
                } catch (IOException ex) {
                    Logger.getLogger(SocketMainServerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        }).start();
        } catch (IOException ex) {
            Logger.getLogger(SocketMainServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
      
    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        for (Map.Entry<Integer, ClientSession> entry : playerToSocketMap.entrySet()) { 
        //envoyer le nouveau modele à tous les clients
        Integer playerId = entry.getKey();
        //playerId: clé du playerToSocketMap
        DataOutputStream out = entry.getValue().out;
        for (Sprite sprite : dynamicGameModel.getSprites()){
        
        }
        for (Player p: dynamicGameModel.getPlayers()) {
                    writePlayer((Player) p,out);
                }
    }
    }

    private void processClient(ClientSession clientSession) {
        try {
            clientSession.out = new DataOutputStream(clientSession.socket.getOutputStream());
            clientSession.in = new DataInputStream(clientSession.socket.getInputStream());
            int commande = clientSession.in.readInt();
            if(commande == ProtocolConstants.CONNECT){
                String playerName = clientSession.in.readUTF();
                StartGameInfo startGameInfo = listener.onReceivePlayerJoined(playerName);
                //writeStartGameInfo(startGameInfo,clientSession);
                playerToSocketMap.put(clientSession.playerId, clientSession);
                
            }else{
                clientSession.out.writeInt(ProtocolConstants.KO);
            }
            
            while(true){
                switch (clientSession.in.readInt()){
                    case ProtocolConstants.LEFT :
                        listener.onReceiveMoveLeft(clientSession.playerId);
                        break;
                    case ProtocolConstants.RIGHT :
                        listener.onReceiveMoveRight(clientSession.playerId);
                        break;
                    case ProtocolConstants.DOWN :
                        listener.onReceiveMoveDown(clientSession.playerId);
                        break;
                    case ProtocolConstants.UP :
                        listener.onReceiveMoveUp(clientSession.playerId);
                        break;
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketMainServerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public void writeStartGameInfo(StartGameInfo gameInfo, ClientSession cs) throws IOException{
        int playerId = gameInfo.getPlayerId();
        int[][] maze  = gameInfo.getMaze() ;
        
        cs.out.write(ProtocolConstants.OK);
        cs.out.writeInt(playerId);
        cs.out.writeInt(maze.length);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {          
                cs.out.write(maze[i][j]);                
            }           
        }
    }
    
    public void writePlayer(Player player, DataOutputStream out) {
        try {
            
            
            out.writeInt(player.getId());
            out.writeUTF(player.getName());
            out.writeInt(player.getProperties().size());
            
            for (Map.Entry<String, Object> entry : player.getProperties().entrySet())
            {
                out.writeUTF(entry.getKey());
                out.writeUTF((String) entry.getValue());
            }
                
                
                } catch (IOException ex) {
            Logger.getLogger(SocketMainServerDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

    }
    
    public void writeSprite(Sprite sprite,DataOutputStream out) throws IOException{
        out.writeInt(sprite.getId());
        out.writeUTF( sprite.getKind());
        out.writeUTF(sprite.getName());
        out.writeInt(sprite.getLocation().getIntX());
        out.writeInt(sprite.getLocation().getIntY());
        out.writeDouble(sprite.getDirection());
        for (Map.Entry<String, Object> entry : sprite.getProperties().entrySet())
        {
             out.writeUTF(entry.getKey());
             out.writeUTF((String) entry.getValue());
        }

    }
 
        
    private class ClientSession{
        int playerId;
        Socket socket;
        DataOutputStream out;
        DataInputStream in;
        
        public ClientSession(int playerId, Socket socket, DataOutputStream out, DataInputStream in) {
            this.playerId = playerId;
            this.socket=socket;
            this.out = out;
            this.in = in;
        }
        


    }
    
}
