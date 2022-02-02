package com.example.chuckjokes.fragments.jokes

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
import com.example.chuckjokes.databinding.JokesFragmentBinding
import com.example.chuckjokes.main.MainActivity
import com.example.chuckjokes.util.viewModelsExt

class JokesFragment : Fragment() {

    private var _binding: JokesFragmentBinding? = null
    private val binding
        get() = _binding!!

    //TODO: это зачем сюда вынесено?
    private lateinit var recyclerAdapter: RecyclerAdapter

    private val viewModel by viewModelsExt {
        JokesViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = JokesFragmentBinding.inflate(inflater)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.JokesList.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter()
        binding.JokesList.adapter = recyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(binding.JokesList.context, linearLayoutManager.orientation)
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider_item_shape, null)!!)
        binding.JokesList.addItemDecoration(dividerItemDecoration)

        binding.JokesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(unused: RecyclerView, dx: Int, dy: Int) {
                viewModel.onScrolledToEnd(linearLayoutManager.findLastVisibleItemPosition(), linearLayoutManager.itemCount)
            }
        })

        viewModel.jokes.observe(viewLifecycleOwner) {
            recyclerAdapter.setData(it, JOKES_VALUE)
        }

        viewModel.exception.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).setErrorFragment(it)
        }

        return binding.JokesList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}