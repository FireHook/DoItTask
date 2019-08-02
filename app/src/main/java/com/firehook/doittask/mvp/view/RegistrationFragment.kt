package com.firehook.doittask.mvp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.firehook.doittask.R
import com.firehook.doittask.constants.taskListKey
import com.firehook.doittask.mvp.presenter.AuthorizationPresenter
import com.firehook.doittask.network.model.Task
import kotlinx.android.synthetic.main.fragment_registration.*

/**
 * Created by Vladyslav Bondar on 21.07.2019
 * Skype: diginital
 */

class RegistrationFragment : MvpAppCompatDialogFragment(), AuthorizationView {

    @InjectPresenter(tag = "RegistrationFragment") lateinit var mPresenter : AuthorizationPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            if (!email_reg_edittext.text.isNullOrBlank() && !password_reg_edittext.text.isNullOrBlank())
                mPresenter.register(email_reg_edittext.text.toString(), password_reg_edittext.text.toString())
            else
                Toast.makeText(context, R.string.fill_the_info, Toast.LENGTH_LONG).show()
        }

        go_to_login_textview.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.authorization_frame, LoginFragment())?.commit()
        }
    }

    override fun openLoginScreen() {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.authorization_frame, LoginFragment())?.commit()
    }

    override fun openMainScreen(taskList: ArrayList<Task>) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putParcelableArrayListExtra(taskListKey, taskList)
        startActivity(intent)
    }

    override fun showNetworkError(message: String?) {
        Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show()
    }

    override fun startSplash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopSplash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}