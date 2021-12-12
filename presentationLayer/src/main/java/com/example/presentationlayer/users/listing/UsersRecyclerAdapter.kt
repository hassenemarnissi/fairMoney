package com.example.presentationlayer.users.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentationlayer.databinding.HolderUserBinding
import com.example.presentationlayer.users.UserUi

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

class UserAdapter(
    private val context: Context,
    private val collection: List<Pair<UserUi, String>>,
    private val clickAction: (Pair<String, CardView>) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = HolderUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserHolder(itemBinding, clickAction)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (position != RecyclerView.NO_POSITION) (holder as UserHolder).bind(collection[position])
    }

    override fun getItemCount(): Int = collection.size
}

private class UserHolder(
    private val binding: HolderUserBinding,
    private val clickAction: (Pair<String, CardView>) -> Unit
) : BaseViewHolder<Pair<UserUi, String>>(binding.root) {
    override fun bind(item: Pair<UserUi, String>) {
        with(binding) {
            userUi = item.first
            cardView.transitionName = item.first.name
            root.setOnClickListener { clickAction.invoke(item.second to cardView) }
        }
    }

}


