package com.example.cocktailcatalog.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cocktailcatalog.databinding.FragmentCocktailDetailBinding
import com.example.cocktailcatalog.viewmodel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailFragment : Fragment() {

    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Retrieve cocktailId argument (using Safe Args or Bundle key)
        val cocktailId = arguments?.getString("cocktailId") ?: ""
        viewModel.loadCocktailDetail(cocktailId)
        viewModel.cocktailDetail.observe(viewLifecycleOwner) { detail ->
            binding.tvName.text = detail.name
            binding.tvCategory.text = detail.category
            binding.tvAlcoholic.text = detail.alcoholic
            binding.tvInstructions.text = detail.instructions

            // Combine available ingredients
            val ingredients = listOfNotNull(
                detail.ingredient1,
                detail.ingredient2,
                detail.ingredient3,
                detail.ingredient4,
                detail.ingredient5
            )
            binding.tvIngredients.text = "Ingredients: ${ingredients.joinToString(", ")}"
            Glide.with(requireContext()).load(detail.thumbnail).into(binding.ivCocktail)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
