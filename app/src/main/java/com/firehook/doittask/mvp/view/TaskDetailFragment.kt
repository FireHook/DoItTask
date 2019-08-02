package com.firehook.doittask.mvp.view

import android.os.Build
import android.os.Bundle
import android.support.v7.content.res.AppCompatResources.getDrawable
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.constants.taskIdKey
import com.firehook.doittask.constants.taskKey
import com.firehook.doittask.mvp.presenter.MainPresenter
import com.firehook.doittask.network.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_task_details.*
import timber.log.Timber
import java.util.ArrayList

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class TaskDetailFragment : MvpAppCompatFragment(), MainView {

    @InjectPresenter (tag = "TaskDetailFragment") lateinit var mPresenter : MainPresenter

    private var mToolbar: Toolbar? = null
    private var mTask: Task? = null
    private var mTaskId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        mToolbar = activity?.toolbar
        mToolbar?.title = "Task Details"
        setHasOptionsMenu(true)

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setHomeButtonEnabled(true)
        activity?.toolbar?.setNavigationIcon(android.R.drawable.arrow_up_float)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar?.overflowIcon = activity?.getDrawable(android.R.drawable.ic_menu_edit)
        } else {
            //TODO Override options menu icon for earlier sdk
        }

        Timber.d("Data: %s", arguments?.getParcelable(taskKey))
        //mTask = arguments?.getParcelable(taskKey)
        //if (mTask != null) initView(mTask)
        mTask = arguments?.getParcelable(taskKey)
        val taskId = mTask?.id
        Timber.d("TaskId: %s", taskId)
        if (taskId != null) mPresenter.getTask(taskId)
        //app_bar.navigationIcon = resources.getDrawable(R.drawable.ic_go_arrow)

        task_detail_delete_button.setOnClickListener {
            val id = mTask?.id
            if (id != null) mPresenter.deleteTask(id)
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
                return true
            }
            R.id.action_edit -> {
                val bundle = Bundle()
                bundle.putParcelable(taskKey, mTask)
                val fragment = TaskEditFragment()
                fragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, fragment)?.addToBackStack(TaskEditFragment::class.java.simpleName)?.commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTaskLoaded(taskList: ArrayList<Task>) {
        Timber.d("Task loaded: %s", taskList[0])
        mTask = taskList[0]
        initView(taskList[0])
    }

    override fun closeScreen() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showNetworkError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun initView(task: Task?) {
        task_detail_name_textview.text = task?.name
        task_detail_priority_textview.text = task?.priority
        task_detail_expiration_textview.text = task?.expiration.toString()
    }
}