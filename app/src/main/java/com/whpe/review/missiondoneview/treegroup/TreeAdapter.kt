package com.whpe.review.missiondoneview.treegroup

import android.view.View
import android.view.ViewGroup

interface TreeAdapter{

    var treeNode: TreeNode?

    fun getView(node: TreeNode,viewGroup: ViewGroup):View





}