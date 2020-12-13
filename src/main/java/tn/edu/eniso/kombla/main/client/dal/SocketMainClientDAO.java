/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.client.dal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.vpc.gaming.atom.model.DefaultPlayer;
import net.vpc.gaming.atom.model.DefaultSprite;
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
public class SocketMainClientDAO implements MainClientDAO {
    MainClientDAOListener listener;
    Socket socket;
    String serverAddress;
    int serverPort;
    int playerId;
    int mazeSize;
    DataInputStream in;
    DataOutputStream out;
    int[][] maze = new int[mazeSize][mazeSize];
    int size;
    int nbPlayers;
    int nbSprites;
    ModelPoint model;
    @Override
    public void start(MainClientDAOListener listener, Map<String, Object> properties) {
        this.listener=listener;
        serverAddress=(String) properties.get("serverAddress");
        serverPort=(int) properties.get("serverPort");
        
    }
    
    public void onLoopRecieveModelChanged(){
        new Thread(() -> {
            DynamicGameModel dgm = new DynamicGameModel();
            try {
                
                //set frame
                dgm.setFrame(in.readLong());
                      
                //readPlayer()
                nbPlayers = in.readInt();
                List<Player> players = null;
                for (int i = 0; i < nbPlayers; i++) {               
                    players.add(readPlayer());
                }
                //setPLayers
                dgm.setPlayers(players);
                
                //readSprite()
                nbSprites = in.readInt();
                List<Sprite> sprites = null;
                for (int i = 0; i < nbSprites; i++) {
                    sprites.add(readSprite());
                }
                //setSprites()
                dgm.setSprites(sprites);
            } catch (IOException ex) {
                Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            listener.onModelChanged(dgm);
            
        }).start();
    }
   
    
    public StartGameInfo readStartGameInfo() throws IOException{
        playerId = in.readInt();
        mazeSize = in.readInt();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                maze[i][j]=in.readInt();

            } 
        }
    return new StartGameInfo(playerId,maze);

    }
    
    public DefaultPlayer readPlayer() throws IOException{
        DefaultPlayer player = new DefaultPlayer();
        player.setId(in.readInt());
        player.setName(in.readUTF());
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            String k = in.readUTF();
            String v = in.readUTF();
            player.setProperty(k, v);   
        }
        return player;  
    }
    
    public DefaultSprite readSprite() throws IOException{
        DefaultSprite sprite = new DefaultSprite();
        sprite.setId(in.readInt());
        sprite.setKind(in.readUTF());
        sprite.setName(in.readUTF());
        model = new ModelPoint(in.readDouble(),in.readDouble());
        sprite.setLocation(model);
        return sprite;
    }

    @Override
    public StartGameInfo connect() {
        try {
            socket = new Socket(serverAddress,serverPort);
            in =new  DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.write(ProtocolConstants.CONNECT);
            out.writeUTF("nom du joueur");
            
            if(in.readInt() == ProtocolConstants.OK){
               StartGameInfo s = readStartGameInfo();
               onLoopRecieveModelChanged();
            }else if(in.readInt() == ProtocolConstants.KO){
                System.out.println("Error in StartGameInfo");
            }
          
            
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void sendMoveLeft(){
        try {
            out.write(ProtocolConstants.LEFT);
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveRight() {
        try { 
            out.write(ProtocolConstants.RIGHT);
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveUp() {
        try {
            out.write(ProtocolConstants.UP);
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendMoveDown() {
        try {
            out.write(ProtocolConstants.DOWN);
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendFire() {
        try {
            out.write(ProtocolConstants.FIRE);
        } catch (IOException ex) {
            Logger.getLogger(SocketMainClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
}
