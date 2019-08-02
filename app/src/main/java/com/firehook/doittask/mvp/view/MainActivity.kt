package com.firehook.doittask.mvp.view

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.constants.taskListKey
import com.firehook.doittask.mvp.presenter.MainPresenter
import com.firehook.doittask.network.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter(tag = "MainActivity") lateinit var mPresenter: MainPresenter

    private lateinit var mTaskList: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        android:background="?attr/colorPrimary"

        mTaskList = intent.getParcelableArrayListExtra(taskListKey)

        val bundle = Bundle()
        bundle.putParcelableArrayList(taskListKey, mTaskList)
        val fragment = TaskListFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        Timber.d("Reminder clicked")
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, ReminderFragment())
            .addToBackStack(ReminderFragment::class.java.simpleName).commit()
//        if (supportFragmentManager.backStackEntryCount > 0)
//            supportFragmentManager.popBackStack()
//        else
//            finish() //TODO Open reminders screen
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            finish()
    }

    override fun openTaskListScreen(taskList: ArrayList<Task>) {
        val bundle = Bundle()
        bundle.putParcelableArrayList(taskListKey, taskList)
        val fragment = TaskListFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
    }

    override fun showNetworkError(message: String?) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show()
    }
}
