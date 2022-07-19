package com.example.medlineinfo

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appnetwork = BasicNetwork(HurlStack())
        // appnetwork allows the app to use HTTP clients
        val appcache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        val description = findViewById<TextView>(R.id.description)
        val button = findViewById<Button>(R.id.button)
        val clearButton = findViewById<Button>(R.id.clearButton)
        var test1 = " "
        var test2 = " "
        var test3 = " "
        var test4 = " "

        val requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }
        button.setOnClickListener { //allows search button to search when checkbox is clicked
            if (checkbox.isChecked)
                test1 = // change value of empty variables if clicked
                    "TYPE 1 DIABETES: \na disorder characterized by abnormally high blood sugar levels. In this form of diabetes, specialized cells in the pancreas called beta cells stop producing insulin. Insulin controls how much glucose (a type of sugar) is passed from the blood into cells for conversion to energy. Lack of insulin results in the inability to use glucose for energy or to control the amount of sugar in the blood."
            if (checkbox2.isChecked)
                test2 =
                    "PULMONARY HYPERTENSION (PH): \nhigh blood pressure in the arteries to your lungs. It is a serious condition. If you have it, the blood vessels that carry blood from your heart to your lungs become hard and narrow. Your heart has to work harder to pump the blood through. Over time, your heart weakens and cannot do its job and you can develop heart failure."
            if (checkbox3.isChecked)
                test3 =
                    "ALZHEIMERS: \na degenerative disease of the brain that causes dementia, which is a gradual loss of memory, judgment, and ability to function. This disorder usually appears in people older than age 65, but less common forms of the disease appear earlier in adulthood. Memory loss is the most common sign of Alzheimer disease. Forgetfulness may be subtle at first, but the loss of memory worsens over time until it interferes with most aspects of daily living. Even in familiar settings, a person with Alzheimer disease may get lost or become confused. Routine tasks such as preparing meals, doing laundry, and performing other household chores can be challenging."
            if (checkbox4.isChecked)
                test4 =
                    "SYSTEMIC LUPUS ERYTHEMATOSUS (SLE): \na chronic disease that causes inflammation in connective tissues, such as cartilage and the lining of blood vessels, which provide strength and flexibility to structures throughout the body. The signs and symptoms of SLE vary among affected individuals, and can involve many organs and systems, including the skin, joints, kidneys, lungs, central nervous system, and blood-forming (hematopoietic) system. SLE is one of a large group of conditions called autoimmune disorders that occur when the immune system attacks the body's own tissues and organs. SLE may first appear as extreme tiredness (fatigue), a vague feeling of discomfort or illness (malaise), fever, loss of appetite, and weight loss."
                description.setText("$test1 \n$test2 \n$test3 \n$test4") // print all values
        }
        clearButton.setOnClickListener{
            test1 = " " // resetting all values to empty
            test2 = " "
            test3 = " "
            test4 = " "
            description.setText(" ")
            if (checkbox.isChecked)
                checkbox.toggle()
            if (checkbox2.isChecked)
                checkbox2.toggle()
            if (checkbox3.isChecked)
                checkbox3.toggle()
            if (checkbox4.isChecked)
                checkbox4.toggle()
        }
    }
}