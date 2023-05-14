package app.simulacra.discover.sections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.simulacra.discover.R
import app.simulacra.domaindiscover.Summary
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_summary.tvName
import kotlinx.android.synthetic.main.item_summary.tvDescription

class SummariesAdapter(
    private val onClickListener: (summary: Summary) -> Unit
) : ListAdapter<Summary, SummariesAdapter.ItemViewHolder>(SummaryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_summary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class ItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: Summary) {
            tvName.text = item.title
            tvDescription.text = item.summaryShort
            containerView.setOnClickListener { onClickListener.invoke(item) }
        }
    }
}

class SummaryItemDiffCallback : DiffUtil.ItemCallback<Summary>() {

    override fun areItemsTheSame(oldItem: Summary, newItem: Summary): Boolean {
        return oldItem.sourceUrl == newItem.sourceUrl
    }

    override fun areContentsTheSame(oldItem: Summary, newItem: Summary): Boolean {
        return oldItem == newItem
    }

}