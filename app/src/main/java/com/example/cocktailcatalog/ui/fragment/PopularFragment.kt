package com.example.cocktailcatalog.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cocktailcatalog.R
import com.example.cocktailcatalog.databinding.FragmentPopularBinding
import com.example.cocktailcatalog.ui.adapter.CocktailAdapter
import com.example.cocktailcatalog.viewmodel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailViewModel by viewModels()
    private lateinit var adapter: CocktailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CocktailAdapter { cocktail ->
            val action = PopularFragmentDirections.actionPopularFragmentToCocktailDetailFragment(cocktail.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) { cocktails ->
            adapter.submitList(cocktails)
        }
        viewModel.loadPopularDrinks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as? SearchView)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchCocktails(it) }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchCocktails(it) }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
