package com.antonchainikov.graphapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.antonchainikov.graphapp.graph.Graph
import com.antonchainikov.graphapp.graph.GraphData
import com.antonchainikov.graphapp.graph.GraphPoint
import org.joda.time.LocalDateTime

class GraphActivity: AppCompatActivity() {

    lateinit var graph: Graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        graph = Graph(this)
        setContentView(graph)
        createData()
    }

    fun createData() {
        Thread {
            val data = ArrayList<GraphPoint>(5)
            val dateNow = LocalDateTime.now()
            data.apply {
                add(GraphPoint(dateNow.withDayOfMonth(5).toDate().time, 50))
                add(GraphPoint(dateNow.withDayOfMonth(7).toDate().time, 100))
                add(GraphPoint(dateNow.withDayOfMonth(8).toDate().time, 150))
                add(GraphPoint(dateNow.withDayOfMonth(10).toDate().time, 70))
                add(GraphPoint(dateNow.withDayOfMonth(11).toDate().time, 200))
            }
            val graphData = GraphData(data)
            Handler(Looper.getMainLooper()).post {
                graph.data = graphData
            }
        }.start()
    }
}