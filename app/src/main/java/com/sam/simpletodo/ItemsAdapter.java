package com.sam.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

// resposible for displaying data from the model into row in the recyler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    public interface OnClickListener {
        void onItemClicked(int poistion);
    }


    List<String> items;
    OnLongClickListener onLongClickListener;
    OnClickListener ClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener onClickListener) {
        this.items = items;
        this.onLongClickListener = longClickListener;
        this.ClickListener = onClickListener;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        // use layout to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it in view holder and return it
        return new ViewHolder(todoView);
    }

    // responsible for binding data to particular view holder
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemsAdapter.ViewHolder holder, int position) {
        // grab item at the position
        String item = items.get(position);
        // bind the item into specified view holder
        holder.bind(item);
    }

    // tells the recycler view how many items are there in the list
    @Override
    public int getItemCount() {
        return items.size();
    }


    // Container to provide easy access to views that represent each row of list
    class ViewHolder extends RecyclerView.ViewHolder {  // public static abstract class RecyclerView.ViewHolder

        TextView tvView;


        public ViewHolder(@NonNull View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tvView = itemView.findViewById(android.R.id.text1);

        }

        // update the view inside of the view holder with this data
        public void bind(String item) {
            tvView.setText(item);
            tvView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // getAdapterPosition() is decrepeted
                    // alternative is getBindingAdapterPosition()
                    ClickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // remove the item from the recycler view
                    // getAdapterPosition() is decrepeted
                    // notify the listener which position was long pressed
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
