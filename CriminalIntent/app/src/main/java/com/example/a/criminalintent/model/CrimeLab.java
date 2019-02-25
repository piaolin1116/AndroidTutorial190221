package com.example.a.criminalintent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a.criminalintent.datadase.CrimeBaseHelper;
import com.example.a.criminalintent.datadase.CrimeCursorWrapper;
import com.example.a.criminalintent.datadase.CrimeDbScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.a.criminalintent.datadase.CrimeDbScheme.CrimeTable;
import static com.example.a.criminalintent.datadase.CrimeDbScheme.CrimeTable.Cols;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private SQLiteDatabase mDatebase;
    private Context mContext;

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null,null);
        while(cursor.moveToNext()){
            crimes.add(cursor.getCrime());
        }
        cursor.close();
        return crimes;
    }
    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrimes(Cols.UUID+"=?",new String[]{id.toString()});
        if(cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        Crime crime = cursor.getCrime();
        cursor.close();
        return crime;
    }

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime){
        //mCrimes.add(crime);
        mDatebase.insert(CrimeTable.NAME, null, getContentValues(crime));
    }
    public void updateCrime(Crime crime){
        ContentValues values = getContentValues(crime);
        mDatebase.update(CrimeTable.NAME, values,Cols.UUID+"=?", new String[]{ crime.getId().toString() });
    }
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatebase.query(CrimeTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new CrimeCursorWrapper(cursor);
    }
    public void deleteCrime(Crime crime){
        mDatebase.delete(CrimeTable.NAME, Cols.UUID+"=?", new String[]{ crime.getId().toString() });
    }

    private CrimeLab(Context context){
        //mCrimes = new ArrayList<>();
        mContext = context;
        mDatebase = new CrimeBaseHelper(mContext).getWritableDatabase();
/*
        for(int i=0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("범죄 #"+i);
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }
*/
    }
    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(Cols.UUID,crime.getId().toString());
        values.put(Cols.TITLE,crime.getTitle());
        values.put(Cols.DATE,crime.getDate().getTime());
        values.put(Cols.SOLVED,crime.isSolved()?1:0);
        return values;
    }
}