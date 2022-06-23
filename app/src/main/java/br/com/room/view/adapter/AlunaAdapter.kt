package br.com.room.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.room.databinding.ItemRecyclerViewBinding
import br.com.room.model.Aluna

class AlunaAdapter(): RecyclerView.Adapter<AlunaAdapter.ViewHolderAluna>() {

    private var alunaList: MutableList<Aluna> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAluna {
        val itemBinding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolderAluna(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderAluna, position: Int) {
        holder.onBind(alunaList[position])
    }

    override fun getItemCount(): Int = alunaList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAux: List<Aluna>){
        alunaList.clear()
        alunaList.addAll(listAux)
        notifyDataSetChanged()
    }

    class ViewHolderAluna(private val binding: ItemRecyclerViewBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(aluna: Aluna){
            binding.apply {
                tvName.text = aluna.name
                tvLastName.text = aluna.lastName
                tvAge.text = aluna.age.toString()
                //tvEmail.text = aluna.email
            }
        }
    }
}