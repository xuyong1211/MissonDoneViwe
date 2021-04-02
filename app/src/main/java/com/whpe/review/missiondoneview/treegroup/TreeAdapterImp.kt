package com.whpe.review.missiondoneview.treegroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.whpe.review.missiondoneview.R

class TreeAdapterImp : TreeAdapter {
    override var treeNode: TreeNode? = null
    override fun getView(node: TreeNode,viewGroup: ViewGroup): View {
        var view =  LayoutInflater.from(viewGroup.context).inflate(R.layout.tree_node, null)
        var tvName = view.findViewById<TextView>(R.id.tv_name)
        tvName.text = treeNode?.name1
        return view
    }
}