package com.dnieln7.fake17.ui.home.cat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.CatListTileBinding
import com.dnieln7.fake17.domain.Cat

class CatListAdapter(private val cats: List<Cat>, private val onClick: (Cat) -> Unit) :
    RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    class CatViewHolder(private val binding: CatListTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat, onClick: (Cat) -> Unit) {
            val uri = cat.catImage?.url?.toUri()?.buildUpon()?.scheme("https")?.build()

            with(binding) {
                root.setOnClickListener { onClick(cat) }
                image.load(uri) {
                    crossfade(true)
                    error(R.drawable.ic_broken_image)
                }
                name.text = cat.name
                origin.text = cat.origin
                weight.text = root.context.getString(R.string.weight, cat.catWeight.metric)
                temperament.text = root.context.getString(
                    R.string.temperament_description,
                    cat.temperament
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            CatListTileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(cats[position], onClick)
    }

    override fun getItemCount(): Int {
        return cats.size
    }
}