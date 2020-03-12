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

public class NewDateAdapter extends ArrayAdapter<ItemDateClass> {
    private ArrayList<ItemDateClass> listDate = new ArrayList<>();


    public NewDateAdapter(Context context, int resource,  ArrayList<ItemDateClass> objects) {
        super(context, resource, objects);
        listDate = objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_date_layout,null);

        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView dtCount = convertView.findViewById(R.id.countDateeSec);
        dtCount.setText(Integer.toString(position+1));
        txtDate.setText(listDate.get(position).getCurrentdate());
        return convertView;

    }


}
