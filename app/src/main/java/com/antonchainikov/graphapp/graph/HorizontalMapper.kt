package com.antonchainikov.graphapp.graph

import android.graphics.Rect
import android.graphics.RectF

class HorizontalMapper(private val renderArea: RectF): LineMapper {
    override fun map(value: Long, minValue: Long, maxValue: Long): Float {
        val width = renderArea.width()
        return renderArea.left + width / (maxValue - minValue) * (value - minValue)
    }
}
