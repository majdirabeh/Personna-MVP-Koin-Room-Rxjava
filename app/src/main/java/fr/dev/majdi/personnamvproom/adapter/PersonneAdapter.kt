package fr.dev.majdi.personnamvproom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.dev.majdi.personnamvproom.R
import fr.dev.majdi.personnamvproom.model.Personne

/**
 * Created by Majdi RABEH on 30/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class PersonneAdapter(private val personneList: MutableList<Personne>) :
    RecyclerView.Adapter<PersonneAdapter.ViewHolder>() {

    private var onItemClickListener: ItemClickListener? = null
    private var personnes: List<Personne> = personneList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return personnes.size
    }

    public fun setItems(personneList: List<Personne>) {
        personnes = personneList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = personnes[position]

        holder.name.text = user.name.toString()
        holder.email.text = user.email

        if (user.gender == "Male") {
            holder.imageProfile.setImageResource(R.drawable.photo_profile)
        } else {
            holder.imageProfile.setImageResource(R.drawable.profile_female)
        }
        holder.deleteButton.setOnClickListener {
            onItemClickListener?.onDeleteButtonClick(user)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val imageProfile = itemView.findViewById<ImageView>(R.id.image_profile)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete);

        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(itemView, 0)
            }
        }
    }

    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onDeleteButtonClick(personne: Personne)
    }

}