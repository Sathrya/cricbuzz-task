package com.example.sneakerapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakerapp.R
import com.example.sneakerapp.data.SelectedSneaker
import com.example.sneakerapp.data.Sneakeritem
import com.example.sneakerapp.databinding.FragmentSneakerListBinding
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.ui.adapters.SneakerListsAdapter
import com.example.sneakerapp.ui.viewmodel.SharedViewModel
import com.example.sneakerapp.ui.viewmodel.SneakerListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SneakerListFragment : Fragment() {


    lateinit var binding: FragmentSneakerListBinding
    private val vm : SneakerListViewModel by viewModel()
    private val sharedViewModel : SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSneakerListBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObservers()
    }

    private fun setUpObservers() {
        vm.getAllSneakers()
        vm.sneakerList.observe(viewLifecycleOwner) {
            drawUI(it)
        }
    }


    private fun drawUI(sneakeritems: List<Sneakeritem>) {
        val adapter = SneakerListsAdapter( this::onSneakerSelected, this::onAddtoCardClicked)
        adapter.setItems(sneakeritems)
        binding.sneakersContainer.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),2)
            this.adapter = adapter
        }
    }

    private fun onSneakerSelected(id: String?,name: String?,brand: String?,price: Int,year: Int?) {
        val selectedItem = SelectedSneaker(id,name,brand,price,year)
        sharedViewModel.setSneaker(selectedItem)
        findNavController().navigate(R.id.action_sneakerListFragment_to_sneakerDetailsFragment)
    }

    private fun onAddtoCardClicked(id: String?, name: String? , price: Int){
        val item = CartItem(0,id,name,price)
        sharedViewModel.addToCart(item)
        Toast.makeText(requireContext(),requireContext().getText(R.string.added),Toast.LENGTH_SHORT).show()
    }



}