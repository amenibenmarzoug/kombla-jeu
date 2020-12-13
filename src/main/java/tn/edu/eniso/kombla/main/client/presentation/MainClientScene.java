/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.client.presentation;

//import net.vpc.gaming.atom.debug.layers.DebugLayer;

import net.vpc.gaming.atom.annotations.AtomScene;
import net.vpc.gaming.atom.annotations.Inject;
import net.vpc.gaming.atom.annotations.OnInstall;
import net.vpc.gaming.atom.engine.SceneEngine;
import net.vpc.gaming.atom.model.Sprite;
import net.vpc.gaming.atom.presentation.DefaultScene;
import net.vpc.gaming.atom.presentation.ImageMatrixProducer;
import net.vpc.gaming.atom.presentation.Scene;
import tn.edu.eniso.kombla.main.client.engine.MainClientEngine;
import tn.edu.eniso.kombla.main.local.presentation.MainLocalScene;
import tn.edu.eniso.kombla.main.shared.prensentation.BomberScene;
import tn.edu.eniso.kombla.main.shared.prensentation.ScoreLayer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
@AtomScene(
        id = "mainClient",
        engine = "mainClient",
        title = "Kombla - Client",
        tileWidth = 80,
        isometric = false
        , cameraWidth = 0.5f
)
public class MainClientScene extends BomberScene {

    @Inject
    SceneEngine engin;

    public MainClientScene() {
    }

    @OnInstall
    private void onInstall() {
        this.addLayer(new ScoreLayer());
        engin.addPropertyChangeListener("modelChanged", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateControl();
            }
        });
    }

    protected void updateControl() {
        MainClientEngine sceneEngine = getSceneEngine();
        Sprite sprite = sceneEngine.findSpriteByPlayer(Sprite.class,sceneEngine.getCurrentPlayerId());
        if (sprite != null) {
            lockCamera(sprite);
            resetControlPlayers();
            addControlPlayer(sceneEngine.getCurrentPlayerId());
        }
    }

}
