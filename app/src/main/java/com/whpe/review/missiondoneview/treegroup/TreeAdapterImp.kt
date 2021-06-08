package com.whpe.review.missiondoneview.treegroup

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.whpe.review.missiondoneview.R

class TreeAdapterImp : TreeAdapter {
    override var treeNode: TreeNode? = null
    override fun getView(node: TreeNode,viewGroup: ViewGroup): View {
        var view =  LayoutInflater.from(viewGroup.context).inflate(R.layout.tree_node, viewGroup,false)
        var tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = node?.name1
        return view
    }
}