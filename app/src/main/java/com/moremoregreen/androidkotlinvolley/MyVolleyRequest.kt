package com.moremoregreen.androidkotlinvolley

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.LruCache

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MyVolleyRequest {
    private var mRequestQueue: RequestQueue? = null
    private var context: Context? = null
    private var iVolley: IVolley? = null
    var imageLoader: ImageLoader? = null
        private set
    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null)
                mRequestQueue = Volley.newRequestQueue(context!!.applicationContext)
            return mRequestQueue!!
        }

    companion object {
        private var mInstance : MyVolleyRequest? = null
        @Synchronized
        fun getInstance(context: Context):MyVolleyRequest{
            if (mInstance == null){
                mInstance = MyVolleyRequest(context)
            }
            return mInstance!!
        }

        @Synchronized
        fun getInstance(context: Context,iVolley: IVolley):MyVolleyRequest{
            if (mInstance == null){
                mInstance = MyVolleyRequest(context,iVolley)
            }
            return mInstance!!
        }
    }

    private constructor(context: Context, iVolley: IVolley) {
        this.context = context
        this.iVolley = iVolley
        mRequestQueue = requestQueue
        this.imageLoader = ImageLoader(mRequestQueue, object : ImageLoader.ImageCache {
            private val mCache = LruCache<String, Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap? {
                return mCache.get(url!!)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url!!, bitmap!!)
            }

        })
    }

    private constructor(context: Context) {
        this.context = context
        mRequestQueue = requestQueue
        this.imageLoader = ImageLoader(mRequestQueue, object : ImageLoader.ImageCache {
            private val mCache = LruCache<String, Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap? {
                return mCache.get(url!!)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url!!, bitmap!!)
            }

        })
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

    //GET
    fun getRequest(url: String) {
        val getRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            iVolley!!.onResponse(response.toString())
        }, Response.ErrorListener { error ->
            iVolley!!.onResponse(error.message!!)
        })
        addToRequestQueue(getRequest)
    }

    //Post with params
    fun postRequest(url: String) {
        val postRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                iVolley!!.onResponse(response.toString())
            }, Response.ErrorListener { error ->
                iVolley!!.onResponse(error.message!!)
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["name"] = "MMG"
                params["value"] = "這是從kotlin App post 的"
                return params
            }
        }
        addToRequestQueue(postRequest)
    }

    //Put with params
    fun putRequest(url: String) {
        val putRequest = object : StringRequest(Request.Method.PUT, url,
            Response.Listener { response ->
                iVolley!!.onResponse(response.toString())
            }, Response.ErrorListener { error ->
                iVolley!!.onResponse(error.message!!)
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["name"] = "MMG"
                params["value"] = "這是從kotlin App put 的"
                return params
            }
        }
        addToRequestQueue(putRequest)
    }

    //PATCH with params
    fun patchRequest(url: String) {
        val patchRequest = object : StringRequest(Request.Method.PATCH, url,
            Response.Listener { response ->
                iVolley!!.onResponse(response.toString())
            }, Response.ErrorListener { error ->
                iVolley!!.onResponse(error.message!!)
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["name"] = "MMG"
                params["value"] = "這是從kotlin App patch 的"
                return params
            }
        }
        addToRequestQueue(patchRequest)
    }

    //Delete with params
    fun deleteRequest(url: String) {
        val deleteRequest = StringRequest(Request.Method.DELETE, url,
            Response.Listener { response ->
                iVolley!!.onResponse(response)
            }, Response.ErrorListener { error ->
                iVolley!!.onResponse(error.message!!)
            })

        addToRequestQueue(deleteRequest)
    }


}