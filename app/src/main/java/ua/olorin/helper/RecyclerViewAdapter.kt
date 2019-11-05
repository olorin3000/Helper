package ua.olorin.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.olorin.helper.data.Data
import ua.olorin.helper.databinding.ContentRecyclerViewBinding

class RecyclerViewAdapter(private val dataList: List<Data>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val expandedItems = hashMapOf<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ContentRecyclerViewBinding = DataBindingUtil.inflate(
            inflater, R.layout.content_recycler_view, parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], expandedItems[position] ?: false, listener)

    }

    private val listener = object : RecyclerViewListener{
        override fun onItemClick(data: Data, isExpanded: Boolean) {
            val position = dataList.indexOf(data)
            expandedItems[position] = !isExpanded
            notifyItemChanged(position)
        }

    }


    class ItemViewHolder(private val binding: ContentRecyclerViewBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(data: Data, isExpanded: Boolean, listener: RecyclerViewListener){
            binding.data = data
            binding.isExpanded = isExpanded
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    interface RecyclerViewListener{
        fun onItemClick(data: Data, isExpanded: Boolean)
    }
}