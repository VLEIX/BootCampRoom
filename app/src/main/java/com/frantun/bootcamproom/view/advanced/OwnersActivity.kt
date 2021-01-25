package com.frantun.bootcamproom.view.advanced

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.frantun.bootcamproom.adapter.*
import com.frantun.bootcamproom.application.WordsApplication
import com.frantun.bootcamproom.databinding.ActivityOwnerBinding

class OwnersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerBinding

    //    private lateinit var dogAndOwnerAdapter: DogAndOwnerAdapter
//private lateinit var catsAndOwnerAdapter: CatsAndOwnerAdapter
    private lateinit var birdsAndOwnerAdapter: BirdsAndOwnerAdapter

    lateinit var viewModelFactory: OwnersViewModelFactory
    lateinit var viewModel: OwnersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = OwnersViewModelFactory((application as WordsApplication).repositoryOwner)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OwnersViewModel::class.java)

        binding = ActivityOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1:1
//        dogAndOwnerAdapter = DogAndOwnerAdapter(DogAndOwnerListener { dogAndOwner ->
//            Log.d("OwnersActivity", dogAndOwner.toString())
//        })
//        binding.ownersRecycler.apply {
//            adapter = dogAndOwnerAdapter
//            layoutManager = LinearLayoutManager(this@OwnersActivity)
//        }
//
//        viewModel.dogsAndOwners.observe(this) { dogsAndOwners ->
//            dogsAndOwners.let {
//                dogAndOwnerAdapter.submitList(it)
//            }
//        }

        // 1:*
//        catsAndOwnerAdapter = CatsAndOwnerAdapter(CatsAndOwnerListener { catsAndOwner ->
//            Log.d("OwnersActivity", catsAndOwner.toString())
//        })
//        binding.ownersRecycler.apply {
//            adapter = catsAndOwnerAdapter
//            layoutManager = LinearLayoutManager(this@OwnersActivity)
//        }
//
//        viewModel.catsAndOwners.observe(this) { catsAndOwners ->
//            catsAndOwners.let {
//                catsAndOwnerAdapter.submitList(it)
//            }
//        }

        // *:*
        birdsAndOwnerAdapter = BirdsAndOwnerAdapter(BirdsAndOwnerListener { birdsAndOwner ->
            Log.d("OwnersActivity", birdsAndOwner.toString())
        })
        binding.ownersRecycler.apply {
            adapter = birdsAndOwnerAdapter
            layoutManager = LinearLayoutManager(this@OwnersActivity)
        }

        viewModel.birdsAndOwners.observe(this) { birdsAndOwner ->
            birdsAndOwner.let {
                birdsAndOwnerAdapter.submitList(it)
            }
        }
    }
}