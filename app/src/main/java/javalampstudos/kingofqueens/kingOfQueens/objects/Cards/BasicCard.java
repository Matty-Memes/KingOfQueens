package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

/**
 * Created by Andrew on 29/03/2017.
 */

public class BasicCard extends GameObject

{

        private CardSchools cardSchool;

        // monitors whether or not a card is destroyed
        public boolean destroyed;

        // pointer id for keeping track of touch input
        public int pointerID;



        // need all the fields from the superclass


        // Modified by Andrew - 27/03/17
        public BasicCard(int x, int y, int width, int height, Bitmap Sprite, boolean player,
                         CardSchools cardSchool, boolean destroyed, int pointerID)
        {
            super(x, y, width, height, Sprite, player);
            this.cardSchool = cardSchool;
            this.destroyed = destroyed;
            this.pointerID = pointerID;

        }

        // getters and setters

        public CardSchools getCardSchool() {return cardSchool;}

        public void setCardSchool() { this.cardSchool = cardSchool;}

        public void setCardSchool(CardSchools cardSchool) {
            this.cardSchool = cardSchool;
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

// set the pointer ID

        public void setPointerID (int pID)
        {
            this.pointerID = pID;

        }

        public void draw (Canvas canvas)
        {



            super.draw(canvas);

        }

        //Matt:This metod will be used in creating the card graphic. It checks the card School and sets the graphic

        public void setCardSchoolGraphic()
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






}
