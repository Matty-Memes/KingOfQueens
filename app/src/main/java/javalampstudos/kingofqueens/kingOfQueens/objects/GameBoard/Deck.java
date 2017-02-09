package javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard;

import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Matt on 06/02/2017.
 */

public class Deck extends GameObject {

    private int noOfMonsterCards,noOfManaCards,noOfSupportCards;
    private MonsterCard[] monsterArray = new MonsterCard[noOfMonsterCards];
    private ManaCard[] manaArray = new ManaCard[noOfManaCards];
    private SupportCard[] supportArray = new SupportCard[noOfSupportCards];

    public Deck(float x, float y, int width, int height,
    Bitmap sprite)
    {
        super(x, y, width, height, sprite);
    }

}
