package com.firehook.doittask.mvp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.constants.taskListKey
import com.firehook.doittask.mvp.presenter.AuthorizationPresenter
import com.firehook.doittask.network.model.Task
import java.util.ArrayList

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class AuthorizationActivity : MvpAppCompatActivity(), AuthorizationView {

    @InjectPresenter(tag = "AuthorizationActivity") lateinit var mPresenter : AuthorizationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        mPresenter.auth()
    }

    override fun openMainScreen(taskList: ArrayList<Task>) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putParcelableArrayListExtra(taskListKey, taskList)
        startActivity(intent)
    }

    override fun openLoginScreen() {
        supportFragmentManager.beginTransaction().replace(R.id.authorization_frame, LoginFragment()).commit()
    }

    override fun openRegistrationScreen() {
        supportFragmentManager.beginTransaction().replace(R.id.authorization_frame, RegistrationFragment()).commit()
    }

    override fun showNetworkError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun startSplash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopSplash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}