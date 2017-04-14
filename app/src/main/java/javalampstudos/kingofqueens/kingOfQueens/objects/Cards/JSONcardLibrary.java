package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;
import android.content.SyncStatusObserver;
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
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.MonsterCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.ManaCard;
import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.SupportCard;

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
            if (name.equals("name"))
            {
                cardName = reader.nextString();
            }
            else if(name.equals("cardSchool"))
            {
                cardSchool = (CardSchools.valueOf(reader.nextString()));
            }
            else if(name.equals("level"))
            {
                level = (CardLevel.valueOf(reader.nextString()));
            }
            else if(name.equals("health"))
            {
                health = reader.nextInt();
            }
            else if(name.equals("defence"))
            {
                defence = reader.nextInt();
            }
            else if(name.equals("attackValue"))
            {
                attackValue = reader.nextInt();
            }
            else if(name.equals("evolutionID"))
            {
                evolutionID = reader.nextInt();
            }
            else if(name.equals("attackManaRequirement"))
            {
                attackManaRequirements = readManaRequirements(reader);
            }
            else
            {
                reader.skipValue();
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

    //EEECS Monsters
    private Bitmap CodeMonkey = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/CodeMonkey.png");
    private Bitmap BananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/BananaEngineer.png");
    private Bitmap SeniorBananaEngineer = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/EEECS/SeniorBananaEngineer.png");

    //Medic Monsters

    private Bitmap Nurse = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Nurse-1.png");
    private Bitmap Doctor = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Doctor.png");
    private Bitmap Surgeon = AssetLoader.loadBitmap(assetManager,"img/Cards/Monster/Medical/Surgeon.png");


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
            default: return cardBackSprite;

        }
    }
}
