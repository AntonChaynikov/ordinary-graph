package com.antonchainikov.graphapp.graph.axis

import android.graphics.Canvas
import android.graphics.Paint
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

abstract class Axis(data: GraphData, protected var dimensions: Dimensions) {

    val axisPaint = Paint()
    val textPaint = Paint()
    var data: GraphData

    init {
        this.data = data
    }

    abstract fun draw(canvas: Canvas)

    open fun onSizeChanged(dimensions: Dimensions) {
        this.dimensions = dimensions
    }
}