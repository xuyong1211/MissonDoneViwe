package com.whpe.review.missiondoneview.treegroup

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max
import kotlin.properties.Delegates

class TreeView : ViewGroup {
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private var widthCount = 0
    var adapter: TreeAdapter? = null
        set(value) {
            field = value
            obtainAllView(adapter?.treeNode, 1)
            childWidth = measuredWidth / widthCount
            childHeight = measuredHeight / maxDeep
            layoutAllView(adapter?.treeNode!!)
            requestLayout()
            invalidate()
        }
    var maxDeep: Int = 0
    private fun obtainAllView(treeNode: TreeNode?, deep: Int) {
        var thisDeep = deep
        if (treeNode == null) {
            maxDeep = max(thisDeep, maxDeep)
            return
        }
        treeNode.children?.forEach { treeNode ->
            obtainAllView(treeNode, ++thisDeep)

        }
        if (treeNode.children == null) {
            widthCount++
            maxDeep = max(thisDeep, maxDeep)
        }
        var childView = adapter?.getView(treeNode, this)
        treeNode.view = childView
        treeNode.deep = deep
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (child in children) {
            Log.d("addView","onDraw${viewCount}")
            drawChild(canvas,child,100)
        }

    }
    var childWidth = 0
    var childHeight = 0

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
    var viewCount = 0
    var currentX = 0
    private fun layoutAllView(treeNode: TreeNode):Int {

        var leftX = 0
        var rightX = 0
        if(treeNode.children == null){
             currentX += childWidth
            treeNode.view?.x = (currentX - childWidth).toFloat()
            treeNode.view?.y = ((treeNode.deep.minus(1)).times(childHeight)).toFloat()
            addView(treeNode.view)
            Log.d("addView","${viewCount++}")
            return currentX - childWidth
        }
        treeNode.children?.forEachIndexed { index, childNode ->
            var X = layoutAllView(childNode)
            if (index == 0) {
                leftX = X + childWidth
            }
            if (index == (treeNode.children?.size?.minus(1))) {
                rightX = X
            }
        }
        var nodeX = leftX + (rightX- leftX)/2
        treeNode.view?.x = (nodeX - (childWidth/2)).toFloat()
        treeNode.view?.y = ((treeNode.deep.minus(1)).times(childHeight)).toFloat()
        addView(treeNode.view)
        Log.d("addView","${viewCount++}")
        return nodeX
    }

}