package com.whpe.review.missiondoneview.treegroup

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.whpe.review.missiondoneview.R
import kotlin.math.max
import kotlin.properties.Delegates

class TreeView : ViewGroup {
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private var widthCount = 0
    var adapter: TreeAdapter? = null

    var maxDeep: Int = 0
    private fun obtainAllView(treeNode: TreeNode?, deep: Int) {
        var thisDeep = deep
        if (treeNode == null) {
            maxDeep = max(thisDeep, maxDeep)
            return
        }
        treeNode.children?.forEach { treeNode ->
            obtainAllView(treeNode, thisDeep+1)

        }
        if (treeNode.children == null) {
            widthCount++
            maxDeep = max(thisDeep, maxDeep)
        }
        var childView = adapter?.getView(treeNode, this)
        treeNode.view = childView
//        Log.d("addView", "${childView.toString()}${childView?.findViewById<TextView>(R.id.tv_name)}")
        treeNode.deep = deep
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthCount = 0
        obtainAllView(adapter?.treeNode, 1)
        childWidth = measuredWidth / widthCount
        childHeight = measuredHeight / maxDeep
        currentX = 0
        measureAllView(adapter?.treeNode!!)

        for (child in children) {
            child.measure(MeasureSpec.makeMeasureSpec(20,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(20,MeasureSpec.EXACTLY))
        }
    }




    var childWidth = 0
    var childHeight = 0

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d("addView","onLayout")
        addAllViews(adapter?.treeNode!!)
        layoutAllView(adapter?.treeNode!!)
//        if (adapter != null) {
//            children.forEach {
//                it.layout(it.x.toInt(),it.y.toInt(),(it.x + childWidth).toInt(),(it.y+childHeight).toInt())
////                Log.d("addView", "${it.x.toInt()}--${it.y.toInt()}--${(it.x + childWidth).toInt()}--${(it.y+childHeight).toInt()}")
//            }
//        }


    }

    private fun layoutAllView(treeNode: TreeNode) {

        if (treeNode.children == null) {
            treeNode.view?.layout(treeNode.x+8,treeNode.y-8,(treeNode.x + childWidth),(treeNode.y+childHeight-80))

            return
        }

        for ((index,childNode) in(treeNode.children!!.withIndex())){
            layoutAllView(childNode)
        }

        treeNode.view?.layout(treeNode.x,treeNode.y,(treeNode.x + childWidth),(treeNode.y+childHeight-80))
    }



    private fun addAllViews(treeNode: TreeNode) {
        if (treeNode.children == null) {
            addView(treeNode.view)
            return
        }

        for ((index,childNode) in(treeNode.children!!.withIndex())){
            addAllViews(childNode)
        }

        addView(treeNode.view)

    }

    var viewCount = 0
    var currentX = 0
    private fun measureAllView(treeNode: TreeNode): Int {

        var leftX = 0
        var rightX = 0
        if (treeNode.children == null) {
            currentX += childWidth
            treeNode.x = (currentX - childWidth)
            treeNode.y = ((treeNode.deep.minus(1)).times(childHeight))
            Log.d("measureAllView","${treeNode.name1}-- ${treeNode.x} -- ${treeNode.y}")
            return currentX - childWidth
        }

        for ((index,childNode) in(treeNode.children!!.withIndex())){
            var X = measureAllView(childNode)
            if (index == 0) {
                leftX = X + childWidth
            }
            if (index == (treeNode.children?.size?.minus(1))) {
                rightX = X
            }
        }
        var nodeX = leftX + (rightX - leftX) / 2
        treeNode.x = (nodeX - (childWidth / 2))
        treeNode.y = ((treeNode.deep.minus(1)).times(childHeight))
        Log.d("measureAllView","${treeNode.name1}-- ${treeNode.x} -- ${treeNode.y}")
        return (nodeX - (childWidth / 2))
    }

}