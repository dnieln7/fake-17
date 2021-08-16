package com.dnieln7.fake17.ui.home.cat

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.fake17.Fake17Application
import com.dnieln7.fake17.R
import com.dnieln7.fake17.databinding.CatsFragmentBinding
import com.dnieln7.fake17.domain.Cat
import com.dnieln7.fake17.utils.NavigationUtils.navigate

class CatsFragment : Fragment() {
    private var _binding: CatsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatsFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        val serviceLocator = (requireActivity().application as Fake17Application).serviceLocator

        viewModel = ViewModelProvider(
            this,
            CatsViewModel.Factory(serviceLocator.catRepository)
        ).get(CatsViewModel::class.java)

        binding.refresh.setOnRefreshListener { viewModel.fetchCats() }
        binding.items.setHasFixedSize(true)

        viewModel.apiState.observe(viewLifecycleOwner, {
            when (it) {
                is ApiState.Error -> {
                    with(binding) {
                        errorMessage.text = it.error

                        refresh.isRefreshing = false
                        progress.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        items.visibility = View.GONE
                    }
                }
                ApiState.Loading -> binding.progress.visibility = View.VISIBLE
                ApiState.Success -> {
                    with(binding) {
                        refresh.isRefreshing = false
                        progress.visibility = View.GONE
                        error.visibility = View.GONE
                        items.visibility = View.VISIBLE
                    }
                }
            }
        })
        viewModel.cats.observe(viewLifecycleOwner, {
            binding.items.adapter = CatListAdapter(it) { cat -> toDetails(cat) }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cats, menu)

        val searchView = menu.findItem(R.id.search_action).actionView as SearchView

        searchView.apply {
            isIconifiedByDefault = true
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            queryHint = getString(R.string.search_by_country)

            setOnQueryTextFocusChangeListener { _, hasFocus ->
                if(!hasFocus && searchView.query.isBlank()) {
                    searchView.isIconified = true
                }
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        viewModel.filterByKeyword(newText)
                    }
                    return true
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun toDetails(cat: Cat) {
        CatsFragmentDirections.actionCatsFragmentToCatDetailFragment(cat).navigate(binding.root)
    }
}