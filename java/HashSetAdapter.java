package wafoot.becoming.wafoot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HashSetAdapter extends ArrayAdapter<String> {

    private HashSet<String> dataSet;
    private Context mContext;

    public HashSetAdapter(Context context, HashSet<String> data) {
        super(context, R.layout.messages);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public String getItem(int position) {
        List<String> list = new ArrayList<>(dataSet);
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.messages, parent, false);
            
            viewHolder = new ViewHolder();
            viewHolder.textViewItem = convertView.findViewById(R.id.tvMessage);

            convertView.setTag(viewHolder);

        }
        
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);
        viewHolder.textViewItem.setText(item);

        return convertView;
    }

    private static class ViewHolder {
        TextView textViewItem;
    }
}
