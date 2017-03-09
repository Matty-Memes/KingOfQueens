package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;

/**
 * Created by Matt on 08/03/2017.
 */

public class CardLibrary {
    //This class contains all the information for each card in the game

    AssetManager assetManager;
    private int x = 0;
    private int y = 0;
    private int width = 433;
    private int height = 641;
    private int pointerID = 42;
    private Bitmap cardbackSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Cardback.png");

    //*Monster Cards*//

    /*Engineering*/

    //Engineer1
    private Bitmap eng1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-1.png");
    private ManaTypes[] attack1ManaCostEng1 = new ManaTypes[] {ManaTypes.ENGINEERING_MANA};
    private ManaTypes[] attack2ManaCostEng1 = new ManaTypes[] {ManaTypes.ENGINEERING_MANA,ManaTypes.ENGINEERING_MANA};

    public MonsterCard engineer1 = new MonsterCard(x,y,width,height,eng1Sprite,cardbackSprite,false,42,"Engineer","A novice Engineer",CardLevel.UNDERGRAD,80,0,
            CardSchools.ENGINEERING,CardSchools.SOCIAL_SCIENCES,CardSchools.BUILT_ENVIORNMENT,"Wrench Bash",30,attack1ManaCostEng1,
            "Auto Turret",0,attack2ManaCostEng1);

    //Engineer2
    private Bitmap eng2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-2.png");
    private ManaTypes[] attack1ManaCostEng2 = new ManaTypes[] {ManaTypes.ENGINEERING_MANA,ManaTypes.ENGINEERING_MANA};
    private ManaTypes[] attack2ManaCostEng2 = new ManaTypes[] {ManaTypes.ENGINEERING_MANA,ManaTypes.ENGINEERING_MANA,ManaTypes.ENGINEERING_MANA};

    public MonsterCard engineer2 = new MonsterCard(x,y,width,height,eng2Sprite,cardbackSprite,false,42,"Engineer","A standard Engineer",CardLevel.GRAD,120,0,
            CardSchools.ENGINEERING,CardSchools.SOCIAL_SCIENCES,CardSchools.BUILT_ENVIORNMENT,"Hammer Time",50,attack1ManaCostEng2,
            "Auto Turret 2",0,attack2ManaCostEng2);


    //*Mana Cards*//

    //Engineering
    private Bitmap engManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-Engineering.png");

    public ManaCard engineeringMana = new ManaCard(x,y,width,height,engManaSprite,cardbackSprite,"Engineering Mana","Mana used to perform Engineering attacks",
            false,CardSchools.ENGINEERING,ManaTypes.ENGINEERING_MANA,42);

    //EEECS
    private Bitmap EEECSManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-EEECS.png");

    public ManaCard EEECSMana = new ManaCard(x,y,width,height,EEECSManaSprite,cardbackSprite,"EEECS Mana","Mana used to perform EEECS attacks",
            false,CardSchools.EEECS,ManaTypes.EEECS_MANA,42);

    //Medical
    private Bitmap medicalManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-Medical.png");

    public ManaCard medicalMana = new ManaCard(x,y,width,height,medicalManaSprite,cardbackSprite,"Medical Science Mana","Mana used to perform Medical Science attacks",
            false,CardSchools.MEDICS,ManaTypes.MEDICS_MANA,42);

    //Social Science
    private Bitmap socialSciManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-SocialSci.png");

    public ManaCard socialSciMana = new ManaCard(x,y,width,height,socialSciManaSprite,cardbackSprite,"Social Science Mana","Mana used to perform Social Science attacks",
            false,CardSchools.SOCIAL_SCIENCES,ManaTypes.SOCIAL_SCIENCES_MANA,42);

    //Built Environment
    private Bitmap builtEnviManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-BuiltEnvironment.png");

    public ManaCard builtEnvironmentMana = new ManaCard(x,y,width,height,builtEnviManaSprite,cardbackSprite,"Built Environment Mana","Mana used to perform Built Environment attacks",
            false,CardSchools.BUILT_ENVIORNMENT,ManaTypes.BUILT_ENVIRONMENT_MANA,42);

    //Arts & Humanities
    private Bitmap artsManaSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Mana/Mana-Arts.png");

    ManaCard ArtsMana = new ManaCard(x,y,width,height,artsManaSprite,cardbackSprite,"Arts and Humanities Mana","Mana used to perform Arts and Humanities attacks",
            false,CardSchools.ARTS_HUMANITIES,ManaTypes.ARTS_HUMANITIES_MANA,42);
}
