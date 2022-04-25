package com.example.rickmorty.presentation.chars_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.rickmorty.databinding.FragmentCharsListBinding
import com.example.rickmorty.di.DaggerCharsFragmentComponent
import com.example.rickmorty.di.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharsListFragment : Fragment() {
    private var _binding: FragmentCharsListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: CharsListViewModel? = null

    private var adapter: CharsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerCharsFragmentComponent.builder().build().inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[CharsListViewModel::class.java]
        _binding = FragmentCharsListBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectUiState()
    }

    private fun initView() {
        adapter = CharsListAdapter() { char ->
            val action =
                CharsListFragmentDirections.actionCharsListFragmentToCharDetailsFragment(char)
            findNavController().navigate(action)
        }
        binding.charsList.adapter = adapter
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter?.loadStateFlow?.collectLatest { loadStates ->
                when {
                    loadStates.refresh is LoadState.Loading || loadStates.append is LoadState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    loadStates.refresh is LoadState.Error -> {
                        (loadStates.refresh as LoadState.Error).error.localizedMessage?.let {
                            showToast(
                                it,Toast.LENGTH_LONG)
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                    else -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel?.flow?.collectLatest { chars ->
                adapter?.submitData(chars)
            }
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}