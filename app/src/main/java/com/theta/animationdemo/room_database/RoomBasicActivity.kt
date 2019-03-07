package com.theta.animationdemo.room_database

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.gson.Gson
import com.theta.animationdemo.ParallaxViewPagerDemo.Log
import kotlinx.android.synthetic.main.activity_room_basic.*
import kotlinx.android.synthetic.main.content_room_basic.*
import org.json.JSONArray


class RoomBasicActivity : AppCompatActivity() {

    lateinit var adapter: WordListAdapter
    private var mWordViewModel: WordViewModel? = null
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.theta.animationdemo.R.layout.activity_room_basic)
        setSupportActionBar(toolbar)

        adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        getData()

        fab.setOnClickListener { view ->
            val intent = Intent(this@RoomBasicActivity, RoomActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

        mWordViewModel!!.getAllWords().observe(this, Observer<List<Word>> { words ->
            // Update the cached copy of the words in the adapter.
            adapter.setWords(words)
        })

        search_btn.setOnClickListener {
            val query = search_box.text
            if (query == null || query.toString().isEmpty()) {
                subscribeUi(mWordViewModel!!.allWords)
            } else {
                subscribeUi(mWordViewModel!!.searchWord(query.toString()))
            }
            val thread = Thread(Runnable {
                val appDb = WordRoomDatabase.getDatabase(this)
                val dao = appDb.wordDao()
                val data = dao.searchWords(query.toString())
                Log.e(data.toString())
            })
            thread.start()
        }
    }

    private fun subscribeUi(liveData: LiveData<List<Word>>) {
        liveData.observe(this, Observer { words ->
            Log.e(words!!.size.toString())
            adapter.setWords(words)
        })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data.getStringExtra(RoomActivity.EXTRA_REPLY))
            mWordViewModel!!.insert(word)
        } else {
            Toast.makeText(
                    applicationContext,
                    com.theta.animationdemo.R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun getData() {
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts/")
                .setTag("profile")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray?) {
                        val array = response
                        val dataArray: ArrayList<ApiResponse> = ArrayList()
                        for (i in 0 until array!!.length()) {
                            val data = Gson().fromJson(array[i].toString(), ApiResponse::class.java)
                            dataArray.add(data)
                            mWordViewModel!!.insert(Word(data.title))
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(this@RoomBasicActivity, anError.toString(), Toast.LENGTH_LONG).show()
                    }

                })
    }

}
