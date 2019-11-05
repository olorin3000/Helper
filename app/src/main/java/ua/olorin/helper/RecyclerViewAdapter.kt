package ua.olorin.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.olorin.helper.data.Data
import ua.olorin.helper.databinding.ContentRecyclerViewBinding

class RecyclerViewAdapter(private val dataList: List<Data>)

    : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ContentRecyclerViewBinding = DataBindingUtil.inflate(
            inflater, R.layout.content_recycler_view, parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    class ItemViewHolder(private val binding: ContentRecyclerViewBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(data: Data){
            binding.data = data
            binding.executePendingBindings()
        }
    }
}