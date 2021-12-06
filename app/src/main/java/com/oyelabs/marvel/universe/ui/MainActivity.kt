package com.oyelabs.marvel.universe.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.ActivityMainBinding
import com.oyelabs.marvel.universe.domain.model.CharacterModel
import com.oyelabs.marvel.universe.ui.CharactersList.CharactersViewModel
import com.oyelabs.marvel.universe.utils.CharacterListAdapter
import com.oyelabs.marvel.universe.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.crypto.AEADBadTagException

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var searchTerm : String
    var valueRepeat = 3
    var paginatedValue = 0
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var layoutManager : GridLayoutManager
    private val viewModel : CharactersViewModel by viewModels()
    var list = arrayListOf<CharacterModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.characterRecyclerView
        layoutManager = GridLayoutManager(this,2)
        recyclerViewCharacters()
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1)
                {
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callAPI()
                }
            }
        })
        Log.d("tag", Constants.timeStamp)
    }
    private fun callAPI(){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(valueRepeat){
                viewModel._marvelValue.collect{value->
                    when {
                        value.isLoading -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                        value.error.isNotBlank() -> {
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            Toast.makeText(this@MainActivity, value.error, Toast.LENGTH_LONG).show()
                        }
                        value.charactersList.isNotEmpty() -> {
                            binding.progressCircular.visibility = View.GONE
                            valueRepeat = 0
                            adapter.setData(value.charactersList as ArrayList<CharacterModel>)
                        }
                    }
                    delay(1000)
                }
                }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu?.findItem(R.id.menuSearch)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewCharacters() {
        recyclerView = binding.characterRecyclerView
        adapter = CharacterListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            searchTerm = query
        }
        if (searchTerm.isNotEmpty()) {
            search()
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query!=null){
            searchTerm = query
        }
        if (searchTerm.isNotEmpty()) {
            search()
        }
        return true
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun search(){
        viewModel.getSearchedCharacters(searchTerm)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel._marvelValue.collect{
                when{
                    it.isLoading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    it.error.isNotBlank() -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                    it.charactersList.isNotEmpty() -> {
                        binding.progressCircular.visibility = View.GONE
                        adapter.setData(it.charactersList as ArrayList<CharacterModel>)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllCharactersData(paginatedValue)
        callAPI()
    }
}