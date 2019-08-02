package com.antonchainikov.graphapp.graph

import android.graphics.Rect

class VerticalMapper(private val renderArea: Rect): LineMapper {
    override fun map(value: Long, minValue: Long, maxValue: Long): Float {
        val height = renderArea.height()
        return height - height.toFloat() / maxValue * value
    }
}