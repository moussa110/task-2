package com.example.task2.ui.MainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.api.model.ReposResponse

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var data: List<ReposResponse.ReposResponseItem?>? = null;
    var onItemClick: OnRecyclerItemClickListener? = null;


    fun changeData(item: List<ReposResponse.ReposResponseItem?>?) {
        data = item;
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false);
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position);
        if (item?.fork == false || item?.fork == null) {
            holder.itemView.setBackgroundResource(R.color.colorAccent);
        } else
            holder.itemView.setBackgroundResource(R.color.whitet);

        if (onItemClick != null) {
            holder.itemView.setOnLongClickListener {
                onItemClick?.onClick(item)
                return@setOnLongClickListener true;
            }
        }

        holder.name.text = item?.name;
        holder.userName.text = item?.license?.name;
        holder.description.text = item?.description;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_name);
        val userName: TextView = itemView.findViewById(R.id.item_user_name);
        val description: TextView = itemView.findViewById(R.id.item_description);
    }

    interface OnRecyclerItemClickListener {
        fun onClick(item: ReposResponse.ReposResponseItem?);
    }

}