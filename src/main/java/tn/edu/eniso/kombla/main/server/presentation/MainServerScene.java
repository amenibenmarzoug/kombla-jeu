/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.server.presentation;

//import net.vpc.gaming.atom.debug.layers.DebugLayer;

import net.vpc.gaming.atom.annotations.AtomScene;
import net.vpc.gaming.atom.annotations.Inject;
import net.vpc.gaming.atom.annotations.OnInstall;
import net.vpc.gaming.atom.engine.SceneEngine;
import net.vpc.gaming.atom.presentation.DefaultScene;
import net.vpc.gaming.atom.presentation.ImageMatrixProducer;
import net.vpc.gaming.atom.presentation.Scene;
import tn.edu.eniso.kombla.main.local.presentation.MainLocalScene;
import tn.edu.eniso.kombla.main.shared.prensentation.BomberScene;
import tn.edu.eniso.kombla.main.shared.prensentation.ScoreLayer;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
@AtomScene(
        id = "mainServer",
        engine = "mainServer",
        title = "Kombla - Server",
        tileWidth = 80,
        isometric = false
        ,cameraWidth = 0.5f
)
public class MainServerScene extends BomberScene {

    @OnInstall
    public void onInstall(){
        this.addLayer(new ScoreLayer());
    }
}
