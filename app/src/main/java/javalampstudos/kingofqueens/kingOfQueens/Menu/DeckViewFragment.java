package javalampstudos.kingofqueens.kingOfQueens.Menu;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;

import javalampstudos.kingofqueens.R;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameBoard.Deck;

/**
 * Created by Matt on 07/05/2017.
 */

public class DeckViewFragment extends MenuFragment
{
    // DeckBuilder Bitmaps
    private Bitmap bgroundBitmap,backBitmap,leftArrowBitmap,rightArrowBitmap,backToBuilderBitmap,cardbackBitmap;
    // DeckBuilder Rects
    private Rect bgroundRect,backRect,leftArrowRect,rightArrowRect,backToBuilderRect;
    //rects for storing cards
    private Rect card1Rect,card2Rect,card3Rect,card4Rect,card5Rect,card6Rect,card7Rect,card8Rect;

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

    private Bitmap medicalManaSprite,artsManaSprite,eeecsManaSprite,engineeringManaSprite,builtEnvironmentManaSprite;
    private Bitmap ointmentSprite,superOintmentSprite,ultraOintmentSprite,attackBoosterSprite,superAttackBoosterSprite,ultraAttackBoosterSprite,defenceBoosterSprite,superDefenceBoosterSprite,ultraDefenceBoosterSprite,sleepSprite,enrageSprite,curePoisonSprite,cureSleepSprite,cureEnrageSprite;

    int page = 1;

    int playerDeck[];

   //Take in a player deck somehow



    public DeckViewFragment()

    {
        //onCreate();
    }

    public void onCreate()
    {
        //retrieve deck from builder
        Bundle args = getArguments();
        playerDeck = args.getIntArray("deck");
    }

    public void doSetup()


    {
        super.doSetup();

        // Loads in image assets
        AssetManager assetManager = getActivity().getAssets();

        // set up rects here
        bgroundRect = new Rect();
        leftArrowRect = new Rect();
        rightArrowRect = new Rect();
        backToBuilderRect = new Rect();
        card1Rect = new Rect();
        card2Rect = new Rect();
        card3Rect = new Rect();
        card4Rect = new Rect();
        card5Rect = new Rect();
        card6Rect = new Rect();
        card7Rect = new Rect();
        card8Rect = new Rect();


        // Load bitmaps
        bgroundBitmap = AssetLoader.loadBitmap(assetManager,"img/DeckBuilderBackground.png");
        backBitmap = AssetLoader.loadBitmap(assetManager,"img/Marc/ButtonBack.png");
        leftArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowLeft.png");
        rightArrowBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/ArrowRight.png");
        backToBuilderBitmap = AssetLoader.loadBitmap(assetManager,"img/Buttons/BackToBuilder.png");
        cardbackBitmap = AssetLoader.loadBitmap(assetManager,"img/Cards/Cardback.png");

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

        // Set up values for each rect
        backRect = new Rect((4),(24), (96), (96));
        leftArrowRect = new Rect(0,201,96,279);
        rightArrowRect = new Rect(703,201,800,279);
        backToBuilderRect = new Rect(688,400,792,472);
        //Cardslot Rects
        card1Rect = new Rect(125,108,247,276);
        card2Rect = new Rect(269,108,391,276);
        card3Rect = new Rect(408,108,530,276);
        card4Rect = new Rect(552,108,674,276);
        card5Rect = new Rect(125,288,247,456);
        card6Rect = new Rect(269,288,391,456);
        card7Rect = new Rect(408,288,530,456);
        card8Rect = new Rect(552,288,674,456);
    }


    public void doDraw(Canvas canvas)

    {
        super.doDraw(canvas);

        bgroundRect.set(0, 0, width, height);

        // draw each bitmap to the screen

        canvas.drawBitmap(bgroundBitmap,null,bgroundRect,null);
        canvas.drawBitmap(backBitmap,null,backRect,null);
        canvas.drawBitmap(backToBuilderBitmap,null,backToBuilderRect,null);
        switch(page)
        {
//            case 1:
//                canvas.drawBitmap(rightArrowBitmap,null,rightArrowRect,null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[0]), null, card1Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[1]), null, card2Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[2]), null, card3Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[3]), null, card4Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[4]), null, card5Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[5]), null, card6Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[6]), null, card7Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[7]), null, card8Rect, null);
//                break;
//            case 2:
//                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[8]), null, card1Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[9]), null, card2Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[10]), null, card3Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[11]), null, card4Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[12]), null, card5Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[13]), null, card6Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[-1]), null, card7Rect, null);
//                canvas.drawBitmap(bitmapLookup(playerDeck[-1]), null, card8Rect, null);
//                break;
            case 1:
                canvas.drawBitmap(rightArrowBitmap,null,rightArrowRect,null);
                canvas.drawBitmap(bitmapLookup(-1), null, card1Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card2Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card3Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card4Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card5Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card6Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card7Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card8Rect, null);
                break;
            case 2:
                canvas.drawBitmap(leftArrowBitmap,null,leftArrowRect,null);
                canvas.drawBitmap(bitmapLookup(-1), null, card1Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card2Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card3Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card4Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card5Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card6Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card7Rect, null);
                canvas.drawBitmap(bitmapLookup(-1), null, card8Rect, null);
                break;
        }



        // Touch input for each menu rect

        for (int i = 0; i < input.MAX_TOUCH_POINTS; i++) {
            if (input.isTouchDown(i)) {
                int x = (int) input.getTouchX(i), y = (int) input.getTouchY(i);

                if(backToBuilderRect.contains(x,y))
                {
                    //return to the deckbuilder fragment
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new DeckBuilderWorkingFragment(),
                                    "deck_builder_working_fragment").commit();
                }


            }

        }

    }
    private Bitmap bitmapLookup(int ID)
    {
        switch (ID)
        {
            case 1: return Engineer1Sprite;
            case 2: return Engineer2Sprite;
            case 3: return Engineer3Sprite;
            case 4: return Turret1Sprite;
            case 5: return Turret2Sprite;
            case 6: return Turret3Sprite;
            case 7:return AeroSpaceEngineer1Sprite;
            case 8:return AeroSpaceEngineer2Sprite;
            case 9: return CodeMonkeySprite;
            case 10: return BananaEngineerSprite;
            case 11:return SeniorBananaEngineerSprite;
            case 12:return SQLSealSprite;
            case 13:return DataAnalystSprite;
            case 14:return DataAdminSprite;
            case 15:return ScriptKiddieSprite;
            case 16:return HackerManSprite;
            case 17:return NurseSprite;
            case 18:return DoctorSprite;
            case 19:return SurgeonSprite;
            case 20:return MedicSprite;
            case 21:return FieldMedicSprite;
            case 22: return ParaMedicSprite;
            case 23: return ChemistSprite;
            case 24: return PharmacistSprite;
            case 25:return JuniorHistorianSprite;
            case 26:return LegitHistorianSprite;
            case 27:return TimeTravelerSprite;
            case 28:return DoodlerSprite;
            case 29:return SketcherSprite;
            case 30:return ArtisteSprite;
            case 31:return TinkererSprite;
            case 32:return CraftsmanSprite;
            case 33:return RockHunterSprite;
            case 34:return GeologistSprite;
            case 35:return GraveDiggerSprite;
            case 36:return ArcheologistSprite;
            case 37:return Architect1Sprite;
            case 38:return Architect2Sprite;
            case 39:return Architect3Sprite;
            case 40:return eeecsManaSprite;
            case 41:return medicalManaSprite;
            case 42: return artsManaSprite;
            case 43: return engineeringManaSprite;
            case 45: return builtEnvironmentManaSprite;
            case 46:return ointmentSprite;
            case 47: return superOintmentSprite;
            case 48:return ultraOintmentSprite;
            case 49:return attackBoosterSprite;
            case 50:return superAttackBoosterSprite;
            case 51:return ultraAttackBoosterSprite;
            case 52:return defenceBoosterSprite;
            case 53:return superDefenceBoosterSprite;
            case 54:return ultraDefenceBoosterSprite;
            case 55:return sleepSprite;
            case 56:return enrageSprite;
            case 57: return curePoisonSprite;
            case 58:return cureSleepSprite;
            case 59:return cureEnrageSprite;
            default:return cardbackBitmap;
        }
    }
}
