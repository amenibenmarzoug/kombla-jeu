package tn.edu.eniso.kombla.main.server.dal;

import net.vpc.gaming.atom.model.Player;
import net.vpc.gaming.atom.model.Sprite;
import tn.edu.eniso.kombla.main.shared.model.DynamicGameModel;

import java.util.List;
import java.util.Map;

/**
 * Created by vpc on 10/7/16.
 */
public interface MainServerDAO {
    /**
     * stats in non blocking mode the DAO
     * @param listener dao listener to catch dal events
     * @param properties extra properties such as "serverPort", "serverAddress"
     */
    void start(MainServerDAOListener listener, Map<String, Object> properties);

    void sendModelChanged(DynamicGameModel dynamicGameModel);
}
