package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * Created by Andrew on 29/03/2017.
 */

public class BasicCard extends GameObject

{
    public int targetX;

    public CardSchools cardSchool;

    // monitors whether or not a card is destroyed
    public boolean destroyed;

    // pointer id for keeping track of touch input
    public int pointerID;

    public int id;

    // need all the fields from the superclass


    // Modified by Andrew - 27/03/17
    public BasicCard(int x, int y, int width, int height, Bitmap Sprite, boolean player, int id,
                     CardSchools cardSchool, boolean destroyed, int pointerID, int targetX)
    {
        super(x, y, width, height, Sprite, player);
        this.id = id;
        this.cardSchool = cardSchool;
        this.destroyed = destroyed;
        this.pointerID = pointerID;
        this.targetX = targetX;

    }

    // getters and setters

    public void setCardSchool(CardSchools cardSchool) {
        this.cardSchool = cardSchool;
    }

    public CardSchools getCardSchool() {
        return cardSchool;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getPointerID() {
        return pointerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
// set the pointer ID

    public void setPointerID (int pID)
    {
        this.pointerID = pID;

    }

//    public void update()
//    {
//        super.update();
//    }

    public void draw (Canvas canvas)

    {
        if (destroyed == false)

        {

            super.draw(canvas);
        }

    }

    //Matt:What's this doing here?
    public void drawOpenWorld (Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {
        super.drawOpenWorld(canvas, layerViewport, screenViewport);
    }

    // hand cards that have been placed move back to the deck position
    public void moveToDeck ()

    {
        this.x = 800;
        this.y = 410;

    }

    // this invisibly returns the cards to their original positions before they were dragged
    // could reference board layout here

    public void resetPosition (int index)

    {
     if (index == 0)

     {
         this.x = 234;
         this.y = 410;
     }

        if (index == 1)

        {
            this.x = 334;
            this.y = 410;
        }

        if (index == 2)

        {
            this.x = 434;
            this.y = 410;
        }

        if (index == 3)

        {
            this.x = 534;
            this.y = 410;
        }

        if (index == 4)

        {
            this.x = 634;
            this.y = 410;
        }

    }

    //Matt:This metod will be used in creating the card graphic. It checks the card School and sets the graphic
    //05/05/2017 We originally planned to create the cards with layered images. Decided against it in the end

     /*   public void setCardSchoolGraphic()
        {
            if (getCardSchool()== CardSchools.EEECS)
            {
                //Set card type graphic to EEECS
            }

            if (getCardSchool()== CardSchools.MEDICS)
            {
                //Set card type graphic to Medic
            }
            if (getCardSchool()== CardSchools.ARTS_HUMANITIES)
            {
                //Set card type graphic to Arts & Humanities
            }
            if (getCardSchool()== CardSchools.BUILT_ENVIORNMENT)
            {
                //Set card type graphic to Built Environment
            }
            if(getCardSchool()== CardSchools.ENGINEERING)
            {
                //Set card type graphic to Engineering
            }
            if(getCardSchool()== CardSchools.SOCIAL_SCIENCES)
            {
                //Set card type graphic to Social Sciences
            }
        }
*/





}
