package com.example.notes

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,val listener: INotesRvAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.iv_txt)
        val deleteButton = itemView.findViewById<ImageView>(R.id.iv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentnote = allNotes[position]
        holder.textView.text = currentnote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updatelist(newlist:List<Note>){
        allNotes.clear()
        allNotes.addAll(newlist)
        notifyDataSetChanged()
    }

}

interface INotesRvAdapter{
    fun onItemClicked(note: Note)
}