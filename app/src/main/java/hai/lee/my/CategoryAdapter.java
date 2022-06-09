package hai.lee.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slao,parent,false);
        TextView tv_selected = convertView.findViewById(R.id.selected_ao);
        Category category = this.getItem(position);
        if(category != null){
            tv_selected.setText(category.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ctr,parent,false);
        TextView tvCategory = convertView.findViewById(R.id.tv_category);
        Category category = this.getItem(position);
        if(category != null){
            tvCategory.setText(category.getName());
        }
        return convertView;
    }
}
