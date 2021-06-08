package com.whpe.review.missiondoneview

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

class DoneView : View {
    constructor( context: Context ,attributeSet: AttributeSet):super(context,attributeSet)


    private var radius = 0
    private lateinit var center :Point
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = (w.coerceAtMost(h) - paddingLeft - paddingRight)/2
        center = Point(w/2, h/2)
    }

}