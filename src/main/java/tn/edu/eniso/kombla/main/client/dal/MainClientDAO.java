package tn.edu.eniso.kombla.main.client.dal;

import tn.edu.eniso.kombla.main.shared.model.StartGameInfo;

import java.util.Map;

/**
 * Created by vpc on 10/7/16.
 */
public interface MainClientDAO {
    /**
     * !!non blocking!! start method.
     * called once by Engine when game starts
     * @param listener dal listener
     * @param properties config properties
     */
    public void start(MainClientDAOListener listener, Map<String, Object> properties);

    /**
     * !!blocking!! method to connect to server and retreive game info
     * @return StartGameInfo
     */
    public StartGameInfo connect();

    public void sendMoveLeft();

    public void sendMoveRight();

    public void sendMoveUp();

    public void sendMoveDown();

    public void sendFire();
}
