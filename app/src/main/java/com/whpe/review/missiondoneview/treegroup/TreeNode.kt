package com.whpe.review.missiondoneview.treegroup

import android.view.View

class TreeNode {
    constructor(name1: String) {
        this.name1 = name1
    }

    var type = 1
    var name1 = ""
    var name2 = ""
    var deep =  0
    var view : View? = null
    var children : List<TreeNode>? = null

}