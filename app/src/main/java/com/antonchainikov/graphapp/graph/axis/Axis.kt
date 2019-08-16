package com.antonchainikov.graphapp.graph.axis

import android.content.Context
import android.graphics.Paint
import com.antonchainikov.graphapp.GraphElement
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

abstract class Axis(data: GraphData, dimensions: Dimensions, context: Context): GraphElement(data, dimensions, context) {

    val axisPaint = Paint()
    val textPaint = Paint()
}