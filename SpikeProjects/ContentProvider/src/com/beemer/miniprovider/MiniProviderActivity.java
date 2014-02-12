
package com.beemer.miniprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MiniProviderActivity extends Activity {

    private static final String ID = "_id";
    private static final String ITEM = "item";
    private static final String PRECIO = "precio";
    private static final String URI = "content://com.beemer.provider.testnosqlite/data";

    private Cursor cursor = null;
    private ListView listView = null;
    private ContentResolver cr = null;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        cr = getContentResolver();

        try {
            cursor = cr.query(Uri.parse(URI), (new String[] {
                    ID, ITEM, PRECIO
            }), null, null, null);

            // Usando list view adapter
            MyCursorAdapter adapter = new MyCursorAdapter(this, R.layout.row, cursor, new String[] {
                    ITEM, PRECIO
            }, new int[] {
                    R.id.item, R.id.precio
            });
            listView = (ListView) findViewById(R.id.parts_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new myClickListener());

            // Recorriendo el cursor
            int size = 0;
            if (cursor != null) {
                size = cursor.getCount();
            }
            if (size > 0) {
                int[] id = new int[size];
                String[] item = new String[size];
                String[] precio = new String[size];

                int index = 0;

                cursor.moveToFirst();

                int idIdx = cursor.getColumnIndex(ID);
                int itemIdx = cursor.getColumnIndex(ITEM);
                int precioIdx = cursor.getColumnIndex(PRECIO);

                do {
                    id[index] = cursor.getInt(idIdx);
                    item[index] = cursor.getString(itemIdx);
                    precio[index] = cursor.getString(precioIdx);
                    index++;
                } while (cursor.moveToNext());

                TextView tvReport = (TextView) findViewById(R.id.parts_report);
                for (int i = 0; i < size; i++) {
                    tvReport.setText(tvReport.getText().toString() + id[i] + ":   " + item[i] + "    " + precio[i]
                            + "\n");
                }
            }
        } catch (Exception e) {
            Log.e("miniProvider", e.getMessage());
        }
    }

    private class MyCursorAdapter extends SimpleCursorAdapter {

        public MyCursorAdapter(final Context context, final int layout, final Cursor c, final String[] from,
                final int[] to) {
            super(context, layout, c, from, to);
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            if (position % 2 == 0) {
                view.setBackgroundColor(Color.rgb(0xFF, 0xFF, 0xFF));
            } else {
                view.setBackgroundColor(Color.rgb(0xB0, 0xB0, 0xB0));
            }
            return view;
        }

    }

    private class myClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(final AdapterView<?> parent, final View v, final int position, final long id) {
            try {
                Uri uri = ContentUris.withAppendedId(Uri.parse(URI), position);
                cursor = cr.query(uri, (new String[] {
                        ITEM, PRECIO
                }), null, null, null);

                if (cursor == null || cursor.getCount() != 1) {
                    return;
                }

                cursor.moveToFirst();

                int itemIdx = cursor.getColumnIndex(ITEM);
                int precioIdx = cursor.getColumnIndex(PRECIO);

                String item = cursor.getString(itemIdx);
                String precio = cursor.getString(precioIdx);

                TextView tvLine = (TextView) findViewById(R.id.parts_single);
                tvLine.setText(item + "    " + precio);
            } catch (Exception e) {
                Log.e("miniProvider", e.getMessage());
            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
    }

}
