package com.antonchainikov.graphapp.graph

import android.graphics.Rect

class Dimensions(private val renderArea: Rect = Rect()) {

    val window = Window
    val verticalMapper = VerticalMapper(renderArea)
    val horizontalMapper = HorizontalMapper(renderArea)

    var marginStart: Int = 0
        set(value) {
            val diff = value - field
            var newVal = getStart() + diff
            if (newVal < 0) newVal = 0
            setStart(newVal)
            field = value
        }

    var marginEnd: Int = 0
        set(value) {
            val diff = value - field
            setEnd(getEnd() - diff)
            field = value
        }

    var marginTop: Int = 0
        set(value) {
            val diff = value - field
            var newVal = getTop() + diff
            if (newVal < 0) newVal = 0
            setTop(newVal)
            field = value
        }

    var marginBottom: Int = 0
        set(value) {
            val diff = value - field
            setBottom(getBottom() - diff)
            field = value
        }

    fun getDrawableArea() = Rect(renderArea)

    fun allocTop(height: Int): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        dimensions.setBottom(height)
        setTop(height)
        return dimensions
    }

    fun allocBottom(height: Int): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        val newAreaHeight = renderArea.height() - height
        dimensions.setTop(newAreaHeight)
        setBottom(newAreaHeight)
        return dimensions
    }

    fun allocLeft(width: Int): Dimensions {
        val dimensions = Dimensions(getDrawableArea())
        dimensions.setEnd(width)
        setStart(width)
        return dimensions
    }

    fun allocRight(width: Int): Dimensions {
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

    private fun setStart(value: Int) {
        renderArea.left = value
    }

    private fun setEnd(value: Int) {
        renderArea.right = value
    }

    private fun setTop(value: Int) {
        renderArea.top = value
    }

    private fun setBottom(value: Int) {
        renderArea.bottom = value
    }

    fun update(dx: Int, dy: Int) {
        renderArea.right += dx
        renderArea.bottom += dy
    }
}
