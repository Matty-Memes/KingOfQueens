package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;


// Local Imports
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

// Android Imports
import android.graphics.Bitmap;
import android.graphics.Canvas;


// so it's linked to update
public class basicCard extends GameObject {

    private cardSchools cardSchool;

    // monitors whether or not a card is destroyed
    public boolean destroyed;

    // pointer id for keeping track of touch input
    public int pointerID;

    // need all the fields from the superclass


    // Modified by Andrew - 27/03/17
    public basicCard(int x, int y, int width, int height, Bitmap Sprite,
                     cardSchools cardSchool, boolean destroyed, int pointerID)
    {
        super(x, y, width, height, Sprite);
        this.cardSchool = cardSchool;
        this.destroyed = destroyed;
        this.pointerID = pointerID;
    }

    // getters and setters

    public cardSchools getCardSchool() {return cardSchool;}

    public void setCardSchool() { this.cardSchool = cardSchool;}

    public void setCardSchool(cardSchools cardSchool) {
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
        if (getCardSchool()== cardSchools.EEECS)
        {
            //Set card type graphic to EEECS
        }

        if (getCardSchool()== cardSchools.MEDICS)
        {
            //Set card type graphic to Medic
        }
        if (getCardSchool()== cardSchools.ARTS_HUMANITIES)
        {
            //Set card type graphic to Arts & Humanities
        }
        if (getCardSchool()== cardSchools.BUILT_ENVIORNMENT)
        {
            //Set card type graphic to Built Environment
        }
        if(getCardSchool()== cardSchools.ENGINEERING)
        {
            //Set card type graphic to Engineering
        }
        if(getCardSchool()== cardSchools.SOCIAL_SCIENCES)
        {
            //Set card type graphic to Social Sciences
        }
    }

}
