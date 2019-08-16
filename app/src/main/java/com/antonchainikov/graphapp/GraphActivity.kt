package com.antonchainikov.graphapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antonchainikov.graphapp.graph.Graph

class GraphActivity: AppCompatActivity() {

    lateinit var graph: Graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        graph = Graph(this)
        setContentView(graph)
    }
}