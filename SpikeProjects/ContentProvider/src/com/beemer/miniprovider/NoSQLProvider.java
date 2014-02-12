
package com.beemer.miniprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class NoSQLProvider extends ContentProvider {
    private static final int ALLROWS = 1;
    private static final int ONEROW = 2;
    private static UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        String CONTENT_URI = "com.beemer.provider.testnosqlite";

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_URI, "data", ALLROWS);
        uriMatcher.addURI(CONTENT_URI, "data/#", ONEROW);

        return true;
    }

    @Override
    public String getType(final Uri _uri) {
        switch (uriMatcher.match(_uri)) {
            case ALLROWS:
                return "vnd.android.cursor.dir/vnd.beemer.provider.data";
            case ONEROW:
                return "vnd.android.cursor.item/vnd.beemer.provider.data";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + _uri);
        }
    }

    @Override
    public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs,
            final String sort) {

        final int NUM_ITEMS = 4;
        String[] item = {
                "Cuadro", "Manillar", "Ruedas", "Pedales"
        };
        String[] precio = {
                "100.00", "21.00", "45.50", "32.25"
        };

        final MatrixCursor cursor = new MatrixCursor(new String[] {
                "_id", "item", "precio"
        });

        switch (uriMatcher.match(uri)) {
            case ALLROWS:

                int[] id = new int[NUM_ITEMS];
                for (int i = 0; i < NUM_ITEMS; i++) {
                    id[i] = i;
                    cursor.addRow(new Object[] {
                            Integer.valueOf(id[i]), item[i], precio[i]
                    });
                }

                return cursor;

            case ONEROW:
                int row;
                try {
                    row = Integer.parseInt(uri.getLastPathSegment());
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("URI no soportada: " + uri);
                }
                if (row >= 0 && row < NUM_ITEMS) {
                    cursor.addRow(new Object[] {
                            row, item[row], precio[row]
                    });
                    return cursor;
                }
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);

        }
    }

    @Override
    public int update(final Uri uri, final ContentValues values, final String where, final String[] whereArgs) {
        // Not implemented
        throw new IllegalArgumentException("Read only URI");
    }

    @Override
    public Uri insert(final Uri _uri, final ContentValues _initialValues) {
        // Not implemented
        throw new IllegalArgumentException("Read only URI" + _uri);
    }

    @Override
    public int delete(final Uri uri, final String where, final String[] whereArgs) {
        // Not implemented
        throw new IllegalArgumentException("Read only URI" + uri);
    }
}
