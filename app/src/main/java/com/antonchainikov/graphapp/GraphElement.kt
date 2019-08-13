package com.antonchainikov.graphapp

import android.graphics.Canvas
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

abstract class GraphElement(data: GraphData, protected var dimensions: Dimensions) {

    var data: GraphData = GraphData.default()
        set(value) {
            field = value
        }

    init {
        this.data = data
    }

    abstract fun draw(canvas: Canvas)

    open fun onDatasetChanged() {
    }

    open fun onSizeChanged(dimensions: Dimensions) {
        this.dimensions = dimensions
    }
}