package com.moremoregreen.androidkotlinvolley

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IVolley {
    override fun onResponse(response: String) {
        Toast.makeText(this@MainActivity,""+response,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .getRequest("https://jsonplaceholder.typicode.com/todos/1")
        }
        btn_post.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .postRequest("https://jsonplaceholder.typicode.com/posts")
        }

        btn_put.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .putRequest("https://jsonplaceholder.typicode.com/posts/1")
        }
        btn_patch.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .patchRequest("https://jsonplaceholder.typicode.com/posts/1")
        }
        btn_delete.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .deleteRequest("https://jsonplaceholder.typicode.com/posts/1")
        }
        btn_fet_image.setOnClickListener {
            image_view.setImageUrl("http://zh-tw.lc742.com/uploadfiles/380/news/new-year-2018.png",
                MyVolleyRequest.getInstance(this@MainActivity).imageLoader)
        }
    }
}
