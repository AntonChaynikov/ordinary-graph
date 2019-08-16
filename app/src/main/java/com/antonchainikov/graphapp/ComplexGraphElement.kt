package com.antonchainikov.graphapp

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

abstract class ComplexGraphElement(data: GraphData, protected var dimensions: Dimensions, context: Context): ViewGroup(context) {

    constructor(context: Context): this(GraphData.default(), Dimensions(), context)

    var data: GraphData = GraphData.default()
        set(value) {
            field = value
            onDatasetChanged()
        }

    init {
        this.data = data
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dimensions.update((w - oldw).toFloat(), (h - oldh).toFloat())
    }

    open fun onDatasetChanged() {
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(this::class.java.canonicalName, "onTouchEvent")
        return super.onTouchEvent(event)
    }
}
