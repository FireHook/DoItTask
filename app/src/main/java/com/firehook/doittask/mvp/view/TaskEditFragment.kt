package com.firehook.doittask.mvp.view

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.constants.taskKey
import com.firehook.doittask.mvp.presenter.MainPresenter
import com.firehook.doittask.network.model.RequestTask
import com.firehook.doittask.network.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_edit_task.*
import timber.log.Timber

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class TaskEditFragment : MvpAppCompatFragment(), MainView {

    @InjectPresenter(tag = "TaskEditFragment") lateinit var mPresenter: MainPresenter

    private var mToolbar: Toolbar? = null
    private var mTask: Task? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mToolbar = activity?.toolbar
        mToolbar?.title = "Edit Task"
        mTask = arguments?.getParcelable(taskKey)

        Timber.d("Task: %s", mTask)

        activity?.toolbar?.setNavigationIcon(android.R.drawable.arrow_up_float)

        task_edit_delete_button.setOnClickListener {
            mPresenter.editTask(mTask?.id, task_edit_title.text.toString(), task_edit_notification.text.toString(), radioGroup.indexOfChild(activity?.findViewById(radioGroup.checkedRadioButtonId)))
        }
    }

    override fun closeScreen() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showNetworkError(message: String?) {}
}