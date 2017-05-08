package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;
import javalampstudos.kingofqueens.GameLoop;
/*
 * Created by Matt on 13/04/2017.
 */
//This class reads in a json file containing all the cards in the game, and parses it as an Array List
public class JSONcardLibrary
{
    //Declare all Bitmaps for loading during runtime
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
//    private Bitmap Pyschologist1Sprite;
//    private Bitmap Pyschologist2Sprite;
//    private Bitmap Pyschologist3Sprite;
//    private Bitmap Sociologist1Sprite;
//    private Bitmap Sociologist2Sprite;
//    private Bitmap SocialWorker1Sprite;
//    private Bitmap SocialWorker2Sprite;
//    private Bitmap SocialWorker3Sprite;
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
    private Bitmap ultraOintmentSprite;
    private Bitmap attackBoosterSprite;
    private Bitmap superAttackBoosterSprite;
    private Bitmap ultraAttackBoosterSprite;
    private Bitmap defenceBoosterSprite;
    private Bitmap superDefenceBoosterSprite;
    private Bitmap ultraDefenceBoosterSprite;
    private Bitmap sleepSprite;
    private Bitmap enrageSprite;
    private Bitmap curePoisonSprite;
    private Bitmap cureSleepSprite;
    private Bitmap cureEnrageSprite;
    // Store each card type
    public List<MonsterCard> monsterCards = new ArrayList<MonsterCard>();
    public List<ManaCard> manaCards = new ArrayList<ManaCard>();
    public List<SupportCard> supportCards = new ArrayList<SupportCard>();

    //getters
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
    private int id = 0 ;
    private int pointerID=49 ;
    private int x = 20;
    private int y = 350 ;
    private int targetX = 0;
    private int width = 90 ;
    private int height = 120 ;
    private boolean player = false ;
    private boolean destroyed = false ;
    private AssetManager assetManager;

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

    }

    public JSONcardLibrary ()

    {


    }

    //Loads in sprites using the assetManager
    private void loadSprites()
    {
        // load cardback sprite
        cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");
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
        //Social Science Monsters
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


    }
    private void generateCards() throws IOException
    {
        try
        {
            InputStream fileIn = assetManager.open("txt/cardLibraryManaCounter.json");
            readJsonStream(fileIn);
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print("Exception: CardLibrary wasn't read " + e);
        }
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
    //populates the MonsterCard array
    private List<MonsterCard> readMonsterCardArray(JsonReader reader) throws IOException
    {
        reader.beginObject();
        if (reader.nextName().equals("monsterCards")) {
            reader.beginArray();
            while (reader.hasNext()) {
                monsterCards.add(readMonsterCard(reader));
                System.out.println("MonsterCard added to library successfully");
            }
            reader.endArray();
            return monsterCards;
        }
        else
        {
            return null;
        }
    }
    //Returns a monsterCard
    private MonsterCard readMonsterCard(JsonReader reader) throws IOException
    {
        String cardName = null;
        int health = -1;
        int defence =-1;
        int attackValue = -1;
        int evolutionID = -1;
        CardSchools cardSchool = null;
        CardLevel level = null;
        int attackManaRequirements = -1;
       // HashMap<ManaTypes,Integer> attackManaRequirements = null; //07/05/2017 We needed to swap out hashmaps at the very last minute
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
                case "attackManaRequirement":attackManaRequirements = reader.nextInt(); //attackManaRequirements= readManaRequirements(reader) //We had to swap out hashmaps last minute. The method works though
                    break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        Bitmap sprite = assignSprite(cardName);
        id++;
        return new MonsterCard(x,y,width,height,sprite,player,id,cardSchool,destroyed,pointerID,targetX,level,
                health,defence,attackValue,evolutionID,attackManaRequirements);
    }
    //Returns the manaRequirements HashMap
    private HashMap<ManaTypes,Integer> readManaRequirements(JsonReader reader) throws IOException
    {
        ManaTypes manatype = null;
        int manaAmount = -1;
        HashMap<ManaTypes,Integer> attackManaRequirements = new HashMap<ManaTypes,Integer>();
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
            }
            attackManaRequirements.put(manatype, manaAmount);
            reader.endObject();
        }
        reader.endArray();
        return attackManaRequirements;
    }
    //Populates the manaCards array
    private List<ManaCard> readManaCardArray(JsonReader reader) throws IOException
    {
        if (reader.nextName().equals("manaCards"))
        {
            reader.beginArray();
            while (reader.hasNext())
            {
                manaCards.add(readManaCard(reader));
            }
            reader.endArray();
            return manaCards;
        }
        else
            return null;
    }
    //returns a manaCard
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
        reader.endObject();
        id++;
        Bitmap sprite = assignSprite(cardName);
        return new ManaCard(x,y,width,height,sprite,player,id,manaType,cardSchool,destroyed,pointerID,targetX);
    }
    //Populates the supportCard Array
    private List<SupportCard> readSupportCardArray(JsonReader reader) throws IOException
    {
        if (reader.nextName().equals("supportCards")) {
            reader.beginArray();
            while (reader.hasNext())
            {
                supportCards.add(readSupportCard(reader));
            }
            reader.endArray();
            return supportCards;
        }
        else
            return null;
    }
    //Returns a supportCard
    private SupportCard readSupportCard(JsonReader reader) throws IOException
    {
        BuffType buffType = null;
        int buffValue = -1;
        CardSchools cardSchool = CardSchools.ENGINEERING;//This really doesn't matter anymore
        StatusEffect statusEffect = StatusEffect.None;
        String cardName = null;

        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            switch(name)
            {
                case "name": cardName = reader.nextString();
                    break;
                case "buffType": buffType = BuffType.valueOf(reader.nextString());
                    break;
                case "buffValue":buffValue = reader.nextInt();
                    break;
                case "statusEffect":statusEffect = StatusEffect.valueOf(reader.nextString());
                    break;
                default: reader.skipValue();
            }
        }
        reader.endObject();
        id++;
        Bitmap sprite = assignSprite(cardName);
        return new SupportCard(x,y,width,height,sprite,player,id,cardSchool,destroyed,pointerID,targetX,buffType,
                buffValue,statusEffect);
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
//            case "Psychologist1":return Pyschologist1Sprite;
//            case "Psychologist2":return Pyschologist2Sprite;
//            case "Psychologist3":return Pyschologist3Sprite;
//            case "Sociologist1":return Sociologist1Sprite;
//            case "Sociologist2":return Sociologist2Sprite;
//            case "SocialWorker1":return SocialWorker1Sprite;
//            case "SocialWorker2":return SocialWorker2Sprite;
//            case "SocialWorker3":return SocialWorker3Sprite;
            case "EEECS":return eeecsManaSprite;
            case "MEDICS":return medicalManaSprite;
            case "ARTS_HUMANITIES": return artsManaSprite;
            case "ENGINEERING": return engineeringManaSprite;
            case "SOCIAL_SCIENCES": return socialScienceManaSprite;
            case "BUILT_ENVIORNMENT": return builtEnvironmentManaSprite;
            case "Ointment":return ointmentSprite;
            case "SuperOintment": return superOintmentSprite;
            case "UltraOintment":return ultraOintmentSprite;
            case "AttackBooster":return attackBoosterSprite;
            case "SuperAttackBooster":return superAttackBoosterSprite;
            case "UltraAttackBooster":return ultraAttackBoosterSprite;
            case "DefenceBooster":return defenceBoosterSprite;
            case "SuperDefenceBooster":return superDefenceBoosterSprite;
            case "UltraDefenceBooster":return ultraDefenceBoosterSprite;
            case "Sleep":return sleepSprite;
            case "Enrage":return enrageSprite;
            case "CurePoison": return curePoisonSprite;
            case "CureSleep":return cureSleepSprite;
            case "CureEnrage":return cureEnrageSprite;
            default: return cardBackSprite;
        }
    }

}