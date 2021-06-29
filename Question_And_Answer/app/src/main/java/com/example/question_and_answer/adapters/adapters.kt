package com.example.question_and_answer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.question_and_answer.R
import com.example.question_and_answer.holder.exampleViewHolder
import java.util.*

class Adapters(var layout: Int, private var items: ArrayList<out Any>) : RecyclerView.Adapter<CustomViewHolder>() {
    var TAG = "Adapters"

    //private var onitemdraglistener : ItemDragListener
    private var onClickListener: ((View, Int) -> Unit)? = null
    private var onLongClickListener: ((View, Int) -> Unit)? = null
    private var onCallbackListener: ((Int, Boolean, String) -> Unit)? = null
    private var onCallbackTypeListener: ((Int, ADAPTERTYPE, String) -> Unit)? = null
    private var onPositionCallbackListener: ((Int) -> Unit)? = null

    enum class ADAPTERTYPE { EDIT, DEL, MEMBERADD, DETAIL }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(layout, parent, false)



        return when (layout) {

            R.layout.item_example -> exampleViewHolder(view, onClickListener, onPositionCallbackListener, onLongClickListener)

            else -> exampleViewHolder(view, onClickListener, onPositionCallbackListener, onLongClickListener)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(@NonNull holderCustom: CustomViewHolder, position: Int) {

        holderCustom.init(items[position])

    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.init(items[position])
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    fun onListClickListener(onClickListener: (view: View, position: Int) -> Unit) {
        this.onClickListener = onClickListener
        notifyDataSetChanged()
    }

    fun onListLongClickListener(onLongClickListener: (view: View, position: Int) -> Unit) {
        this.onLongClickListener = onLongClickListener
        notifyDataSetChanged()
    }

    fun onCallbackListener(onCallbackListener: (position: Int, check: Boolean, name: String) -> Unit) {
        this.onCallbackListener = onCallbackListener
        notifyDataSetChanged()
    }

    fun onCallbackTypeListener(onCallbackListener: (position: Int, type: ADAPTERTYPE, name: String) -> Unit) {
        this.onCallbackTypeListener = onCallbackListener
        notifyDataSetChanged()
    }

    fun onPositionCallbackListener(onCardViewCallbackListener: (position: Int) -> Unit) {
        this.onPositionCallbackListener = onCardViewCallbackListener
        notifyDataSetChanged()
    }

    fun setItems(items: ArrayList<out Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setItemWithPosition(item: Nothing, position: Int) {
        this.items.set(position, item)
        notifyDataSetChanged()
    }

    fun setItems1(check: Boolean) {
        notifyItemRangeChanged(0, items.size, check)
    }

    fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(item: Any) {
        this.items.add(item as Nothing)
    }


    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}