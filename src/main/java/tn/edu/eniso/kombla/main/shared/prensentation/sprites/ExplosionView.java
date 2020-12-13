/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.shared.prensentation.sprites;

import net.vpc.gaming.atom.annotations.AtomSpriteView;
import net.vpc.gaming.atom.model.Sprite;
import net.vpc.gaming.atom.presentation.ImageSpriteView;
import net.vpc.gaming.atom.presentation.Scene;
import net.vpc.gaming.atom.presentation.SpriteViewImageSelector;
import tn.edu.eniso.kombla.main.shared.engine.tasks.ExplosionSpriteTask;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
@AtomSpriteView(
        scene = "mainLocal,mainServer,mainClient",
        kind = "Explosion"
)
public class ExplosionView extends ImageSpriteView {

    public ExplosionView() {
        super("/explosion.png", 5, 5);
        //setResizeImages(true);
        setImageSelector(new SpriteViewImageSelector() {
            @Override
            public int getImageIndex(Sprite sprite, Scene scene, long frame, int imagesCount) {
                Object explosionTime = sprite.getProperty("explosionTime");
                if(explosionTime!=null){
                    if(explosionTime instanceof String){
                        explosionTime=Integer.parseInt((String)explosionTime);
                    }
                    int explosionTimeInt=(int)explosionTime;
                    return explosionTimeInt;
                }
                return 0;
            }
        });
    }
}
