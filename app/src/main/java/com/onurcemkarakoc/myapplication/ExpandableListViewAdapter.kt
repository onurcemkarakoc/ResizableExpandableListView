package com.onurcemkarakoc.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Created by onurcemkarakoc on 2020-01-03.
 */
class ExpandableListViewAdapter(
    val c: Context,
    private val groups: ArrayList<String>,
    private val childs: ArrayList<String>
) : BaseExpandableListAdapter() {

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val item = getGroup(groupPosition)
        val view = (c.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.list_group, null
        )
        val txt: TextView = view?.findViewById(R.id.list_group_txt)!!
        txt.text = item.toString()
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val item = getChild(groupPosition, childPosition)
        val view = (c.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.list_item, null
        )
        val txt: TextView = view?.findViewById(R.id.list_item_txt)!!
        txt.text = item.toString()
        return view
    }

    override fun getGroup(groupPosition: Int): Any = groups[groupPosition]
    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()
    override fun getGroupCount(): Int = groups.size
    override fun getChild(groupPosition: Int, childPosition: Int): Any = childs[childPosition]
    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()
    override fun getChildrenCount(groupPosition: Int): Int = childs.size
    override fun hasStableIds(): Boolean = false
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true


}