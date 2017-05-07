package javalampstudos.kingofqueens.kingOfQueens.util;

import javalampstudos.kingofqueens.kingOfQueens.objects.Cards.CardSchools;

/**
 * Created by Andrew on 07/05/2017.
 */

public class ManaTotals

{
    public int engTotal;
    public int artsTotal;
    public int builtTotal;
    public int eeecsTotal;
    public int medicTotal;

    public int totalToReturn;

    // Andrew - 40083349
    public ManaTotals ()

    {

    }

    // Andrew - 40083349
    public void incrementTotal (CardSchools cardSchool)

    {
        switch (cardSchool)

        {
            case ARTS_HUMANITIES:
                artsTotal++;
                break;
            case ENGINEERING:
                engTotal++;
                break;
            case BUILT_ENVIORNMENT:
                builtTotal++;
                break;
            case EEECS:
                eeecsTotal++;
                break;
            case MEDICS:
                medicTotal++;
                break;
        }

    }

    // Andrew - 40083349
    public int returnTotal (CardSchools cardSchool)

    {
        switch (cardSchool)

        {
            case ARTS_HUMANITIES:
                totalToReturn = artsTotal;
                break;
            case ENGINEERING:
                totalToReturn = engTotal;
                break;
            case BUILT_ENVIORNMENT:
                totalToReturn = builtTotal;
                break;
            case EEECS:
                totalToReturn = eeecsTotal;
                break;
            case MEDICS:
                totalToReturn = medicTotal;
                break;
        }

        System.out.println(totalToReturn);
        return totalToReturn;

    }



}
