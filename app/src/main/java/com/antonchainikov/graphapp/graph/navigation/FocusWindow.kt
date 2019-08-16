package com.antonchainikov.graphapp.graph.navigation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Region
import android.os.Build
import android.view.MotionEvent
import com.antonchainikov.graphapp.GraphElement
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

class FocusWindow(data: GraphData, dimensions: Dimensions, context: Context): GraphElement(data, dimensions, context) {

    constructor(context: Context): this(GraphData.default(), Dimensions(), context)

    enum class TouchableArea {
        BORDER_LEFT, BORDER_RIGHT, WINDOW
    }

    interface OnTouchListener {
        fun onFocusWindowTouch(event: MotionEvent, area: TouchableArea)
    }

    val borderPaint = Paint()

    var focusWindowTouchListener: OnTouchListener? = null

    private val BORDER_WIDTH_VERT = 8f
    private val BORDER_WIDTH_HORZ = 32f

    private val internalWindow = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            canvas.save()
            canvas.clipRect(dimensions.getDrawableArea())

            if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
                canvas.clipRect(internalWindow, Region.Op.DIFFERENCE)
            else{
                canvas.clipOutRect(internalWindow);
            }

            canvas.drawRect(dimensions.getDrawableArea(), borderPaint)

            canvas.restore()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        updateVerticalBorders()
        updateHorizontalBorders()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        if (wasStartBorderTouched(event)) {
            focusWindowTouchListener?.onFocusWindowTouch(event, TouchableArea.BORDER_LEFT)
            return true
        }
        if (wasEndBorderTouched(event)) {
            focusWindowTouchListener?.onFocusWindowTouch(event, TouchableArea.BORDER_RIGHT)
            return true
        }
        focusWindowTouchListener?.onFocusWindowTouch(event, TouchableArea.WINDOW)
        return true
    }

    private fun wasStartBorderTouched(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        return  x >= dimensions.getStart() && x <= internalWindow.left
                && y >= dimensions.getTop() && y <= dimensions.getBottom()
    }

    private fun wasEndBorderTouched(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        return  x >= internalWindow.right && x <= dimensions.getEnd()
                && y >= dimensions.getTop() && y <= dimensions.getBottom()
    }

    private fun updateVerticalBorders() {
        internalWindow.top = dimensions.getTop() + BORDER_WIDTH_VERT
        internalWindow.bottom = dimensions.getBottom() - BORDER_WIDTH_VERT
    }

    private fun updateHorizontalBorders() {
        updateStartBorder()
        updateEndBorder()
    }

    private fun updateStartBorder() {
        internalWindow.left = dimensions.getStart() + BORDER_WIDTH_HORZ
    }

    private fun updateEndBorder() {
        internalWindow.right = dimensions.getEnd() - BORDER_WIDTH_HORZ
    }
}
