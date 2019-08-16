package com.antonchainikov.graphapp.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup
import com.antonchainikov.graphapp.R
import com.antonchainikov.graphapp.graph.axis.Axis
import com.antonchainikov.graphapp.graph.axis.XAxis
import com.antonchainikov.graphapp.graph.axis.YAxis
import com.antonchainikov.graphapp.graph.line.GraphLine
import com.antonchainikov.graphapp.graph.navigation.NavWindow

class Graph(context: Context): ViewGroup(context) {

    var data: GraphData =
        GraphData.default()
        set(value) {
            field = value
            yAxis.data = value
            xAxis.data = value
            graphLine.data = value
            navWindow.data = value
            invalidate()
        }

    private val freeAreaDimensions: Dimensions

    private val yAxis: Axis
    private val xAxis: Axis
    private val graphLine: GraphLine
    private val navWindow: NavWindow

    init {
        freeAreaDimensions = Dimensions()

        yAxis = YAxis(data, Dimensions(), context)
        xAxis = XAxis(data, Dimensions(), context)
        graphLine = GraphLine(data, Dimensions(), context)
        navWindow = NavWindow(data, Dimensions(), context)

        freeAreaDimensions.marginStart = 24F
        freeAreaDimensions.marginEnd = 24F
        freeAreaDimensions.marginTop = 24F
        freeAreaDimensions.marginBottom = 24F

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

        val linePaint = graphLine.linePaint
        linePaint.color = Color.RED
        linePaint.isAntiAlias = true
        linePaint.strokeWidth = 8f
        linePaint.style = Paint.Style.STROKE

        val navWindowLinePaint = navWindow.graphLinePaint
        navWindowLinePaint.color = Color.RED
        navWindowLinePaint.isAntiAlias = true
        navWindowLinePaint.strokeWidth = 4f
        navWindowLinePaint.style = Paint.Style.STROKE

        val navWindowBorderPaint = navWindow.focusWindowPaint

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            navWindowBorderPaint.color = context.getColor(R.color.colorFocusWindowBorder)
        } else {
            navWindowBorderPaint.color = context.resources.getColor(R.color.colorFocusWindowBorder)
        }

        navWindowBorderPaint.style = Paint.Style.FILL

        val navWindowTintPaint = navWindow.navWindowTintPaint

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            navWindowTintPaint.color = context.getColor(R.color.colorFocusWindowTint)
        } else {
            navWindowTintPaint.color = context.resources.getColor(R.color.colorFocusWindowTint)
        }

        navWindowTintPaint.style = Paint.Style.FILL
        addView(yAxis, 0)
        addView(xAxis, 1)
        addView(graphLine, 2)
        addView(navWindow, 3)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        freeAreaDimensions.setStart(l.toFloat())
        freeAreaDimensions.setTop(t.toFloat())
        freeAreaDimensions.setEnd(r.toFloat())
        freeAreaDimensions.setBottom(b.toFloat())

        val navWindowRect  = freeAreaDimensions.allocBottom(freeAreaDimensions.window.dip(300f)).getDrawableArea()
        navWindow.layout(navWindowRect.left.toInt(), navWindowRect.top.toInt(), navWindowRect.right.toInt(), navWindowRect.bottom.toInt())

        val xAxisRect = freeAreaDimensions.allocBottom(freeAreaDimensions.window.dip(100f)).getDrawableArea()
        xAxis.layout(xAxisRect.left.toInt(), xAxisRect.top.toInt(), xAxisRect.right.toInt(), xAxisRect.bottom.toInt())

        val yAxisNavWindRect = freeAreaDimensions.allowCurrentAll().getDrawableArea()
        yAxis.layout(yAxisNavWindRect.left.toInt(), yAxisNavWindRect.top.toInt(), yAxisNavWindRect.right.toInt(), yAxisNavWindRect.bottom.toInt())
        graphLine.layout(yAxisNavWindRect.left.toInt(), yAxisNavWindRect.top.toInt(), yAxisNavWindRect.right.toInt(), yAxisNavWindRect.bottom.toInt())
    }
}
