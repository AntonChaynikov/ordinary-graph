package com.antonchainikov.graphapp.graph

import android.graphics.RectF

class Dimensions(private val renderArea: RectF = RectF()) {

    val window = Window
    val verticalMapper = VerticalMapper(renderArea)
    val horizontalMapper = HorizontalMapper(renderArea)

    var marginStart: Float = 0F
        set(value) {
            val diff = value - field
            var newVal = getStart() + diff
            if (newVal < 0) newVal = 0F
            setStart(newVal)
            field = value
        }

    var marginEnd: Float = 0F
        set(value) {
            val diff = value - field
            setEnd(getEnd() - diff)
            field = value
        }

    var marginTop: Float = 0F
        set(value) {
            val diff = value - field
            var newVal = getTop() + diff
            if (newVal < 0) newVal = 0F
            setTop(newVal)
            field = value
        }

    var marginBottom: Float = 0F
        set(value) {
            val diff = value - field
            setBottom(getBottom() - diff)
            field = value
        }

    fun getDrawableArea() = RectF(renderArea)

    fun allocTop(height: Float): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        dimensions.setBottom(height)
        setTop(height)
        return dimensions
    }

    fun allocBottom(height: Float): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        val newAreaHeight = renderArea.height() - height
        dimensions.setTop(newAreaHeight)
        setBottom(newAreaHeight)
        return dimensions
    }

    fun allocLeft(width: Float): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        dimensions.setEnd(width)
        setStart(width)
        return dimensions
    }

    fun allocRight(width: Float): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        val newAreaWidth = renderArea.width() - width
        dimensions.setStart(newAreaWidth)
        setEnd(newAreaWidth)
        return dimensions
    }

    fun allowCurrentAll(): Dimensions {
        return Dimensions(getDrawableArea())
    }

    fun getStart() = renderArea.left

    fun getEnd() = renderArea.right

    fun getTop() = renderArea.top

    fun getBottom() = renderArea.bottom

    fun getHeight() = renderArea.height()

    fun getWidth() = renderArea.width()

    fun getCenterX() = renderArea.centerX()

    fun getCenterY() = renderArea.centerY()

    fun setStart(value: Float) {
        renderArea.left = value
    }

    fun setEnd(value: Float) {
        renderArea.right = value
    }

    fun setTop(value: Float) {
        renderArea.top = value
    }

    fun setBottom(value: Float) {
        renderArea.bottom = value
    }

    fun update(dx: Float, dy: Float) {
        renderArea.right += dx
        renderArea.bottom += dy
    }
}
