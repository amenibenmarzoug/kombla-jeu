/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.kombla.main.shared.prensentation.sprites;

import net.vpc.gaming.atom.annotations.AtomSpriteView;
import net.vpc.gaming.atom.engine.SpriteTask;
import net.vpc.gaming.atom.engine.tasks.MoveSpriteTask;
import net.vpc.gaming.atom.model.MovementStyles;
import net.vpc.gaming.atom.model.Orientation;
import net.vpc.gaming.atom.model.OrientationType;
import net.vpc.gaming.atom.model.Sprite;
import net.vpc.gaming.atom.presentation.ImageSpriteView;
import net.vpc.gaming.atom.presentation.OrientationImageSelector;
import net.vpc.gaming.atom.presentation.Scene;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
@AtomSpriteView(
        scene = "mainLocal,mainServer,mainClient",
        kind = "Person"
)
public class PersonView extends ImageSpriteView {

    public PersonView() {
//        super(1, 1, "person.png");
        super("/person.png", 13, 16);
        //setResizeImages(true);
        setImageSelector(new OrientationImageSelector(OrientationType.PLUS_ORIENTATION) {
            @Override
            protected int getImageIndex(Sprite sprite, Scene view, long frame, Orientation dir, int player, double speed) {
                boolean move = sprite.getMovementStyle()!= MovementStyles.STOPPED;
                int max_players = 4;
                int anime_per_dir = 13;
                int anime_per_player = anime_per_dir * 4;
                int ndx_frame = move ? ((int) (frame % anime_per_dir)) : (anime_per_dir - 1);
                int ndx_dir = dir == Orientation.EAST ? 0 : dir == Orientation.WEST ? 1 : dir == Orientation.NORTH ? 2 : dir == Orientation.SOUTH ? 3 : -1;
                int ndx_player = player<=0?0:(player - 1 % max_players);
                return ndx_player * anime_per_player + ndx_dir * anime_per_dir + ndx_frame;
            }
        });
    }

}
