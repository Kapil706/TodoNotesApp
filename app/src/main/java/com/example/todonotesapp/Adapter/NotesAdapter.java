package com.example.todonotesapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todonotesapp.Model.Notes;
import com.example.todonotesapp.R;
import com.example.todonotesapp.clicklistener.ItemClickListener;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

   private List<Notes> notesList;
   private ItemClickListener itemClickListener;

    public NotesAdapter(List<Notes> notes,ItemClickListener itemClickListener){

        this.notesList=notes;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
              // bind the data from the list to viewHolder
        final Notes notes = notesList.get(position);
        String title = notes.getTitle();
        String description = notes.getDescription();

        holder.textViewTitle.setText(title);
        holder.textViewDescription.setText(description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       itemClickListener.onClick(notes);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle,textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDescription=itemView.findViewById(R.id.textViewDescription);

        }
    }
}
