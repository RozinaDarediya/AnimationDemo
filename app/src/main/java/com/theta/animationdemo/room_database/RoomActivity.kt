package com.theta.animationdemo.room_database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import com.theta.animationdemo.R
import kotlinx.android.synthetic.main.activity_room.*


class RoomActivity : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    private var mEditWordView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        mEditWordView = findViewById(R.id.edit_word)

        val button = findViewById<View>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word.getText().toString())) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }
}
