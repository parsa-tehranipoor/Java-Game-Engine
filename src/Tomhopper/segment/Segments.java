/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.structure.AbstractSegment;

/**
 *
 * @author cdwan
 */
public class Segments {

    public static AbstractSegment getNewSeg(String name, Game game) {
        if (name.equals("AnimageTest")) {
            return new SegmentAnimageTest(game);
        }
        if (name.equals("Anish")) {
            return new SegmentAnish(game);
        }
        if (name.equals("Default")) {
            return new SegmentDefault(game);
        }
        if (name.equals("DemoTemplate")) {
            return new SegmentDemoTemplate(game);
        }
        if (name.equals("Idle")) {
            return new SegmentIdle(game);
        }
        if (name.equals("PolarPlayerTest")) {
            return new SegmentPolarPlayerTest(game);
        }
        if (name.equals("Segment")) {
            return new SegmentChooseSegment(game);
        }
        if (name.equals("TankBattle")) {
            return new SegmentTankBattle(game);
        }
        if (name.equals("Template")) {
            return new SegmentTemplate(game);
        }
        if (name.equals("Web")) {
            return new SegmentWeb(game);
        }
        if (name.equals("CollisionTest")) {
            return new SegmentCollisionTest(game);
        }
        if (name.equals("PuzzleIslandGame")) {
            return new SegmentPuzzleIslandGameOne(game);
        }
        return null;
    }

}
