package com.predator.theeagle.astroids.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.predator.theeagle.astroids.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView()
        navigateToDetailsFragment()
    }

    private fun navigateToDetailsFragment() {
        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner) { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.actionNavigateToDetail(it))
                viewModel.onDetailFragmentNavigated()
            }
        }
    }

    private fun setUpRecyclerView() {
        val adapter = AsteroidListAdapter(AsteroidItemClickListener { asteroidId ->
            viewModel.onAsteroidItemClick(asteroidId)
        })
        binding.asteroidRV.adapter = adapter
        viewModel.asteroids.observe(viewLifecycleOwner) { asteroids ->
            adapter.submitList(asteroids)
        }
    }
}
