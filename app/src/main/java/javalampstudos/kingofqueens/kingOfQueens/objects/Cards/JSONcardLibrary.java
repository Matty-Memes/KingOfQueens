package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.util.JsonReader;
import android.content.res.AssetManager;
import android.content.Context;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javalampstudos.kingofqueens.kingOfQueens.Menu.PauseFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.GameLoop;

/*
 * Created by Matt on 13/04/2017.
 */


//This class reads in a json file containing all the cards in the game, and parses it as an Array List
public class JSONcardLibrary
{
    /*Bitmap library */
    //cardback sprite
    private Bitmap cardBackSprite;

    /*Monster Cards*/
    //Engineer Monsters
    private Bitmap Engineer1;
    private Bitmap Engineer2;
    private Bitmap Engineer3;
    private Bitmap Turret1;
    private Bitmap Turret2;
    private Bitmap Turret3;
    private Bitmap AeroSpaceEngineer1;
    private Bitmap AeroSpaceEngineer2;

    //EEECS Monsters
    private Bitmap CodeMonkey;
    private Bitmap BananaEngineer;
    private Bitmap SeniorBananaEngineer;
    private Bitmap SQLSeal;
    private Bitmap DataAnalyst;
    private Bitmap DataAdmin;
    private Bitmap ScriptKiddie;
    private Bitmap HackerMan;

    //Medic Monsters
    private Bitmap Nurse;
    private Bitmap Doctor;
    private Bitmap Surgeon;
    private Bitmap Medic;
    private Bitmap FieldMedic;
    private Bitmap ParaMedic;
    private Bitmap Chemist;
    private Bitmap Pharmacist;

    //Social Science Monsters
    private Bitmap JuniorHistorian;
    private Bitmap LegitHistorian;
    private Bitmap TimeTraveler;
    private Bitmap Doodler;
    private Bitmap Sketcher;
    private Bitmap Artiste;
    private Bitmap Tinkerer;
    private Bitmap Craftsman;

    //Built Enviroment Monsters
    private Bitmap RockHunter;
    private Bitmap Geologist;
    private Bitmap GraveDigger;
    private Bitmap Archeologist;
    private Bitmap Architect1;
    private Bitmap Architect2;
    private Bitmap Architect3;

    //Social Science Monsters
    private Bitmap Pyschologist1;
    private Bitmap Pyschologist2;
    private Bitmap Pyschologist3;
    private Bitmap Sociologist1;
    private Bitmap Sociologist2;
    private Bitmap SocialWorker1;
    private Bitmap SocialWorker2;
    private Bitmap SocialWorker3;

    // ManaSprites
    private Bitmap socialScienceSprite;
    private Bitmap medicalManaSprite;
    private Bitmap artsManaSprite;
    private Bitmap eeecsManaSprite;
    private Bitmap engineeringManaSprite;
    private Bitmap builtEnvironmentManaSprite;

    //Support Sprites
    private Bitmap ointmentSprite;
    private Bitmap superOintmentSprite;

    // Store each card type
    // Need getters and setters
    public List<MonsterCard> monsterCards = new ArrayList<MonsterCard>();
    public List<ManaCard> manaCards = new ArrayList<ManaCard>();
    public List<SupportCard> supportCards = new ArrayList<SupportCard>();

    public List<MonsterCard> getMonsterCards()
    {
        return monsterCards;
    }

    public List<ManaCard> getManaCards()
    {
        return manaCards;
    }

    public List<SupportCard> getSupportCards()
    {
        return supportCards;
    }

    /*Card details for all cards*/
    //keeps track of the current cardID
    private int id = 0;
    private int pointerID=49;
    private int x = 20;
    private int y = 350;
    private int width = 90;
    private int height = 120;
    private boolean player = false;
    private boolean destroyed = false;

    private AssetManager assetManager;

    private void generateCards() throws IOException
    {
        try
        {
            InputStream fileIn = new FileInputStream("txt/cardLibrary");
            readJsonStream(fileIn);

            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print("Exception: CardLibrary wasn't read " + e);
        }
    }

    // EQUIVALENT CLASS FOR SFX

    // load SFX in here

    //NB - Volume is hard coded currently but it should be selectable by the user

    // test = AssetLoader.loadSoundpool(assetManager, "SFX/EnemyDeathNoise.ogg");

    /*

    // You have to use a certain API for this to work.
    // Might need a way to check for this.
    try {
    test = new SoundFX(fragment.getActivity(), R.raw.buttonpush);
} catch (IOException e) {
    Log.e("DialFX", "Dial FX could not be loaded");
}

*/
    // Make sure all the monster cards are intialized
//    private void initializeMonsters ()
//
//    {
//
//        // Temporary variable to use for all hash maps
//        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
//        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);
//
//
//        Engineer1 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.UNDERGRAD, 80, 0, 30, 1, requiredMana);
//        Engineer2 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.GRAD, 120, 0, 50, 1, requiredMana);
//        Engineer3 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.DOCTRATE, 200, 0, 70, 1, requiredMana);
//        Turret1 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.UNDERGRAD, 50, 10, 30, 2, requiredMana);
//        Turret2 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.GRAD, 70, 15, 50, 2, requiredMana);
//        Turret3 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.DOCTRATE, 100, 25, 70, 2, requiredMana);
//        AeroSpaceEngineer1 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.UNDERGRAD, 70, 0, 40, 3, requiredMana);
//        AeroSpaceEngineer2 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
//                false, 49, CardLevel.GRAD, 100, 15, 70, 3, requiredMana);
//
//        monsterCards.add(Engineer1);
//        monsterCards.add(Engineer2);
//        monsterCards.add(Engineer3);
//        monsterCards.add(Turret1);
//        monsterCards.add(Turret2);
//        monsterCards.add(Turret3);
//        monsterCards.add(AeroSpaceEngineer1);
//        monsterCards.add(AeroSpaceEngineer2);
//
//    }
//
//    private void initializeMana ()
//
//    {
//        // Syntax is wrong here
//        socialScienceMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.SOCIAL_SCIENCES_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );
//        medicalMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.MEDICS_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );
//        artsMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.ARTS_HUMANITIES_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
//        eeecsMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.EEECS_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
//        engineeringMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.ENGINEERING_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
//        builtEnvironmentMana = new ManaCard(0, 0, 90, 120, socialScienceManaSprite, true, 2,
//                ManaTypes.BUILT_ENVIRONMENT_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
//
//        manaCards.add(socialScienceMana);
//        manaCards.add(medicalMana);
//        manaCards.add(artsMana);
//        manaCards.add(eeecsMana);
//        manaCards.add(engineeringMana);
//        manaCards.add(builtEnvironmentMana);
//
//    }


    // populate each of the 3 parts of the source deck
    public JSONcardLibrary (GameLoop loop)

    {
        try {

            assetManager = loop.fragment.getActivity().getAssets();
            loadSprites();
//            initializeMonsters();
//            initializeMana();
            generateCards();

        }

        catch (Exception ex)

        {
            ex.printStackTrace();

        }


    }

    //Loads in sprites using the assetManager
    private void loadSprites()

    {


        // load cardback sprite
        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");

       /*Monster Cards*/
       //Engineer Monsters
        Engineer1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-1.png");
        Engineer2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-2.png");
        Engineer3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-3.png");
        Turret1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-1.png");
        Turret2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-2.png");
        Turret3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-3.png");
        AeroSpaceEngineer1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-1.png");
        AeroSpaceEngineer2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-2.png");

        //EEECS Monsters
        CodeMonkey = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/CodeMonkey.png");
        BananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/BananaEngineer.png");
        SeniorBananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SeniorBananaEngineer.png");
        SQLSeal = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SQLSeal.png");
        DataAnalyst = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAnalyst.png");
        DataAdmin = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAdmin.png");
        ScriptKiddie = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/ScriptKiddie.png");
        HackerMan = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/HackerMan.png");

        //Medic Monsters
        Nurse = AssetLoader.loadBitmap(assetManager, "img/Cards/Monster/Medical/Nurse.png");
        Doctor = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Doctor.png");
        Surgeon = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Surgeon.png");
        Medic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Medic.png");
        FieldMedic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/FieldMedic.png");
        ParaMedic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/ParaMedic.png");
        Chemist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Chemist.png");
        Pharmacist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Pharmacist.png");

        //Social Science Monsters
        JuniorHistorian = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/JuniorHistorian.png");
        LegitHistorian = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/LegitHistorian.png");
        TimeTraveler = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/TimeTraveller.png");
        Doodler = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Doodler.png");
        Sketcher = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Sketcher.png");
        Artiste = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Artiste.png");
        Tinkerer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Tinkerer.png");
        Craftsman = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Craftsman.png");

        //Built Enviroment Monsters
        RockHunter = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/RockHunter.png");
        Geologist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Geologist.png");
        GraveDigger = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/GraveDigger.png");
        Archeologist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Archeologist.png");
        Architect1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-1.png");
        Architect2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-2.png");
        Architect3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-3.png");

        //Social Science Monsters
        Pyschologist1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-1.png");
        Pyschologist2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-2.png");
        Pyschologist3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-3.png");
        Sociologist1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-1.png");
        Sociologist2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-2.png");
        SocialWorker1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-1.png");
        SocialWorker2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-2.png");
        SocialWorker3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-3.png");

        // ManaSprites
        socialScienceSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/SocialSciencesMana.png");
        medicalManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/MedicalMana.png");
        artsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ArtsMana.png");
        eeecsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        engineeringManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        builtEnvironmentManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/BuiltMana.png");

        //support cards
        ointmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/Ointment.png");
        superOintmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/SuperOintment.png");

    }



    //opens the JSON stream
    private void readJsonStream(InputStream in) throws IOException
    {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try
        {
            readMonsterCardArray(reader);
            readManaCardArray(reader);
            readSupportCardArray(reader);
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println("IO Exception " + e);
        }
    }

    private List<MonsterCard> readMonsterCardArray(JsonReader reader) throws IOException
    {

        reader.beginArray();
        if (reader.nextName().equals("monsterCards")) {
            while (reader.hasNext()) {
                monsterCards.add(readMonsterCard(reader));
            }
            reader.endArray();
            return monsterCards;
        }
        else
        {
            return null;
        }
    }
    private MonsterCard readMonsterCard(JsonReader reader) throws IOException
    {
        String cardName = null;
        int health = -1;
        int defence =-1;
        int attackValue = -1;
        int evolutionID = -1;
        CardSchools cardSchool = null;
        CardLevel level = null;
        HashMap<ManaTypes,Integer> attackManaRequirements = null;

        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            switch(name)
            {
                case "name":cardName = reader.nextString();
                    break;
                case "cardSchool":cardSchool = (CardSchools.valueOf(reader.nextString()));
                    break;
                case "level":level = (CardLevel.valueOf(reader.nextString()));
                    break;
                case "health":health = reader.nextInt();
                    break;
                case "defence":defence = reader.nextInt();
                    break;
                case "attackValue":attackValue = reader.nextInt();
                    break;
                case "evolutionID":evolutionID = reader.nextInt();
                    break;
                case "attackManaRequirement":attackManaRequirements = readManaRequirements(reader);
                    break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        Bitmap sprite = assignSprite(cardName);
        id++;

        return new MonsterCard(x,y,width,height,sprite,player,id,cardSchool,destroyed,pointerID,level,
                health,defence,attackValue,evolutionID,attackManaRequirements);
    }
    private HashMap<ManaTypes,Integer> readManaRequirements(JsonReader reader) throws IOException
    {
        ManaTypes manatype = null;
        int manaAmount = -1;
        HashMap<ManaTypes,Integer> attackManaRequirements = null;

        reader.beginArray();
        while (reader.hasNext())
        {
            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("manatype")) {
                    manatype = ManaTypes.valueOf(reader.nextString());
                } else if (name.equals("value")) {
                    manaAmount = reader.nextInt();
                }
                attackManaRequirements.put(manatype, manaAmount);
            }
            reader.endObject();
        }
        reader.endArray();
        return attackManaRequirements;
    }

    private List<ManaCard> readManaCardArray(JsonReader reader) throws IOException
    {
        reader.beginArray();
        if (reader.nextName().equals("manaCards"))
        {
            while (reader.hasNext())
            {
                manaCards.add(readManaCard(reader));
            }
            reader.endArray();
            return manaCards;
        }
        else
        {
            return null;
        }
    }
    private ManaCard readManaCard(JsonReader reader) throws IOException
    {
        ManaTypes manaType = null;
        CardSchools cardSchool = null;
        String cardName = null;

        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            switch(name)
            {
                case "manaType": manaType = ManaTypes.valueOf(reader.nextString());
                    break;
                case "CardSchool":cardName = reader.nextString();
                    cardSchool = CardSchools.valueOf(cardName);
                    break;
                default: reader.skipValue();
            }
        }
        id++;
        Bitmap sprite = assignSprite(cardName);
        return new ManaCard(x,y,width,height,sprite,player,id,manaType,cardSchool,destroyed,pointerID);
    }
    private List<SupportCard> readSupportCardArray(JsonReader reader) throws IOException
    {

        reader.beginArray();
        if (reader.nextName().equals("supportCards"))
        while (reader.hasNext())
        {
            supportCards.add(readSupportCard(reader));
        }
        reader.endArray();
        return supportCards;
    }
    private SupportCard readSupportCard(JsonReader reader) throws IOException
    {
        BuffType buffType = null;
        CardSchools cardSchool = null;
        String cardName = null;

        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            switch(name)
            {
                case "name": cardName = reader.nextString();
                case "buffType": buffType = BuffType.valueOf(reader.nextString());
                    break;
                case "CardSchool":cardSchool = CardSchools.valueOf(cardName);
                    break;
                default: reader.skipValue();
            }
        }
        id++;
        Bitmap sprite = assignSprite(cardName);
        return new SupportCard(x,y,width,height,sprite,player,id,cardSchool,destroyed,pointerID,buffType);
    }

    //This method takes in the name of the card and returns the card bitmap
    private Bitmap assignSprite(String name)
    {
        switch (name)
        {
            case "Engineer1": return Engineer1;
            case "Engineer2": return Engineer2;
            case "Engineer3": return Engineer3;
            case "Turret1": return Turret1;
            case "Turret2": return Turret2;
            case "Turret3": return Turret3;
            case "AeroSpaceEngineer1":return AeroSpaceEngineer1;
            case "AeroSpaceEngineer2":return AeroSpaceEngineer2;
            case "CodeMonkey": return CodeMonkey;
            case "BananaEngineer": return BananaEngineer;
            case "SeniorBananaEngineer":return SeniorBananaEngineer;
            case "SQLSeal":return SQLSeal;
            case "DataAnalyst":return DataAnalyst;
            case "DataAdmin":return DataAdmin;
            case "ScriptKiddie":return ScriptKiddie;
            case "HackerMan":return HackerMan;
            case "Nurse":return Nurse;
            case "Doctor":return Doctor;
            case "Surgeon":return Surgeon;
            case "Medic": return Medic;
            case "FieldMedic":return FieldMedic;
            case "ParaMedic": return ParaMedic;
            case "Chemist" : return Chemist;
            case "Pharmacist" : return Pharmacist;
            case "JuniorHistorian":return JuniorHistorian;
            case "LegitHistorian":return LegitHistorian;
            case "TimeTraveler":return TimeTraveler;
            case "Doodler":return Doodler;
            case "Sketcher":return Sketcher;
            case "Artiste":return Artiste;
            case "Tinkerer":return Tinkerer;
            case "Craftsman":return Craftsman;
            case "RockHunter":return RockHunter;
            case "Geologist":return Geologist;
            case "GraveDigger":return GraveDigger;
            case "Archeologist":return Archeologist;
            case "Architect1":return Architect1;
            case "Architect2":return Architect2;
            case "Architect3":return Architect3;
            case "Pyschologist1":return Pyschologist1;
            case "Pyschologist2":return Pyschologist2;
            case "Pyschologist3":return Pyschologist3;
            case "Sociologist1":return Sociologist1;
            case "Sociologist2":return Sociologist2;
            case "SocialWorker1":return SocialWorker1;
            case "SocialWorker2":return SocialWorker2;
            case "SocialWorker3":return SocialWorker3;
            case "EEECS":return eeecsManaSprite;
            case "MEDICS":return medicalManaSprite;
            case "ARTS_HUMANITIES": return artsManaSprite;
            case "ENGINEERING": return engineeringManaSprite;
            case "SOCIAL_SCIENCES": return socialScienceSprite;
            case "BUILT_ENVIORNMENT": return builtEnvironmentManaSprite;
            case "Ointment":return ointmentSprite;
            case "SuperOintment": return superOintmentSprite;
            default: return cardBackSprite;

        }
    }
}
