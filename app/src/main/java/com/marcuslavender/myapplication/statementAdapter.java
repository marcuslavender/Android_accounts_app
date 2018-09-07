package com.marcuslavender.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by marcus.lavender on 07/09/2018.
 */

public class statementAdapter extends ArrayAdapter<Statements> {
    private List<Statements> statements;
    private Context context;

public statementAdapter(Context context, List<Statements> statements )
{
    super(context, R.layout.showstatement, statements);
    this.statements = statements;
}

@Override
public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.showstatement, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView4);

        //TreeMap<String, String> statementHistoryFromFile = statements.get(0).statements;

        //String dateTime = ShowStatement.aStatements.getStatementsTreeMap().;
        //String balance = statements.get("");

        return rowView;

    }

}
