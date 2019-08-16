package com.antonchainikov.graphapp

import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData
import com.antonchainikov.graphapp.graph.navigation.NavWindow

open class GraphElement(data: GraphData, protected var dimensions: Dimensions, context: Context): View(context) {

    constructor(context: Context): this(GraphData.default(), Dimensions(), context)

    var data: GraphData = GraphData.default()
        set(value) {
            field = value
            onDatasetChanged()
        }

    init {
        this.data = data
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(this::class.java.canonicalName, "onDraw")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(this::class.java.canonicalName, "onLayout l${l} t${t} r${r} b${b}")
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

    override fun invalidate() {
        super.invalidate()
        Log.d(this::class.java.canonicalName, "invalidate")
    }
}
