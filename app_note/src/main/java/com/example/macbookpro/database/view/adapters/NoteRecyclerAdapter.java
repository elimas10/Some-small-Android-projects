package com.example.macbookpro.database.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.example.macbookpro.database.R;

import com.example.macbookpro.database.model.entities.NoteEntity;

import java.util.ArrayList;
import com.example.macbookpro.database.view.interfaces.*;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.NoteViewHolder> {

    ArrayList<NoteEntity> data=new ArrayList<>();
    Context context;
    OnNoteStateOperations operations=null;



    public void setOperations(OnNoteStateOperations operations) {
        this.operations = operations;
    }

    public NoteRecyclerAdapter(ArrayList<NoteEntity> data, Context context) {
        this.data = data;
        this.context = context;
    }

    private NoteRecyclerAdapter() {
    }

    @NonNull
    @Override
    public NoteRecyclerAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.item_recycler,null,false);
        NoteViewHolder holder=new NoteViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerAdapter.NoteViewHolder holder, int position) {
        NoteEntity note=data.get(position);
        holder.txtContent.setText(note.getContent());
        holder.txtTitle.setText(note.getTitle());
        holder.txtDate.setText(note.getDateInString());

        holder.position=position;
        holder.item=note;


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public void addNote(NoteEntity newNote) {
        data.add(newNote);
        int pos=data.lastIndexOf(newNote);
        notifyItemInserted(pos);
    }

    public void removeNote(NoteEntity entity) {
        int index = data.indexOf(entity);
        data.remove(entity);
        notifyItemRemoved(index);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

        NoteEntity item;
        int position;

        TextView txtTitle;
        TextView txtDate;
        TextView txtContent;
        ImageButton btn;


        public NoteViewHolder(View itemView) {
            super(itemView);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtContent=itemView.findViewById(R.id.txtContent);
            btn=itemView.findViewById(R.id.btn);
            btn.setOnClickListener(this);


        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        }

        @Override
        public void onClick(View v) {
            PopupMenu pop=new PopupMenu(context,btn);
            pop.inflate(R.menu.menu_note_item_more);
            pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.delete:
                            if(operations!=null)
                                operations.onTaskEditClicked((NoteEntity) item);
                            return true;

                        case R.id.edit:
                            if (operations!=null)
                                operations.onTaskEditClicked((NoteEntity) item);
                            return true;
                            default:
                                return false;

                    }
                }
            });
            pop.show();

        }




        }
    }

