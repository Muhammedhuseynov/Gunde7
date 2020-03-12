package tm.example.gunde7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CostumAdapter extends ArrayAdapter<ItemsClass> {
    private ArrayList <ItemsClass> lists = new ArrayList<>();
    private LayoutInflater inflater;

    public CostumAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemsClass> objects) {
        super(context, resource, objects);
        lists = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      convertView = inflater.inflate(R.layout.item_layout,null);
        TextView txt = convertView.findViewById(R.id.txt);
        TextView mtxt = convertView.findViewById(R.id.count);
        mtxt.setText(Integer.toString(position+1));
        txt.setText(lists.get(position).getName());

        return convertView;

    }
}
