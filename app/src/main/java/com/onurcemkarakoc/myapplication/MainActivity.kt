package com.onurcemkarakoc.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView

class MainActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expandableListView = findViewById(R.id.elv)

        initElv()

    }

    private fun initElv() {

        val groupList = arrayListOf("GROUP 0", "GROUP 1", "GROUP 2", "GROUP 3", "GROUP 4", "GROUP 5", "GROUP 6")
        val childList = arrayListOf("CHILD 0", "CHILD 1", "CHILD 2", "CHILD 3", "CHILD 4", "CHILD 5", "CHILD 6")

        val adapter = ExpandableListViewAdapter(
            this,
            groupList,
            childList
        )
        expandableListView.setAdapter(adapter)

        val params = expandableListView.layoutParams
        val desireWidth: Int =
            View.MeasureSpec.makeMeasureSpec(expandableListView.width, View.MeasureSpec.UNSPECIFIED)


        val h = getHeaderHeight(groupList.size, desireWidth)

        params.height = h
        expandableListView.layoutParams = params

        var lastPosition = -1
        var childViewHeight = 0
        expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            if (lastPosition != groupPosition) {
                parent.expandGroup(groupPosition)
                parent.collapseGroup(lastPosition)

                childViewHeight += getChildHeight(groupPosition, desireWidth, adapter)

                if (lastPosition != -1  ) childViewHeight -= getChildHeight(lastPosition, desireWidth, adapter)
                lastPosition = groupPosition
            } else {

                childViewHeight -= getChildHeight(groupPosition, desireWidth, adapter)

                if (childViewHeight<1) lastPosition = -1

                parent.collapseGroup(groupPosition)
            }
            params.height = h + childViewHeight
            expandableListView.layoutParams = params
            return@setOnGroupClickListener true
        }
    }

    private fun getChildHeight(
        groupPosition: Int,
        desireWidth: Int,
        adapter: ExpandableListViewAdapter
    ): Int {
        var h = 0
        val count = adapter.getChildrenCount(groupPosition)
        repeat(count) {
            val childView =
                adapter.getChildView(groupPosition, it, false, null, expandableListView)
            childView.measure(desireWidth, View.MeasureSpec.UNSPECIFIED)
            h += childView.measuredHeight
        }
        return h
    }

    private fun getHeaderHeight(size: Int, desireWidth: Int): Int {
        var h = 0
        repeat(size) {
            val listItem = expandableListView.adapter.getView(it, null, expandableListView)
            listItem.measure(desireWidth, View.MeasureSpec.UNSPECIFIED)
            h += listItem.measuredHeight
        }
        return h
    }

}
