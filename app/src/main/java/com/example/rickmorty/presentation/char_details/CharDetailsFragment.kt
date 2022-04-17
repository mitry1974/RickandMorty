package com.example.rickmorty.presentation.char_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickmorty.databinding.FragmentCharDetailsBinding
import com.example.rickmorty.di.DaggerCharsFragmentComponent
import com.example.rickmorty.domain.models.Character
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharDetailsFragment : Fragment() {
    private var _binding: FragmentCharDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharDetailsViewModel

    @Inject
    lateinit var viewModelFactory: CharDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerCharsFragmentComponent.builder().build().inject(this)

        val char = arguments?.get("selectedChar") as Character
        viewModel = viewModelFactory.create(char)

        _binding = FragmentCharDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            Picasso.Builder(requireActivity()).build().load(viewModel.char.image)
                .into(charDetailsImage)
            charStatus.text = viewModel.char.status
            charName.text = viewModel.char.name
            charGender.text = viewModel.char.gender
            charSpecies.text = viewModel.char.species
            charLocation.text = viewModel.char.location
            charEpisodesCount.text = viewModel.char.episodes.size.toString()
        }
    }
}