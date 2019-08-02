package com.antonchainikov.graphapp.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import com.antonchainikov.graphapp.graph.axis.Axis
import com.antonchainikov.graphapp.graph.axis.XAxis
import com.antonchainikov.graphapp.graph.axis.YAxis

class Graph(context: Context): View(context) {

    var data: GraphData =
        GraphData.default()
        set(value) {
            field = value
            yAxis.data = value
            xAxis.data = value
            invalidate()
        }

    private val freeAreaDimensions: Dimensions

    private val yAxis: Axis
    private val xAxis: Axis

    init {
        freeAreaDimensions = Dimensions()

        yAxis = YAxis(data, Dimensions())
        xAxis = XAxis(data, Dimensions())

        freeAreaDimensions.marginStart = 24
        freeAreaDimensions.marginEnd = 24
        freeAreaDimensions.marginTop = 24
        freeAreaDimensions.marginBottom = 24

        val yAxisPaint = yAxis.axisPaint
        yAxisPaint.strokeWidth = 2f
        yAxisPaint.isAntiAlias = true
        yAxisPaint.color = Color.GRAY

        val yAxisTextpaint = yAxis.textPaint
        yAxisTextpaint.color = Color.GRAY
        yAxisTextpaint.textSize = 40f
        yAxisTextpaint.isFakeBoldText = true

        val xAxisTextPaint = xAxis.textPaint
        xAxisTextPaint.color = Color.GRAY
        xAxisTextPaint.textSize = 40f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            yAxis.draw(canvas)
            xAxis.draw(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        freeAreaDimensions.update(w-oldw, h-oldh)

        val xAxisDimensions = freeAreaDimensions.allocBottom(freeAreaDimensions.window.dip(100f).toInt())
        xAxis.onSizeChanged(xAxisDimensions)

        yAxis.onSizeChanged(freeAreaDimensions.allowCurrentAll())
    }
}
