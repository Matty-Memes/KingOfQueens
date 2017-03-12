package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;


// Local Imports
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

// Android Imports
import android.graphics.Bitmap;
import android.graphics.Canvas;


// so it's linked to update
public class BasicCard extends GameObject {

    private String name,description;
    private CardSchools cardSchool;
    private Bitmap cardBackSprite;


    // monitors whether or not a card is destroyed
    public boolean destroyed;

    // pointer id for keeping track of touch input
    public int pointerID;

    // need all the fields from the superclass


    public BasicCard(int x, int y, int width, int height, Bitmap Sprite, Bitmap cardBackSprite,
                     String name, String description, CardSchools cardSchool, boolean destroyed,int pointerID)
    {
        super(x, y, width, height, Sprite);
        this.cardBackSprite = cardBackSprite;
        this.name = name;
        this.description= description;
        this.cardSchool = cardSchool;
        this.destroyed = destroyed;
        this.pointerID = pointerID;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {return description;}

    public void setDescription() {this.description =description;}

    public CardSchools getCardSchool() {return cardSchool;}

    public void setCardSchool() { this.cardSchool = cardSchool;}


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
