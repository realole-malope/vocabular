package com.rom.works.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.ListFragment;

import com.rom.works.R;
import com.rom.works.entity.Definition;
import com.rom.works.listener.DefinitionEventListener;
import com.rom.works.service.UserDefinitionDatabaseHelper;

import java.util.List;

import static com.rom.works.MainActivity.definitionEventListener;

public class HomeFragment extends ListFragment implements AdapterView.OnItemClickListener, DefinitionEventListener {

    private static final String TAG = HomeFragment.class.getName();
    private ArrayAdapter<Definition> adapter;
    private List<Definition> definitions;
    private UserDefinitionDatabaseHelper definitionDatabaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        definitionDatabaseHelper = UserDefinitionDatabaseHelper.getInstance();
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        definitionEventListener = this;
        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        definitions = definitionDatabaseHelper.getAllContacts();
        adapter = new HomeDefinitionAdapter(definitions, this.getContext());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final Definition definition = definitions.get(position);
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete " + definition.getWord());
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                definitions.remove(position);
                String message = definition.getWord() + " removed";
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                definitionDatabaseHelper.deleteDefinition(definition);
            }
        });
        adb.show();
    }

    @Override
    public void onAdd(@NonNull Definition definition) {
        definitions.add(definition);
        adapter.notifyDataSetChanged();
    }
}