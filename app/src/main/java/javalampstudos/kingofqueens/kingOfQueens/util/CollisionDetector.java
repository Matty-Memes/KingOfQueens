package javalampstudos.kingofqueens.kingOfQueens.util;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Nathan on 12/03/2017.
 */

public class CollisionDetector {



    public boolean collision (GameObject object1, GameObject object2) {

        if ((((object1.x - object1.width / 2) - 15) <= (object2.x + object2.width / 2)) &&
                (((object1.x + object1.width / 2) + 15) >= (object2.x - object2.width / 2)) &&
                (((object1.y - object1.height / 2) - 15) <= (object2.y + object2.height / 2)) &&
                (((object1.y + object1.height / 2) + 15) >= (object2.y - object2.height / 2))) {
            return true;
        }

        return false;
    }

}
