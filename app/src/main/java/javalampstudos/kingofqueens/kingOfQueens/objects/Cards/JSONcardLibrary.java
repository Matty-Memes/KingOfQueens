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
    // load cardback sprite
    private Bitmap cardBackSprite;

    /*Monster Cards*/
    //Engineer Monsters
    private Bitmap Engineer1Sprite;
    private Bitmap Engineer2Sprite;
    private Bitmap Engineer3Sprite;
    private Bitmap Turret1Sprite;
    private Bitmap Turret2Sprite;
    private Bitmap Turret3Sprite;
    private Bitmap AeroSpaceEngineer1Sprite;
    private Bitmap AeroSpaceEngineer2Sprite;

    //EEECS Monsters
    private Bitmap CodeMonkeySprite;
    private Bitmap BananaEngineerSprite;
    private Bitmap SeniorBananaEngineerSprite;
    private Bitmap SQLSealSprite;
    private Bitmap DataAnalystSprite;
    private Bitmap DataAdminSprite;
    private Bitmap ScriptKiddieSprite;
    private Bitmap HackerManSprite;

    //Medic Monsters
    private Bitmap NurseSprite;
    private Bitmap DoctorSprite;
    private Bitmap SurgeonSprite;
    private Bitmap MedicSprite;
    private Bitmap FieldMedicSprite;
    private Bitmap ParaMedicSprite;
    private Bitmap ChemistSprite;
    private Bitmap PharmacistSprite;

    //Social Science Monsters
    private Bitmap JuniorHistorianSprite;
    private Bitmap LegitHistorianSprite;
    private Bitmap TimeTravelerSprite;
    private Bitmap DoodlerSprite;
    private Bitmap SketcherSprite;
    private Bitmap ArtisteSprite;
    private Bitmap TinkererSprite;
    private Bitmap CraftsmanSprite;

    //Built Enviroment Monsters
    private Bitmap RockHunterSprite;
    private Bitmap GeologistSprite;
    private Bitmap GraveDiggerSprite;
    private Bitmap ArcheologistSprite;
    private Bitmap Architect1Sprite;
    private Bitmap Architect2Sprite;
    private Bitmap Architect3Sprite;

    //Social Science Monsters
    private Bitmap Pyschologist1Sprite;
    private Bitmap Pyschologist2Sprite;
    private Bitmap Pyschologist3Sprite;
    private Bitmap Sociologist1Sprite;
    private Bitmap Sociologist2Sprite;
    private Bitmap SocialWorker1Sprite;
    private Bitmap SocialWorker2Sprite;
    private Bitmap SocialWorker3Sprite;

    // ManaSprites
    private Bitmap socialScienceManaSprite;
    private Bitmap medicalManaSprite;
    private Bitmap artsManaSprite;
    private Bitmap eeecsManaSprite;
    private Bitmap engineeringManaSprite;
    private Bitmap builtEnvironmentManaSprite;

    //Support cards
    private Bitmap ointmentSprite;
    private Bitmap superOintmentSprite;

    // Actual Card Objects

    // Engineering
    private MonsterCard Engineer1;
    private MonsterCard Engineer2;
    private MonsterCard Engineer3;
    private MonsterCard Turret1;
    private MonsterCard Turret2;
    private MonsterCard Turret3;
    private MonsterCard AeroSpaceEngineer1;
    private MonsterCard AeroSpaceEngineer2;

    // Mana Card Objects

    private ManaCard socialScienceMana;
    private ManaCard medicalMana;
    private ManaCard artsMana;
    private ManaCard eeecsMana;
    private ManaCard engineeringMana;
    private ManaCard builtEnvironmentMana;

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

    //bool for backup method
    private boolean generated = true;

    private void generateCards() throws IOException
    {
        try
        {
            InputStream fileIn = new FileInputStream("\\txt\\cardLibrary.txt");
            readJsonStream(fileIn);

            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print("Exception: CardLibrary wasn't read " + e);
            generated = false;
        }
    }

    //Matt: This is a comment that Andrew left for himself. just ignore for now.
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
    //This is a backup method in case the parser breaks
    private void initializeMonsters ()

    {

        // Temporary variable to use for all hash maps
        HashMap<ManaTypes,Integer> requiredMana = new HashMap<ManaTypes,Integer>();
        requiredMana.put(ManaTypes.BUILT_ENVIRONMENT_MANA,5);


        MonsterCard Engineer1 = new MonsterCard(0, 0, 90, 120, Engineer1Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.UNDERGRAD, 80, 0, 30, 1, requiredMana);
        MonsterCard Engineer2 = new MonsterCard(0, 0, 90, 120, Engineer2Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.GRAD, 120, 0, 50, 1, requiredMana);
        MonsterCard Engineer3 = new MonsterCard(0, 0, 90, 120, Engineer3Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.DOCTRATE, 200, 0, 70, 1, requiredMana);
        MonsterCard Turret1 = new MonsterCard(0, 0, 90, 120, Turret1Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.UNDERGRAD, 50, 10, 30, 2, requiredMana);
        MonsterCard Turret2 = new MonsterCard(0, 0, 90, 120, Turret2Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.GRAD, 70, 15, 50, 2, requiredMana);
        MonsterCard Turret3 = new MonsterCard(0, 0, 90, 120, Turret3Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.DOCTRATE, 100, 25, 70, 2, requiredMana);
        MonsterCard AeroSpaceEngineer1 = new MonsterCard(0, 0, 90, 120, AeroSpaceEngineer1Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.UNDERGRAD, 70, 0, 40, 3, requiredMana);
        MonsterCard AeroSpaceEngineer2 = new MonsterCard(0, 0, 90, 120,AeroSpaceEngineer2Sprite, true, 0, CardSchools.ENGINEERING,
                false, 49, CardLevel.GRAD, 100, 15, 70, 3, requiredMana);

        monsterCards.add(Engineer1);
        monsterCards.add(Engineer2);
        monsterCards.add(Engineer3);
        monsterCards.add(Turret1);
        monsterCards.add(Turret2);
        monsterCards.add(Turret3);
        monsterCards.add(AeroSpaceEngineer1);
        monsterCards.add(AeroSpaceEngineer2);

    }


    private void initializeMana ()

    {

        ManaCard socialScienceMana = new ManaCard(0, 0, 90, 120,socialScienceManaSprite, true, 2,
                ManaTypes.SOCIAL_SCIENCES_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );
        ManaCard medicalMana = new ManaCard(0, 0, 90, 120, medicalManaSprite, true, 2,
                ManaTypes.MEDICS_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );
        ManaCard artsMana = new ManaCard(0, 0, 90, 120, artsManaSprite, true, 2,
                ManaTypes.ARTS_HUMANITIES_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
        ManaCard eeecsMana = new ManaCard(0, 0, 90, 120, eeecsManaSprite, true, 2,
                ManaTypes.EEECS_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
        ManaCard engineeringMana = new ManaCard(0, 0, 90, 120, engineeringManaSprite, true, 2,
                ManaTypes.ENGINEERING_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;
        ManaCard builtEnvironmentMana = new ManaCard(0, 0, 90, 120, builtEnvironmentManaSprite, true, 2,
                ManaTypes.BUILT_ENVIRONMENT_MANA, javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools.SOCIAL_SCIENCES, false, 49 );;

        manaCards.add(socialScienceMana);
        manaCards.add(medicalMana);
        manaCards.add(artsMana);
        manaCards.add(eeecsMana);
        manaCards.add(engineeringMana);
        manaCards.add(builtEnvironmentMana);

    }


    // populate each of the 3 parts of the source deck
    public void loadAssets(GameLoop loop)

    {
        assetManager = loop.fragment.getActivity().getAssets();
        loadSprites();
        try {
            generateCards();
        }
        catch(IOException e)
        {
            System.out.print("Cards were not generated. Exception "+ e );
        }

        //if parser fails use backup methods
        if(!generated)
        {
            initializeMonsters();
            initializeMana();
        }
    }


    //Loads in sprites using the assetManager
    private void loadSprites()

    {


        // load cardback sprite
        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");

        Engineer1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-1.png");
        Engineer2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-2.png");
        Engineer3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-3.png");
        Turret1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-1.png");
        Turret2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-2.png");
        Turret3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-3.png");
        AeroSpaceEngineer1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-1.png");
        AeroSpaceEngineer2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-2.png");

        // I'm commenting these out for now since it's slowing the game down
        // Feel free to put them back in - highlight the relevant lines and press Ctrl /
        //EEECS Monsters
//        CodeMonkeySprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/CodeMonkey.png");
//        BananaEngineerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/BananaEngineer.png");
//        SeniorBananaEngineerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SeniorBananaEngineer.png");
//        SQLSealSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SQLSeal.png");
//        DataAnalystSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAnalyst.png");
//        DataAdminSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAdmin.png");
//        ScriptKiddieSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/ScriptKiddie.png");
//        HackerManSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/HackerMan.png");
//
//        //Medic Monsters
//        NurseSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Monster/Medical/Nurse.png");
//        DoctorSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Doctor.png");
//        SurgeonSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Surgeon.png");
//        MedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Medic.png");
//        FieldMedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/FieldMedic.png");
//        ParaMedicSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/ParaMedic.png");
//        ChemistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Chemist.png");
//        PharmacistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Pharmacist.png");
//
//        //Social Science Monsters
//        JuniorHistorianSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/JuniorHistorian.png");
//        LegitHistorianSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/LegitHistorian.png");
//        TimeTravelerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/TimeTraveller.png");
//        DoodlerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Doodler.png");
//        SketcherSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Sketcher.png");
//        ArtisteSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Artiste.png");
//        TinkererSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Tinkerer.png");
//        CraftsmanSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Craftsman.png");
//
//        //Built Enviroment Monsters
//        RockHunterSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/RockHunter.png");
//        GeologistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Geologist.png");
//        GraveDiggerSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/GraveDigger.png");
//        ArcheologistSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Archeologist.png");
//        Architect1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-1.png");
//        Architect2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-2.png");
//        Architect3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-3.png");
//
//        //Social Science Monsters
//        Pyschologist1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-1.png");
//        Pyschologist2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-2.png");
//        Pyschologist3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-3.png");
//        Sociologist1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-1.png");
//        Sociologist2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-2.png");
//        SocialWorker1Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-1.png");
//        SocialWorker2Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-2.png");
//        SocialWorker3Sprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-3.png");

        // ManaSprites
        socialScienceManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/SocialSciencesMana.png");
        medicalManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/MedicalMana.png");
        artsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ArtsMana.png");
        eeecsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
        engineeringManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EngineeringMana.png");
        builtEnvironmentManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/BuiltMana.png");

        //support cards
//        ointmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/Ointment.png");
//        superOintmentSprite = AssetLoader.loadBitmap(assetManager,"img/Cards/Support/SuperOintment.png");

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
            case "Engineer1": return Engineer1Sprite;
            case "Engineer2": return Engineer2Sprite;
            case "Engineer3": return Engineer3Sprite;
            case "Turret1": return Turret1Sprite;
            case "Turret2": return Turret2Sprite;
            case "Turret3": return Turret3Sprite;
            case "AeroSpaceEngineer1":return AeroSpaceEngineer1Sprite;
            case "AeroSpaceEngineer2":return AeroSpaceEngineer2Sprite;
            case "CodeMonkey": return CodeMonkeySprite;
            case "BananaEngineer": return BananaEngineerSprite;
            case "SeniorBananaEngineer":return SeniorBananaEngineerSprite;
            case "SQLSeal":return SQLSealSprite;
            case "DataAnalyst":return DataAnalystSprite;
            case "DataAdmin":return DataAdminSprite;
            case "ScriptKiddie":return ScriptKiddieSprite;
            case "HackerMan":return HackerManSprite;
            case "Nurse":return NurseSprite;
            case "Doctor":return DoctorSprite;
            case "Surgeon":return SurgeonSprite;
            case "Medic": return MedicSprite;
            case "FieldMedic":return FieldMedicSprite;
            case "ParaMedic": return ParaMedicSprite;
            case "Chemist" : return ChemistSprite;
            case "Pharmacist" : return PharmacistSprite;
            case "JuniorHistorian":return JuniorHistorianSprite;
            case "LegitHistorian":return LegitHistorianSprite;
            case "TimeTraveler":return TimeTravelerSprite;
            case "Doodler":return DoodlerSprite;
            case "Sketcher":return SketcherSprite;
            case "Artiste":return ArtisteSprite;
            case "Tinkerer":return TinkererSprite;
            case "Craftsman":return CraftsmanSprite;
            case "RockHunter":return RockHunterSprite;
            case "Geologist":return GeologistSprite;
            case "GraveDigger":return GraveDiggerSprite;
            case "Archeologist":return ArcheologistSprite;
            case "Architect1":return Architect1Sprite;
            case "Architect2":return Architect2Sprite;
            case "Architect3":return Architect3Sprite;
            case "Pyschologist1":return Pyschologist1Sprite;
            case "Pyschologist2":return Pyschologist2Sprite;
            case "Pyschologist3":return Pyschologist3Sprite;
            case "Sociologist1":return Sociologist1Sprite;
            case "Sociologist2":return Sociologist2Sprite;
            case "SocialWorker1":return SocialWorker1Sprite;
            case "SocialWorker2":return SocialWorker2Sprite;
            case "SocialWorker3":return SocialWorker3Sprite;
            case "EEECS":return eeecsManaSprite;
            case "MEDICS":return medicalManaSprite;
            case "ARTS_HUMANITIES": return artsManaSprite;
            case "ENGINEERING": return engineeringManaSprite;
            case "SOCIAL_SCIENCES": return socialScienceManaSprite;
            case "BUILT_ENVIORNMENT": return builtEnvironmentManaSprite;
            case "Ointment":return ointmentSprite;
            case "SuperOintment": return superOintmentSprite;
            default: return cardBackSprite;

        }
    }
}
