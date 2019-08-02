package com.firehook.doittask.mvp.view

import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.adapter.TaskAdapter
import com.firehook.doittask.constants.*
import com.firehook.doittask.mvp.presenter.MainPresenter
import com.firehook.doittask.network.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task_list.*
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Vladyslav Bondar on 28.07.2019
 * Skype: diginital
 */

class TaskListFragment : MvpAppCompatFragment(), MainView, SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter(tag = "TaskListFragment") lateinit var mPresenter: MainPresenter

    private var mToolbar: Toolbar? = null
    private var mTaskList = arrayListOf<Task>()
    private var mAdapter = TaskAdapter(mTaskList)

    private val mTaskItemClickListener = object : TaskAdapter.OnTaskClickListener {
        override fun onTaskClicked(task: Task) {
            val bundle = Bundle()
            bundle.putParcelable(taskKey, task)
            val fragment = TaskDetailFragment()
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.content_frame, fragment)?.addToBackStack(TaskDetailFragment::class.java.simpleName)?.commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        mToolbar = activity?.toolbar
        mToolbar?.title = "My Tasks"
        setHasOptionsMenu(true)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setHomeButtonEnabled(true)

        recycler_view.layoutManager = LinearLayoutManager(context)

        mPresenter.refreshTasks()
        activity?.toolbar?.setNavigationIcon(android.R.drawable.ic_popup_reminder)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar?.overflowIcon = activity?.getDrawable(android.R.drawable.ic_menu_sort_by_size)
        } else {
            //TODO Override options menu icon for earlier sdk
        }

        floating_button.setOnClickListener {
            mPresenter.createTask()
        }

        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
        if (mAdapter != null) mAdapter.setItemClickListener(mTaskItemClickListener)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onStop() {
        super.onStop()
        if (mAdapter != null) mAdapter.setItemClickListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                Timber.d("HOME CLICK")
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, ReminderFragment())?.addToBackStack(ReminderFragment::class.java.simpleName)?.commit()
                true
            }
            R.id.action_name_asc -> {
                Timber.d("CLICK")
                mPresenter.sort(title, asc)
                true
            }
            R.id.action_name_desc -> {
                Timber.d("CLICK")
                mPresenter.sort(title, desc)
                true
            }
            R.id.action_priority_asc -> {
                Timber.d("CLICK")
                mPresenter.sort(priority, asc)
                true
            }
            R.id.action_priority_desc -> {
                mPresenter.sort(priority, desc)
                true
            }
            R.id.action_date_asc -> {
                mPresenter.sort(dueBy, asc)
                true
            }
            R.id.action_date_desc -> {
                mPresenter.sort(dueBy, desc)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        swipe_refresh.post {
            mPresenter.refreshTasks()
        }
    }

    override fun onTaskLoaded(taskList: ArrayList<Task>) {
        mTaskList.clear()
        mTaskList.addAll(taskList)
        recycler_view.adapter = mAdapter
        swipe_refresh.isRefreshing = false
    }

    override fun notifyRecyclerChanged(task: Task) {
        mPresenter.refreshTasks()
    }

    override fun showNetworkError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}