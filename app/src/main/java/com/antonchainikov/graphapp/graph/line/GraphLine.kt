package com.antonchainikov.graphapp.graph.line

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.antonchainikov.graphapp.GraphElement
import com.antonchainikov.graphapp.graph.Dimensions
import com.antonchainikov.graphapp.graph.GraphData

class GraphLine(data: GraphData, dimensions: Dimensions): GraphElement(data, dimensions) {

    var linePaint: Paint = Paint()
    private val path: Path = Path()

    override fun draw(canvas: Canvas) {
        calcPath()
        canvas.drawPath(path, linePaint)
    }

    private fun calcPath() {
        path.reset()
        data.points.forEachIndexed { index, graphPoint ->
            val x = dimensions.horizontalMapper.map(graphPoint.date, data.minXValue, data.maxXValue)
            val y = dimensions.verticalMapper.map(graphPoint.value, data.minYValue, data.maxYValue)
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
    }
}
