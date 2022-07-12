package com.example.medlineinfo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initiating a requestQueue after setting a content view
        val appnetwork = BasicNetwork(HurlStack())
        // appnetwork allows the app to use HTTP clients
        val appcache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }
        //click listener allows user input to call the API
        search.setOnClickListener {
            var input = userinput.text.toString()
            // input variable stores users search term. this value is then passed to the fetchData method as a parameter.
            fetchData(input)
        }
    }
            //the fetchData function requires a string (user’s input) as a parameter. this string is then joined to the url as =${input}
    fun fetchData(input: String) {
        val url = "https://medlineplus.gov/download/genetics/condition/${input}-disease.json"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                //if the request is successful, we extract the data and parse it to the components
                if (response.get("Response") == "False")
                    name.text = "Incorrect detail" else {
                    name.text =
                        response.getString("Condition")
                }
            },
            //error message is also logged in case the network request fails
            { error ->
                Log.d("vol", error.toString())
            }
        )
            //the jsonObjectRequest is added to the requestQueue.
        requestQueue.add(jsonObjectRequest)
    }
}