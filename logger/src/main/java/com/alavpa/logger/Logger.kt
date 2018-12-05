package com.alavpa.logger

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.os.Build
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnDragListener
import android.view.ViewGroup
import android.widget.LinearLayout

class Logger(activity: Activity) {

    init {
        setDebugContentView(activity)
    }

    private fun setDebugContentView(
        activity: Activity
    ) {
        val rootView: ViewGroup = activity.findViewById(android.R.id.content)

        val view = LayoutInflater.from(activity)
            .inflate(
                R.layout.activity_logger_base,
                rootView,
                true
            )


        view.findViewById<View>(R.id.debugFab).setOnClickListener {
            activity.startActivity(Intent(activity, LogActivity::class.java))
        }

        view.findViewById<View>(R.id.debugFab).setOnLongClickListener { fab ->
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(fab)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fab.startDragAndDrop(data, shadowBuilder, fab, 0)
            } else {
                fab.startDrag(data, shadowBuilder, fab, 0)
            }

            fab.visibility = View.INVISIBLE
            return@setOnLongClickListener true
        }

        view.findViewById<View>(R.id.container1).setOnDragListener(MyDragListener())
        view.findViewById<View>(R.id.container2).setOnDragListener(MyDragListener())
        view.findViewById<View>(R.id.container3).setOnDragListener(MyDragListener())
        view.findViewById<View>(R.id.container4).setOnDragListener(MyDragListener())

    }

    internal inner class MyDragListener : OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    // do nothing
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    // do nothing
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // do nothing
                }
                DragEvent.ACTION_DROP -> {
                    // Dropped, reassign View to ViewGroup
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = v as LinearLayout
                    container.addView(view)
                    view.visibility = View.VISIBLE
                }
                else -> {
                    // do nothing
                }
            }
            return true
        }
    }
}
