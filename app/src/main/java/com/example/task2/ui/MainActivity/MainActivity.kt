package com.example.task2.ui.MainActivity

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task2.R
import com.example.task2.api.ApiManager
import com.example.task2.api.model.ReposResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import mahmoud.moussa.newsapp.database.ReposDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainActivityViewModel by viewModels();
    val adapter = RecyclerAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainActivityViewModel::class.java);
        subscribeToLiveData()
        viewModel.getReposFromApi();
        recyclerview.adapter = adapter



        adapter.onItemClick = object :
            RecyclerAdapter.OnRecyclerItemClickListener {
            override fun onClick(item: ReposResponse.ReposResponseItem?) {
                val builder = AlertDialog.Builder(this@MainActivity);
                val dialog = builder.setTitle("Item Clicked")
                    .setMessage("Where are u want to go?")
                    .setPositiveButton("go to repository",
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(item?.owner?.reposUrl))
                                this@MainActivity.startActivity(browserIntent)
                            }

                        })
                    .setNegativeButton("go to owner", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(item?.owner?.url))
                            this@MainActivity.startActivity(browserIntent)
                        }

                    })
                    .setCancelable(true)
                    .create();
                dialog.show()
            }

        }

    }

    private fun subscribeToLiveData() {
        viewModel.reposResponseLiveData.observe(this, Observer {
            adapter.changeData(it)
        })
        viewModel.reposErrLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })


    }
}