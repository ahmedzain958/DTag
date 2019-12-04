package com.zainco.dtag.presentation.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T : ViewDataBinding>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    protected val binding: T? = DataBindingUtil.bind(itemView)


}
