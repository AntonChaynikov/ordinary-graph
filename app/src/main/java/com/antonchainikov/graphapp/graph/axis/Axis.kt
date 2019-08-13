package com.antonchainikov.graphapp.graph.axis

import android.graphics.Paint
import com.antonchainikov.graphapp.GraphElement
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

abstract class Axis(data: GraphData, dimensions: Dimensions): GraphElement(data, dimensions) {

    val axisPaint = Paint()
    val textPaint = Paint()

}