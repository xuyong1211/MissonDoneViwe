package com.whpe.review.missiondoneview.treegroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max
import kotlin.properties.Delegates

class TreeView : ViewGroup {
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    private var widthCount = 0
    var adapter: TreeAdapter ? = null
    set(value) {
        field = value
        widthCount = obtainAllView(adapter?.treeNode, 1)
    }
    var maxDeep : Int = 0
    private fun obtainAllView(treeNode: TreeNode?,deep:Int):Int {
        var thisDeep = deep
        var width = 0
        if(treeNode == null){
            maxDeep = max(thisDeep, maxDeep)
            return width
        }
        treeNode.children?.forEach {  treeNode ->
            obtainAllView(treeNode,++thisDeep)

        }
        if(treeNode.children == null){
            width ++
        }
        var childView = adapter?.getView(treeNode, this)
        treeNode.view = childView
        treeNode.deep = deep
        return width
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


    }
    var childWidth = 0
    var childHeight = 0
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        childWidth = measuredWidth/widthCount
        childHeight = measuredHeight/maxDeep
        layoutAllView(adapter?.treeNode)
    }

    private fun layoutAllView(treeNode: TreeNode?) {
        if(treeNode == null){
            return
        }
        treeNode.children?.forEachIndexed { index, treeNode ->

        }
    }

}