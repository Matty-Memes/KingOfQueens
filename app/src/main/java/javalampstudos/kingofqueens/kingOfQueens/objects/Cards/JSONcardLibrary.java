package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javalampstudos.kingofqueens.kingOfQueens.Menu.PauseFragment;
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

/*
 * Created by Matt on 13/04/2017.
 */


//This class reads in a json file containing all the cards in the game, and parses it as an Array List
public class JSONcardLibrary
{
    //Array Lists to store each type of card
    private List<MonsterCard> monsterCards = new ArrayList<MonsterCard>();
    private List<ManaCard> manaCards = new ArrayList<ManaCard>();
    private List<SupportCard> supportCards = new ArrayList<SupportCard>();

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


    //opens the JSON stream
    public List<MonsterCard> readJsonStream(InputStream in) throws IOException
    {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try
        {
            return readMonsterCardArray(reader);
        }
        finally
        {
            reader.close();
        }
    }

    private List<MonsterCard> readMonsterCardArray(JsonReader reader) throws IOException
    {

        reader.beginArray();
        while (reader.hasNext())
        {
            monsterCards.add(readMonsterCard(reader));
        }
        reader.endArray();
        return monsterCards;
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
            String name = reader.nextName();
            if(name.equals("manatype"))
            {
                manatype = ManaTypes.valueOf(reader.nextString());
            }
            else if(name.equals("value"))
            {
                manaAmount = reader.nextInt();
            }
            attackManaRequirements.put(manatype,manaAmount);
        }
        return attackManaRequirements;
    }

    private List<ManaCard> readManaCardArray(JsonReader reader) throws IOException
    {

        reader.beginArray();
        while (reader.hasNext())
        {
            manaCards.add(readManaCard(reader));
        }
        reader.endArray();
        return manaCards;
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


    /*Bitmap library */
    //All card bitmaps and bitmap related functions
    private AssetManager assetManager;

    // load cardback sprite
    private Bitmap cardBackSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Cardback.png");

    /*Monster Cards*/
    //Engineer Monsters
    private Bitmap Engineer1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-1.png");
    private Bitmap Engineer2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-2.png");
    private Bitmap Engineer3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Engineer-3.png");
    private Bitmap Turret1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-1.png");
    private Bitmap Turret2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-2.png");
    private Bitmap Turret3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/Turret-3.png");
    private Bitmap AeroSpaceEngineer1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-1");
    private Bitmap AeroSpaceEngineer2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Engineering/AeroSpaceEngineer-2");

    //EEECS Monsters
    private Bitmap CodeMonkey = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/CodeMonkey.png");
    private Bitmap BananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/BananaEngineer.png");
    private Bitmap SeniorBananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SeniorBananaEngineer.png");
    private Bitmap SQLSeal = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SQLSeal.png");
    private Bitmap DataAnalyst = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAnalyst.png");
    private Bitmap DataAdmin = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/DataAdmin.png");
    private Bitmap ScriptKiddie = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/ScriptKiddie.png");
    private Bitmap HackerMan = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/HackerMan.png");

    //Medic Monsters
    private Bitmap Nurse = AssetLoader.loadBitmap(assetManager, "img/Cards/Monster/Medical/Nurse.png");
    private Bitmap Doctor = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Doctor.png");
    private Bitmap Surgeon = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Surgeon.png");
    private Bitmap Medic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Medic.png");
    private Bitmap FieldMedic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/FieldMedic.png");
    private Bitmap ParaMedic = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/ParaMedic.png");
    private Bitmap Chemist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Chemist.png");
    private Bitmap Pharmacist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Pharmacist.png");

    //Social Science Monsters
    private Bitmap JuniorHistorian = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/JuniorHistorian.png");
    private Bitmap LegitHistorian = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/LegitHistorian.png");
    private Bitmap TimeTraveler = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/TimeTraveler.png");
    private Bitmap Doodler = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Doodler.png");
    private Bitmap Sketcher = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Sketcher.png");
    private Bitmap Artiste = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Artiste.png");
    private Bitmap Tinkerer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Tinkerer.png");
    private Bitmap Craftsman = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Arts/Craftsman.png");

    //Built Enviroment Monsters
    private Bitmap RockHunter = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/RockHunter.png");
    private Bitmap Geologist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Geologist.png");
    private Bitmap GraveDigger = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/GraveDigger.png");
    private Bitmap Archeologist = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Archeologist.png");
    private Bitmap Architect1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-1.png");
    private Bitmap Architect2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-2.png");
    private Bitmap Architect3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/BuildEnvi/Architect-3.png");

    //Social Science Monsters
    private Bitmap Pyschologist1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-1.png");
    private Bitmap Pyschologist2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-2.png");
    private Bitmap Pyschologist3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Pyschologist-3.png");
    private Bitmap Sociologist1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-1.png");
    private Bitmap Sociologist2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/Sociologist-2.png");
    private Bitmap SocialWorker1 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-1.png");
    private Bitmap SocialWorker2 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-2.png");
    private Bitmap SocialWorker3 = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/SocialSci/SocialWorker-3.png");

    // ManaSprites
    private Bitmap socialScienceSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/SocialSciencesMana.png");
    private Bitmap medicalManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/MedicalMana.png");
    private Bitmap artsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/ArtsMana.png");
    private Bitmap eeecsManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
    private Bitmap engineeringManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/EEECSMana.png");
    private Bitmap builtEnvironmentManaSprite = AssetLoader.loadBitmap(assetManager, "img/Cards/Mana/BuiltMana.png");

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
            default: return cardBackSprite;

        }
    }
}
