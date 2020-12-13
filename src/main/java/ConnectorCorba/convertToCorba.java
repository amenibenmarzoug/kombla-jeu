/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectorCorba;

import java.util.List;
import net.vpc.gaming.atom.model.DefaultPlayer;
import net.vpc.gaming.atom.model.DefaultSprite;
import net.vpc.gaming.atom.model.Player;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;
import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

/**
 *
 * @author ameni
 */
public class convertToCorba {

    public static Sprite toCorbaSPrite(DefaultSprite s) {
        Sprite sp = null;
        sp.id = s.getId();
        sp.name = s.getName();
        sp.direction = s.getDirection();
        sp.kind = s.getKind();
        sp.playerid = s.getPlayerId();
        return sp;

    }
    public static Player toCorbaPlayer(DefaultPlayer p){
        Player player = null;
        player.setId(p.getId());
        player.setName(p.getName());
        return player;
        
    }
    public static Player[] toCorbaPlayersList(List<DefaultPlayer> dp){
        
        return (Player[]) dp.toArray();
    }
    public static Sprite[] toCorbaSpritesList(List<DefaultSprite> ds){
        return (Sprite[]) ds.toArray();
    }
    public static CorbaStartGameInfo toCorbaStartGameInfo(StartGameInfo game)
    {
        return new CorbaStartGameInfo(game.getPlayerId(),game.getMaze());
    }
    public static CorbaDynamicGameModel toCorbaDynamicGameModel(DynamicGameModel gameModel)
    {
        CorbaDynamicGameModel gm =null;
        gm.frame=gameModel.getFrame();
        Player[] player=null;
      
        for (int i = 0; i < player.length; i++) {
            player=gameModel.getPlayers().toArray(new Player[i]);
            
        }
    
        gm.player= (Player[]) gameModel.getPlayers().toArray();
        gm.sprite=(Sprite[]) gameModel.getSprites().toArray();
        
        return new CorbaDynamicGameModel(gm.frame,gm.player,gm.sprite);
    }
}
