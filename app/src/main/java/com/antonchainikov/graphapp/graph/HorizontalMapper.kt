package com.antonchainikov.graphapp.graph

import android.graphics.Rect

class HorizontalMapper(private val renderArea: Rect): LineMapper {
    override fun map(value: Long, minValue: Long, maxValue: Long): Float {
        val width = renderArea.width()
        return renderArea.left + width.toFloat() / (maxValue - minValue) * (value - minValue)
    }
}
