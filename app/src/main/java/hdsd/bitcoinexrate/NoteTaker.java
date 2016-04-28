package hdsd.bitcoinexrate;


        import android.app.Activity;
        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class NoteTaker extends Activity {
    ArrayList<String> notes;
    ArrayAdapter<String> notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taker);

        setup();
    }

    private void setup() {
        //create names data model list
        notes = new ArrayList<>();
        notes.add("Buy Buy Buy");
        notes.add("Sell Sell Sell");
        notes.add("Click me to Delete");

        //instantiate adapter simple_list_item_1
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        //attached adapter to ListView
        ListView notesListView = (ListView) findViewById(R.id.notes_list_view);
        notesListView.setAdapter(notesAdapter);


        //set Click Listener
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String tempText = editText.getText().toString();
                notes.add(tempText);
                notesAdapter.notifyDataSetChanged();
                editText.setText(null);

                Context context = getApplicationContext();
                Toast.makeText(context, "Added  '" + tempText + "'", Toast.LENGTH_SHORT).show();


            }
        });


        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemData = notes.get(position);
                Toast.makeText(parent.getContext(), "Removed '" + itemData + "'", Toast.LENGTH_SHORT).show();
                notes.remove(itemData);
                notesAdapter.notifyDataSetChanged();
            }
        });

    }
}
