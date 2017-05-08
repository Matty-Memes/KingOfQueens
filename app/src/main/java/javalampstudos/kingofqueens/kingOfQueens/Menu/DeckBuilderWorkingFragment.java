package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.FilterType;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.JSONcardLibrary;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

/**
 * Created by Matt on 07/05/2017.
 * Hacked together version of the Deck builder which *should* work. Look at other versions for better code practice
 */

public class DeckBuilderWorkingFragment extends MenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,cardSchoolFilterBitmap,cardTypeFilterBitmap,resetFilterBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,deckButtonBitmap,returnToBuilderBitmap,cardbackBitmap;
    // Bitmaps for the CardType Filter
    private Bitmap typeFilterBitmap,monsterFilterBitmap,manaFilterBitmap,supportFilterBitmap;
    //Bitmaps for the CardSchool Filter
    private Bitmap schoolFilterBitmap,engineerFilterBitmap,medicFilterBitmap,EEECSFitlerBitmap,artsFilterBitmap,builtEnviFilterBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect, cardSchoolFilterRect,cardTypeFilterRect,resetFilterRect,backRect,leftArrowRect,rightArrowRect,deckButtonRect,returnToBuilderRect;
    //rects for storing cards
    private Rect card1Rect,card2Rect,card3Rect,card4Rect,card5Rect,card6Rect,card7Rect,card8Rect;
    //Rects for the CardType Filter
    private Rect typeFilterBoxRect,monsterFilterRect,manaFilterRect,supportFilterRect;
    //Rects for the CardSchool Filter
    private Rect schoolFilterBoxRect,engineerFilterRect,medicFilterRect,EEECSFilterRect,artsFilterRect,builtEnviFilterRect;

    //Bitmaps for the cards
    //Engineer Monsters
    private Bitmap Engineer1Sprite,Engineer2Sprite,Engineer3Sprite, Turret1Sprite, Turret2Sprite, Turret3Sprite, AeroSpaceEngineer1Sprite,AeroSpaceEngineer2Sprite;
    //EEECS Monsters
    private Bitmap CodeMonkeySprite,BananaEngineerSprite,SeniorBananaEngineerSprite,SQLSealSprite, DataAnalystSprite, DataAdminSprite, ScriptKiddieSprite, HackerManSprite;
    //Medic Monsters
    private Bitmap NurseSprite, DoctorSprite,SurgeonSprite,MedicSprite,FieldMedicSprite,ParaMedicSprite,ChemistSprite,PharmacistSprite;
    //Social Science Monsters
    private Bitmap JuniorHistorianSprite,LegitHistorianSprite,TimeTravelerSprite,DoodlerSprite,SketcherSprite,ArtisteSprite,TinkererSprite,CraftsmanSprite;
    //Built Enviroment Monsters
    private Bitmap RockHunterSprite,GeologistSprite,GraveDiggerSprite,ArcheologistSprite,Architect1Sprite,Architect2Sprite,Architect3Sprite;

    private Bitmap monsters [] = {Engineer1Sprite,Engineer2Sprite,Engineer3Sprite, Turret1Sprite, Turret2Sprite, Turret3Sprite, AeroSpaceEngineer1Sprite,AeroSpaceEngineer2Sprite,CodeMonkeySprite,BananaEngineerSprite,SeniorBananaEngineerSprite,SQLSealSprite,
            DataAnalystSprite, DataAdminSprite, ScriptKiddieSprite, HackerManSprite,NurseSprite, DoctorSprite,SurgeonSprite,MedicSprite,FieldMedicSprite,ParaMedicSprite,ChemistSprite,PharmacistSprite,JuniorHistorianSprite,LegitHistorianSprite,TimeTravelerSprite,
            DoodlerSprite,SketcherSprite,ArtisteSprite,TinkererSprite,CraftsmanSprite,RockHunterSprite,GeologistSprite,GraveDiggerSprite,ArcheologistSprite,Architect1Sprite,Architect2Sprite,Architect3Sprite};

    private Bitmap medicalManaSprite,artsManaSprite,eeecsManaSprite,engineeringManaSprite,builtEnvironmentManaSprite;
    private Bitmap mana[] = {eeecsManaSprite,medicalManaSprite,artsManaSprite,engineeringManaSprite,builtEnvironmentManaSprite};
    private Bitmap ointmentSprite,superOintmentSprite,ultraOintmentSprite,attackBoosterSprite,superAttackBoosterSprite,ultraAttackBoosterSprite,defenceBoosterSprite,superDefenceBoosterSprite,ultraDefenceBoosterSprite,sleepSprite,enrageSprite,curePoisonSprite,cureSleepSprite,cureEnrageSprite;
    private Bitmap support[] = {ointmentSprite,superOintmentSprite,ultraOintmentSprite,attackBoosterSprite,superAttackBoosterSprite,ultraAttackBoosterSprite,defenceBoosterSprite,superDefenceBoosterSprite,ultraDefenceBoosterSprite,sleepSprite,enrageSprite,curePoisonSprite,cureSleepSprite,cureEnrageSprite};
    public Deck playerDeck = new Deck();

    int page = 1; //Starts the view on page 1
    int totalNumberOfPages = 8; //total number of pages, remains the same
    int currentNumberOfPages = 8;//can be manipulated by the filters. Used for showing page numbers
    private FilterType filter = FilterType.none;
    //Arrays containing the id for each card needed on the page. more for reference
//    private int[] page1 = {1,2,3,4,5,6,7,8}; //Engineer cards
//    private int[] page2 = {9,10,11,12,13,14,15,16}; //Medical cards
//    private int[] page3 = {17,18,19,20,21,22,23,24};//EEECS cards
//    private int[] page4 = {25,26,27,28,29,30,31,32};//Arts cards
//    private int[] page5 = {33,34,35,36,37,38,39}; //Built envi cards
//    private int[] page6 = {40,41,42,43,45}; //Mana cards //44 is SocSci which isn't being included
//    private int[] page7 = {46,47,48,49,50,51,52,53};//Support1
//    private int[] page8 = {54,55,56,57,58,59}; //support2
    private int[] card1 = {1,9,17,25,33,40,46,54};
    private int[] card2 = {2,10,18,26,34,41,47,55};
    private int[] card3 = {3,11,19,27,35,42,48,56};
    private int[] card4 = {4,12,20,28,36,43,49,57};
    private int[] card5 = {5,13,21,29,37,45,50,58};
    private int[] card6 = {6,14,22,30,38,-1,51,59};//use-1 for cardback
    private int[] card7 = {7,15,23,31,39,-1,52,-1};
    private int[] card8 = {8,16,24,32,40,-1,53,-1};

    //used to print card bitmaps
    public JSONcardLibrary library;


    public DeckBuilderWorkingFragment()

    {


    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets related to the currently selected language
        AssetManager assetManager = getActivity().getAssets();



        // set up rects here
        bgroundRect = new Rect();
        cardSchoolFilterRect = new Rect();
        cardTypeFilterRect = new Rect();
        resetFilterRect = new Rect();
        leftArrowRect = new Rect();
        rightArrowRect = new Rect();
        deckButtonRect = new Rect();
        returnToBuilderRect = new Rect();
        card1Rect = new Rect();
        card2Rect = new Rect();
        card3Rect = new Rect();
        card4Rect = new Rect();
        card5Rect = new Rect();
        card6Rect = new Rect();
        card7Rect = new Rect();
        card8Rect = new Rect();
        typeFilterBoxRect = new Rect();
        monsterFilterRect = new Rect();
        manaFilterRect = new Rect();
        supportFilterRect = new Rect();
        schoolFilterBoxRect = new Rect();
        engineerFilterRect = new Rect();
        medicFilterRect = new Rect();
        EEECSFilterRect = new Rect();
        artsFilterRect = new Rect();
        builtEnviFilterRect = new Rect();

        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        cardSchoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardSchoolFilter.png");
        cardTypeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/CardTypeFilter.png");
        resetFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ResetFilter.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        deckButtonBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ViewDeckButton.png");
        cardbackBitmap = AssetLoader.loadBitmap(assetManager,"img/Cards/Cardback.png");
        typeFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxType.png");
        monsterFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MonsterFilter.png");
        manaFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ManaFilter.png");
        supportFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/SupportFilter.png");
        schoolFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/FilterBoxSchool.png");
        engineerFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EngineeringLogo.png");
        medicFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/MedicalLogo.png");
        EEECSFitlerBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/EEECSLogo.png");
        artsFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArtsLogo.png");
        builtEnviFilterBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BuiltEnvriLogo.png");

        //Cards
        //Engineering Monsters
        Engineer1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-1.png");
        Engineer2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-2.png");
        Engineer3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-3.png");
        Turret1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-1.png");
        Turret2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-2.png");
        Turret3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-3.png");
        AeroSpaceEngineer1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-1.png");
        AeroSpaceEngineer2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-2.png");

        //EEECS Monsters
        CodeMonkeySprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/CodeMonkey.png");
        BananaEngineerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/BananaEngineer.png");
        SeniorBananaEngineerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SeniorBananaEngineer.png");
        SQLSealSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SQLSeal.png");
        DataAnalystSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAnalyst.png");
        DataAdminSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAdmin.png");
        ScriptKiddieSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/ScriptKiddie.png");
        HackerManSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/HackerMan.png");
        //Medic Monsters
        NurseSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Monster/Medical/Nurse.png");
        DoctorSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Doctor.png");
        SurgeonSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Surgeon.png");
        MedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Medic.png");
        FieldMedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/FieldMedic.png");
        ParaMedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/ParaMedic.png");
        ChemistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Chemist.png");
        PharmacistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Pharmacist.png");
        //Arts and Humanities Monsters
        JuniorHistorianSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/JuniorHistorian.png");
        LegitHistorianSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/LegitHistorian.png");
        TimeTravelerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/TimeTraveller.png");
        DoodlerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Doodler.png");
        SketcherSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Sketcher.png");
        ArtisteSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Artiste.png");
        TinkererSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Tinkerer.png");
        CraftsmanSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Craftsman.png");
        //Built Enviroment Monsters
        RockHunterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/RockHunter.png");
        GeologistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Geologist.png");
        GraveDiggerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/GraveDigger.png");
        ArcheologistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Archeologist.png");
        Architect1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-1.png");
        Architect2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-2.png");
        Architect3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-3.png");

        // ManaSprites
        medicalManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/MedicalMana.png");
        artsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ArtsMana.png");
        eeecsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        engineeringManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EngineeringMana.png");
        builtEnvironmentManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/BuiltMana.png");
        //support cards

        ointmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/Ointment.png");
        superOintmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/SuperOintment.png");
        ultraOintmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/UltraOintment.png");
        attackBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/AttackBoost.png");
        superAttackBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/SuperAttackBoost.png");
        ultraAttackBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/UltraAttackBoost.png");
        defenceBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/DefenceBoost.png");
        superDefenceBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/SuperDefenceBoost.png");
        ultraDefenceBoosterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/UltraDefenceBoost.png");
        sleepSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/Sleep.png");
        enrageSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/Enraged.png");
        cureEnrageSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/CureEnrage.png");
        cureSleepSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/CureSleep.png");
        curePoisonSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/CurePoison.png");

        // Set up values for each deckBuilder rect
        backRect = new Rect((4),(24), (96), (96));
        cardTypeFilterRect = new Rect(120, 24, 312, 96);
        cardSchoolFilterRect = new Rect(552,24,744,96);
        leftArrowRect = new Rect(0,201,96,279);
        rightArrowRect = new Rect(703,201,800,279);
        deckButtonRect = new Rect(688,400,792,472);
        //Cardslot Rects
        card1Rect = new Rect(125,108,247,276);
        card2Rect = new Rect(269,108,391,276);
        card3Rect = new Rect(408,108,530,276);
        card4Rect = new Rect(552,108,674,276);
        card5Rect = new Rect(125,288,391,456);
        card6Rect = new Rect(269,288,391,456);
        card7Rect = new Rect(408,288,552,456);
        card8Rect = new Rect(552,288,391,674);

        //CardSchoolFilter rects
        schoolFilterBoxRect = new Rect(220,120,579,360);
        engineerFilterRect = new Rect(240,192,312,264);
        medicFilterRect = new Rect(364,192,436,264);
        EEECSFilterRect = new Rect(480,192,556,264);
        artsFilterRect = new Rect(302,264,374,336);
        builtEnviFilterRect = new Rect(423,264,495,336);

        //cardTypeFilter rects
        typeFilterBoxRect = new Rect(211,160,578,319);
        monsterFilterRect = new Rect(240,226,336,282);
        manaFilterRect = new Rect(249,226,447,282);
        supportFilterRect = new Rect(462,226,558,282);

//        backRect = new Rect((int) (width - 96 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 24 * uiScaling),
//                (int) (width - 8 * gameScaling),
//                (int) (height / 2 + 96 * uiScaling));
//        cardTypeFilterRect = new Rect((int) (width - 120 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 24 * uiScaling),
//                (int) (width - 312 * gameScaling),
//                (int) (height / 2 + 96 * uiScaling));
//        cardSchoolFilterRect = new Rect((int) (width - 552 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 24 * uiScaling),
//                (int) (width - 744 * gameScaling),
//                (int) (height / 2 + 96 * uiScaling));
//        leftArrowRect = new Rect((int) (width - 0 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 201 * uiScaling),
//                (int) (width - 96 * gameScaling),
//                (int) (height / 2 + 279 * uiScaling));
//        rightArrowRect = new Rect((int) (width - 703 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 201 * uiScaling),
//                (int) (width - 800 * gameScaling),
//                (int) (height / 2 + 279 * uiScaling));
//        deckButtonRect = new Rect((int) (width - 688 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 400 * uiScaling),
//                (int) (width - 792 * gameScaling),
//                (int) (height / 2 + 472 * uiScaling));
//        //Cardslot Rects
//        card1Rect = new Rect((int) (width - 125 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 108 * uiScaling),
//                (int) (width - 247 * gameScaling),
//                (int) (height / 2 + 276 * uiScaling));
//        card2Rect = new Rect((int) (width - 269 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 108 * uiScaling),
//                (int) (width - 391 * gameScaling),
//                (int) (height / 2 + 276 * uiScaling));
//        card3Rect = new Rect((int) (width - 408 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 108 * uiScaling),
//                (int) (width - 530 * gameScaling),
//                (int) (height / 2 + 276 * uiScaling));
//        card4Rect = new Rect((int) (width - 552 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 108 * uiScaling),
//                (int) (width - 674 * gameScaling),
//                (int) (height / 2 + 276 * uiScaling));
//        card5Rect = new Rect((int) (width - 125 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 288 * uiScaling),
//                (int) (width - 391 * gameScaling),
//                (int) (height / 2 + 456 * uiScaling));
//        card6Rect = new Rect((int) (width - 269 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 288 * uiScaling),
//                (int) (width - 391 * gameScaling),
//                (int) (height / 2 + 456 * uiScaling));
//        card7Rect = new Rect((int) (width - 408 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 288 * uiScaling),
//                (int) (width - 552 * gameScaling),
//                (int) (height / 2 + 456 * uiScaling));
//        card8Rect = new Rect((int) (width - 552 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 288 * uiScaling),
//                (int) (width - 391 * gameScaling),
//                (int) (height / 2 + 674 * uiScaling));
//
//        //CardSchoolFilter rects
//        schoolFilterBoxRect = new Rect((int) (width - 220 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 120 * uiScaling),
//                (int) (width - 579 * gameScaling),
//                (int) (height / 2 + 360 * uiScaling));
//        engineerFilterRect = new Rect((int) (width - 240 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 192 * uiScaling),
//                (int) (width - 312 * gameScaling),
//                (int) (height / 2 + 264 * uiScaling));
//        medicFilterRect = new Rect((int) (width - 364 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 192 * uiScaling),
//                (int) (width - 436 * gameScaling),
//                (int) (height / 2 + 264 * uiScaling));
//        EEECSFilterRect = new Rect((int) (width - 480 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 192 * uiScaling),
//                (int) (width - 556 * gameScaling),
//                (int) (height / 2 + 264 * uiScaling));
//        artsFilterRect = new Rect((int) (width - 302 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 264 * uiScaling),
//                (int) (width - 374 * gameScaling),
//                (int) (height / 2 + 336 * uiScaling));
//        builtEnviFilterRect = new Rect((int) (width - 423 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 264 * uiScaling),
//                (int) (width - 495 * gameScaling),
//                (int) (height / 2 + 336 * uiScaling));
//
//        //cardTypeFilter rects
//        typeFilterBoxRect = new Rect((int) (width - 211 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 160 * uiScaling),
//                (int) (width - 578 * gameScaling),
//                (int) (height / 2 + 319 * uiScaling));
//        monsterFilterRect = new Rect((int) (width - 240 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 226 * uiScaling),
//                (int) (width - 336 * gameScaling),
//                (int) (height / 2 + 282 * uiScaling));
//        manaFilterRect = new Rect((int) (width - 249 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 226 * uiScaling),
//                (int) (width - 447 * gameScaling),
//                (int) (height / 2 + 282 * uiScaling));
//        supportFilterRect = new Rect((int) (width - 462 * uiScaling - 3 * gameScaling),
//                (int) (height / 2 + 226 * uiScaling),
//                (int) (width - 558 * gameScaling),
//                (int) (height / 2 + 282 * uiScaling));
    }

    //used for displaying Filter Menus
    boolean typeFilterOn=false;
    boolean schoolFilterOn = false;

    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen

        canvas.drawBitmap(bgroundBitmap,null,bgroundRect,null);
        canvas.drawBitmap(backBitmap, null, backRect, null);
        canvas.drawBitmap(cardSchoolFilterBitmap,null,cardSchoolFilterRect,null);
        canvas.drawBitmap(cardTypeFilterBitmap,null,cardTypeFilterRect,null);
        canvas.drawBitmap(resetFilterBitmap,null,cardTypeFilterRect,null);
        canvas.drawBitmap(backBitmap,null,backRect,null);
        canvas.drawBitmap(deckButtonBitmap,null,deckButtonRect,null);

        //Draw cards for each page
        switch(page)
        {
            case 1:
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(Engineer1Sprite, null, card1Rect, null);
                canvas.drawBitmap(Engineer2Sprite, null, card2Rect, null);
                canvas.drawBitmap(Engineer3Sprite, null, card3Rect, null);
                canvas.drawBitmap(Turret1Sprite, null, card4Rect, null);
                canvas.drawBitmap(Turret2Sprite, null, card5Rect, null);
                canvas.drawBitmap(Turret3Sprite, null, card6Rect, null);
                canvas.drawBitmap(AeroSpaceEngineer1Sprite, null, card7Rect, null);
                canvas.drawBitmap(AeroSpaceEngineer2Sprite, null, card8Rect, null);
                break;
            case 2:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(NurseSprite, null, card1Rect, null);
                canvas.drawBitmap(DoctorSprite, null, card2Rect, null);
                canvas.drawBitmap(SurgeonSprite, null, card3Rect, null);
                canvas.drawBitmap(MedicSprite, null, card4Rect, null);
                canvas.drawBitmap(FieldMedicSprite, null, card5Rect, null);
                canvas.drawBitmap(ParaMedicSprite, null, card6Rect, null);
                canvas.drawBitmap(ChemistSprite, null, card7Rect, null);
                canvas.drawBitmap(PharmacistSprite, null, card8Rect, null);
                break;
            case 3:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(CodeMonkeySprite, null, card1Rect, null);
                canvas.drawBitmap(BananaEngineerSprite, null, card2Rect, null);
                canvas.drawBitmap(SeniorBananaEngineerSprite, null, card3Rect, null);
                canvas.drawBitmap(SQLSealSprite, null, card4Rect, null);
                canvas.drawBitmap(DataAnalystSprite, null, card5Rect, null);
                canvas.drawBitmap(DataAdminSprite, null, card6Rect, null);
                canvas.drawBitmap(ScriptKiddieSprite, null, card7Rect, null);
                canvas.drawBitmap(HackerManSprite, null, card8Rect, null);
                break;
            case 4:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(JuniorHistorianSprite, null, card1Rect, null);
                canvas.drawBitmap(LegitHistorianSprite, null, card2Rect, null);
                canvas.drawBitmap(TimeTravelerSprite, null, card3Rect, null);
                canvas.drawBitmap(DoodlerSprite, null, card4Rect, null);
                canvas.drawBitmap(SketcherSprite, null, card5Rect, null);
                canvas.drawBitmap(ArtisteSprite, null, card6Rect, null);
                canvas.drawBitmap(TinkererSprite, null, card7Rect, null);
                canvas.drawBitmap(CraftsmanSprite, null, card8Rect, null);
                break;
            case 5:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                if(filter!=FilterType.monster)
                {
                    canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                }
                canvas.drawBitmap(RockHunterSprite, null, card1Rect, null);
                canvas.drawBitmap(GeologistSprite, null, card2Rect, null);
                canvas.drawBitmap(GraveDiggerSprite, null, card3Rect, null);
                canvas.drawBitmap(ArcheologistSprite, null, card4Rect, null);
                canvas.drawBitmap(Architect1Sprite, null, card5Rect, null);
                canvas.drawBitmap(Architect2Sprite, null, card6Rect, null);
                canvas.drawBitmap(Architect3Sprite, null, card7Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card8Rect, null);
                break;
            case 6:
                if(filter!=FilterType.mana)
                {
                    canvas.drawBitmap(leftArrowBitmap, null, leftArrowRect, null);
                    canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                }
                canvas.drawBitmap(eeecsManaSprite, null, card1Rect, null);
                canvas.drawBitmap(medicalManaSprite, null, card2Rect, null);
                canvas.drawBitmap(artsManaSprite, null, card3Rect, null);
                canvas.drawBitmap(engineeringManaSprite, null, card4Rect, null);
                canvas.drawBitmap(builtEnvironmentManaSprite, null, card5Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card6Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card7Rect, null);
                canvas.drawBitmap(cardbackBitmap, null, card8Rect, null);
                break;
            case 7:
                if(filter!=FilterType.support)
                {
                    canvas.drawBitmap(leftArrowBitmap, null, leftArrowRect, null);
                }
                canvas.drawBitmap(rightArrowBitmap, null, rightArrowRect, null);
                canvas.drawBitmap(ointmentSprite,null,card1Rect,null);
                canvas.drawBitmap(superOintmentSprite,null,card2Rect,null);
                canvas.drawBitmap(ultraOintmentSprite,null,card3Rect,null);
                canvas.drawBitmap(attackBoosterSprite,null,card4Rect,null);
                canvas.drawBitmap(superAttackBoosterSprite,null,card5Rect,null);
                canvas.drawBitmap(ultraAttackBoosterSprite,null,card6Rect,null);
                canvas.drawBitmap(defenceBoosterSprite,null,card7Rect,null);
                canvas.drawBitmap(superDefenceBoosterSprite,null,card8Rect,null);
                break;
            case 8:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(ultraDefenceBoosterSprite,null,card1Rect,null);
                canvas.drawBitmap(sleepSprite,null,card2Rect,null);
                canvas.drawBitmap(enrageSprite,null,card3Rect,null);
                canvas.drawBitmap(curePoisonSprite,null,card4Rect,null);
                canvas.drawBitmap(cureSleepSprite,null,card5Rect,null);
                canvas.drawBitmap(cureEnrageSprite,null,card6Rect,null);
                canvas.drawBitmap(cardbackBitmap,null,card8Rect,null);
                canvas.drawBitmap(cardbackBitmap,null,card8Rect,null);
                break;
        }

        //If a Filter button has been pressed, draw the Filter assets
        if(typeFilterOn)
        {
            canvas.drawBitmap(typeFilterBitmap,null,typeFilterBoxRect,null);
            canvas.drawBitmap(monsterFilterBitmap,null,monsterFilterRect,null);
            canvas.drawBitmap(manaFilterBitmap,null,manaFilterRect,null);
            canvas.drawBitmap(supportFilterBitmap,null,supportFilterRect,null);
        }
        if(schoolFilterOn)
        {
            canvas.drawBitmap(schoolFilterBitmap,null,schoolFilterBoxRect,null);
            canvas.drawBitmap(engineerFilterBitmap,null,engineerFilterRect,null);
            canvas.drawBitmap(medicFilterBitmap,null,medicFilterRect,null);
            canvas.drawBitmap(EEECSFitlerBitmap,null,EEECSFilterRect,null);
            canvas.drawBitmap(artsFilterBitmap,null,artsFilterRect,null);
            canvas.drawBitmap(builtEnviFilterBitmap,null,builtEnviFilterRect,null);
        }

        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);


                if(cardSchoolFilterRect.contains(x,y))
                {
                    //Bring up the cardSchool Filter menu
                    schoolFilterOn =true;
                }

                if(schoolFilterOn)
                {
                    if(engineerFilterRect.contains(x,y))
                    {
                        //Filter by engineer cards
                        page = 1;
                        filter = FilterType.engineer;
                        currentNumberOfPages = 1;
                        schoolFilterOn =false;
                    }
                    if(medicFilterRect.contains(x,y))
                    {
                        //filter by medic cards
                        page = 2;
                        filter = FilterType.medic;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }
                    if(EEECSFilterRect.contains(x,y))
                    {
                        //filter by EEECS cards
                        page =3;
                        filter = FilterType.EEECS;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }
                    if(artsFilterRect.contains(x,y))
                    {
                        //filter by arts cards
                        page =4;
                        filter = FilterType.arts;
                        currentNumberOfPages = 1;
                        schoolFilterOn=false;
                    }
                    if(builtEnviFilterRect.contains(x,y))
                    {
                        //filter by built environment cards
                        page = 5;
                        filter = FilterType.built;
                        currentNumberOfPages = 1;
                        schoolFilterOn = false;
                    }

                    if(backRect.contains(x,y))
                    {
                        //return to deck builder without changing filter
                        schoolFilterOn =false;
                    }
                }

                if(cardTypeFilterRect.contains(x,y))
                {
                    //Bring up the cardType filter menu
                    typeFilterOn = true;
                }
                if(typeFilterOn)
                {
                    if(monsterFilterRect.contains(x,y))
                    {
                        page = 1;
                        filter = FilterType.monster;
                        currentNumberOfPages = 5;
                        typeFilterOn =false;
                    }
                    if(manaFilterRect.contains(x,y))
                    {
                        page =6;
                        filter = FilterType.mana;
                        currentNumberOfPages = 1;
                        typeFilterOn = false;
                    }
                    if(supportFilterRect.contains(x,y))
                    {
                        page = 7;
                        filter = FilterType.support;
                        currentNumberOfPages = 2;
                        typeFilterOn = false;
                    }
                }

                if (resetFilterRect.contains(x,y))
                {
                    filter = FilterType.none;
                    currentNumberOfPages = totalNumberOfPages;
                    System.out.print("The filter has been reset");
                }


                //when there is only one page due to a filter, the arrow does not appear
                if (!filterSchoolEnabled()||(filter !=FilterType.support&&page==7)||filter!=FilterType.mana||page!=1) //Arrow does not appear on first page
                {
                    if (leftArrowRect.contains(x, y)) {
                        //move to previous page
                        page--;
                    }
                }

                //when there is only one page due to a filter, the arrow does not appear
                if (!filterSchoolEnabled()||(filter !=FilterType.engineer && page==5)||filter!=FilterType.mana||page!=currentNumberOfPages) //Arrow does not appear on last page
                {
                    if (rightArrowRect.contains(x, y)) {
                        //move to next page
                        page++;
                    }
                }


                if(deckButtonRect.contains(x,y))
                {
                    //move to the Deck View
//                    getFragmentManager()
//                            .beginTransaction()
                          //  .replace(R.id.container, new DeckViewFragment(playerDeck), "DeckViewFragment").commit();
                }

                //If a card is touched it is added to the deck. This pulls information from the page arrays
                if (card1Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card1[page-1]);
                }
                if (card2Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card2[page-1]);
                }
                if (card3Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card3[page-1]);
                }
                if (card4Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card4[page-1]);
                }
                if (card5Rect.contains(x,y))
                {
                    playerDeck.addToDeck(card5[page-1]);
                }
                if (card6Rect.contains(x,y))
                {
                    if(card6[page-1]!=-1)
                        playerDeck.addToDeck(card6[page-1]);
                }
                if (card7Rect.contains(x,y))
                {
                    if(card7[page-1]!=-1)
                        playerDeck.addToDeck(card7[page-1]);
                }
                if (card8Rect.contains(x,y))
                {
                    if(card8[page-1]!=-1)
                        playerDeck.addToDeck(card8[page-1]);
                }
            }
        }
    }
    private boolean filterSchoolEnabled()
    {
        boolean enabled = false;
        switch(page)
        {
            case 1: if(filter == FilterType.engineer) {
                enabled = true;
            }
                break;
            case 2: if (filter == FilterType.medic)
            {
                enabled = true;
                break;
            }
            case 3: if(filter == FilterType.EEECS)
            {
                enabled = true;
                break;
            }
            case 4: if(filter == FilterType.arts)
            {
                enabled = true;
                break;
            }
            case 5: if(filter == FilterType.built)
            {
                enabled = true;
                break;
            }
        }
        return enabled;
    }

    boolean filterTypeEnabled()
    {
        boolean enabled = false;
        switch(page)
        {
            case 5:if(filter ==FilterType.monster)
                enabled =true;
                break;
            case 6:if(filter == FilterType.mana)
                enabled = true;
                break;
            case 7:if(filter ==FilterType.support)
                enabled = true;
                break;
        }
        return enabled;
    }
}

