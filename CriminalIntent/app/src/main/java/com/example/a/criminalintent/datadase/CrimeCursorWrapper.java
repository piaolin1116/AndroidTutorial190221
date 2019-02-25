package com.example.a.criminalintent.datadase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a.criminalintent.model.Crime;

import static com.example.a.criminalintent.datadase.CrimeDbScheme.CrimeTable.Cols;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
        String uuid = getString(getColumnIndex(Cols.UUID));
        String title = getString(getColumnIndex(Cols.TITLE));
        long date = getLong(getColumnIndex(Cols.DATE));
        int isSolved = getInt(getColumnIndex(Cols.SOLVED));
        String suspect = getString(getColumnIndex(Cols.SUSPECT));

        return new Crime(uuid, title, date, isSolved, suspect);
    }
}
