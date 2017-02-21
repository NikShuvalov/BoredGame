package shuvalov.nikita.boredgame;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by NikitaShuvalov on 2/7/17.
 */

public class FirebaseDatabaseUtils {

    public static DatabaseReference getServersReference(FirebaseDatabase firebaseDatabase){
        return firebaseDatabase.getReference().child(MainActivity.FIREBASE_SERVER_REF);
    }

    public static DatabaseReference getServerReference(FirebaseDatabase firebaseDatabase, String serverKey){
        return firebaseDatabase.getReference().child(MainActivity.FIREBASE_SERVER_REF).child(serverKey);
    }

    public static DatabaseReference getPlayerOneReference(FirebaseDatabase firebaseDatabase, String serverKey){
        return firebaseDatabase.getReference().child(MainActivity.FIREBASE_SERVER_REF).child(serverKey).child("players").child("0");
    }
    public static DatabaseReference getPlayerTwoReference(FirebaseDatabase firebaseDatabase, String serverKey){
        return firebaseDatabase.getReference().child(MainActivity.FIREBASE_SERVER_REF).child(serverKey).child("players").child("1");
    }
}
