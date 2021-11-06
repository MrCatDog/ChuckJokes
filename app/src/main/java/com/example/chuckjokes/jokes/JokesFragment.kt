package com.example.chuckjokes.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.chuckjokes.R
import androidx.recyclerview.widget.RecyclerView
import com.example.chuckjokes.Shared
import com.example.chuckjokes.databinding.JokesFragmentBinding
import com.example.chuckjokes.main.MainActivity
import com.example.chuckjokes.viewModelsExt

private const val VISIBLE_THRESHOLD = 5

class JokesFragment : Fragment() {

    private lateinit var binding: JokesFragmentBinding
    private lateinit var recyclerAdapter: RecyclerAdapter

    private val viewModel by viewModelsExt {
        JokesViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = JokesFragmentBinding.inflate(inflater)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.JokesList.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter(this)
        binding.JokesList.adapter = recyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(binding.JokesList.context, linearLayoutManager.orientation)
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_item_shape, null)!!)
        binding.JokesList.addItemDecoration(dividerItemDecoration)

        binding.JokesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(unused: RecyclerView, dx: Int, dy: Int) {
                //вот это явно логика, но как её убрать отсюда? этот объект будет требовать постоянного проброса значений с linearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD > linearLayoutManager.itemCount) {
                    viewModel.onScrolledToEnd()
                }
            }
        })

        viewModel.jokes.observe(viewLifecycleOwner) {
            recyclerAdapter.setData(it, JokesConstants.JOKES_VALUE)
        }

        viewModel.exceptionBundle.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).changeFragmentByDirection(Shared.Direction.ERROR, it)
        }

        viewModel.onScrolledToEnd()

        return binding.JokesList
    }
}