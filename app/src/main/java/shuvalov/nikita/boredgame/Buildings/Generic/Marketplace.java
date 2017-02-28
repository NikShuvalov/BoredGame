package shuvalov.nikita.boredgame.Buildings.Generic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import shuvalov.nikita.boredgame.Buildings.Building;
import shuvalov.nikita.boredgame.GameConstants;
import shuvalov.nikita.boredgame.Players.BaseCharacterRace;
import shuvalov.nikita.boredgame.R;

/**
 * Created by NikitaShuvalov on 2/27/17.
 */

public class Marketplace extends Building implements AdapterView.OnItemSelectedListener {
    public static final int MARKETPLACE_HP = 6;
    public static final int[] MARKETPLACE_COST = {4,0,4,0,0};
    private String mSelectedGive, mSelectedGet;

    public Marketplace() {
        super(MARKETPLACE_HP, false, 0, GameConstants.MARKETPLACE_ID, 0);
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.marketplace_name);
    }

    @Override
    public String getDescription(Context context) {
        return context.getString(R.string.marketplace_description);
    }

    @Override
    public boolean useAbility(BaseCharacterRace player, Context context) {
        if(getCounters()>= getLevel()){
            Toast.makeText(context, "No more usages left this turn", Toast.LENGTH_SHORT).show();
            return false;
        }

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_marketplace,null);
        Spinner giveSpinner, recSpinner;
        EditText giveAmount, recAmount;
        giveSpinner = (Spinner)view.findViewById(R.id.trading_spinner);
        giveAmount = (EditText)view.findViewById(R.id.trading_amount);
        recSpinner = (Spinner)view.findViewById(R.id.receiving_spinner);
        recAmount = (EditText)view.findViewById(R.id.receiving_amount);

        ArrayAdapter<CharSequence> giveAdap = ArrayAdapter.createFromResource(context, R.array.resources,R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> receiveAdap = ArrayAdapter.createFromResource(context, R.array.resources, android.R.layout.simple_spinner_item);

        giveAdap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        receiveAdap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        giveSpinner.setAdapter(giveAdap);
        recSpinner.setAdapter(receiveAdap);

        giveSpinner.setOnItemSelectedListener(this);
        recSpinner.setOnItemSelectedListener(this);

        new AlertDialog.Builder(context)
                .setTitle(R.string.marketplace_name)
                .setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();//ToDo: Finish this.
        return true;//ToDo: Hold this hostage in the alertDialog. If the player clicks cancel, return false, if she completes trade, return true.
    }

    @Override
    public int[] getCost() {
        return MARKETPLACE_COST;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("Marketplace", "onItemSelected: "+view.getId());
        switch(adapterView.getId()){
            case R.id.receiving_spinner:
                Log.d("Marketplace", "onItemSelected: Receiving");
                break;
            case R.id.trading_spinner:
                Log.d("Marketplace", "onItemSelected: Trading");
                break;
        }
        //ToDo: Check which adapter is being used, then apply that resource to the appropriate selected blah blah blah.
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Don't do anything then.
    }
}
