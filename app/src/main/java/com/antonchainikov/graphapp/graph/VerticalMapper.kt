package com.antonchainikov.graphapp.graph

import android.graphics.RectF

class VerticalMapper(private val renderArea: RectF): LineMapper {
    override fun map(value: Long, minValue: Long, maxValue: Long): Float {
        val height = renderArea.height()
        return renderArea.top + height - height / maxValue * value
    }
}