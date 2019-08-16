package com.antonchainikov.graphapp.graph.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Region
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import com.antonchainikov.graphapp.ComplexGraphElement
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData
import com.antonchainikov.graphapp.graph.line.GraphLine

const val MOVE_SENSIVITY = 8F

class NavWindow(data: GraphData, dimensions: Dimensions, context: Context): ComplexGraphElement(data, dimensions, context), FocusWindow.OnTouchListener {

    constructor(context: Context): this(GraphData.default(), Dimensions(), context)

    private val graphLine = GraphLine(data, Dimensions(), context)
    private val focusWindow = FocusWindow(data, Dimensions(), context)

    val graphLinePaint = graphLine.linePaint
    val focusWindowPaint = focusWindow.borderPaint
    val navWindowTintPaint = Paint()

    private var touchedFocusWindowArea: FocusWindow.TouchableArea? = null
    private val focusWindowCoords = Rect()

    private var touchX = 0F

    init {
        addView(graphLine, 0)
        addView(focusWindow, 1)
        focusWindow.focusWindowTouchListener = this
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            canvas.save()

            canvas.clipRect(dimensions.getStart(), dimensions.getTop(), dimensions.getEnd(), dimensions.getBottom())
            if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
                canvas.clipRect(focusWindow.left.toFloat(), dimensions.getTop(), focusWindow.right.toFloat(), dimensions.getBottom(), Region.Op.DIFFERENCE)
            else{
                canvas.clipOutRect(focusWindow.left.toFloat(), dimensions.getTop(), focusWindow.right.toFloat(), dimensions.getBottom())
            }
            canvas.drawPaint(navWindowTintPaint)

            canvas.restore()
        }
    }

    @SuppressLint("WrongCall")
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        onDraw(canvas)
    }

    override fun onDatasetChanged() {
        super.onDatasetChanged()
        graphLine?.data = data
        focusWindow?.data = data
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(this::class.java.canonicalName, "onFocusWindowTouch")
        graphLine.layout(dimensions.getStart().toInt(), dimensions.getTop().toInt(), dimensions.getEnd().toInt(), dimensions.getBottom().toInt())
        focusWindow.layout(Math.round((dimensions.getEnd() * 0.6F + touchX)), dimensions.getTop().toInt(), Math.round((dimensions.getEnd() + touchX)), dimensions.getBottom().toInt())
    }

    override fun onFocusWindowTouch(event: MotionEvent, area: FocusWindow.TouchableArea) {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchX = event.x
            }
            MotionEvent.ACTION_UP -> {
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - touchX
                Log.d(this::class.java.canonicalName, "onFocusWindowTouch dx ${dx}")
                if (Math.abs(dx) >= MOVE_SENSIVITY) {
                    Log.d(this::class.java.canonicalName, "updated")
                    touchX = event.x
                    focusWindow.translationX = dx
                    invalidate()
                }
            }
        }
    }
}
