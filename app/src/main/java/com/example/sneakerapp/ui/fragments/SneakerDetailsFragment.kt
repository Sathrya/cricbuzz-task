package com.example.sneakerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sneakerapp.R
import com.example.sneakerapp.databinding.FragmentSneakerDetailsBinding
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SneakerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSneakerDetailsBinding
    private val sharedVm : SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSneakerDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        drawUI()
    }

    private fun drawUI() {

        binding.name.text = sharedVm.selectedSneaker?.name
        binding.brand.text = sharedVm.selectedSneaker?.brand
        binding.yearOfRelease.text = sharedVm.selectedSneaker?.year.toString()
        binding.price.text = "$" + sharedVm.selectedSneaker?.price.toString()

        binding.addToCartBtn.setOnClickListener {
            sharedVm.addToCart(
                CartItem(
                    0,
                    sharedVm.selectedSneaker?.id,
                    sharedVm.selectedSneaker?.name,
                    sharedVm.selectedSneaker!!.price
                )

            )
            Toast.makeText(requireContext(),requireContext().getText(R.string.added), Toast.LENGTH_SHORT).show()
        }

    }
}