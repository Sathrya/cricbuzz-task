package com.example.sneakerapp.ui.fragments

import android.os .Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sneakerapp.R
import com.example.sneakerapp.databinding.FragmentCartBinding
import com.example.sneakerapp.db.CartItem
import com.example.sneakerapp.ui.adapters.CartListAdapter
import com.example.sneakerapp.utils.concatenateCurrency
import com.example.sneakerapp.ui.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    private val vm : CartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getCartItems()
        setUpObservers()
    }

    private fun setUpObservers() {
        vm.cartItems.observe(viewLifecycleOwner){
            if (it.isNotEmpty()) {
                binding.empty.visibility = View.GONE
                drawUI(it)
            }
            else {
                hideViews()
            }
        }
    }

    private fun hideViews() {
        binding.priceDetails.visibility = View.GONE
        binding.taxDetails.visibility = View.GONE
        binding.totalDetails.visibility = View.GONE
        binding.checkout.visibility = View.GONE
        binding.orderDetails.visibility = View.GONE

        binding.empty.visibility = View.VISIBLE
    }

    private fun drawUI(it: MutableList<CartItem>) {
        val adapter = CartListAdapter( this::onRemoveClicked)
        adapter.setCart(it)
        binding.cartContainer.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            this.adapter = adapter
        }

        binding.totalPrice.text = concatenateCurrency(vm.calculateCartItemsCost())
        binding.tax.text = context?.getText(R.string.taxValue)
        binding.totalPriceWithTax.text = concatenateCurrency(vm.totalPriceWithTax())
    }

    private fun onRemoveClicked(id: Int,sneakerId:String?,name:String?,price : Int) {
        val cartItemToDelete = CartItem (
            sid = id,
            name = name,
            id = sneakerId,
            price = price
        )
        vm.removeItemFromCart(cartItemToDelete)
        binding.loadingbar.visibility = View.VISIBLE

        Handler().postDelayed({
            refreshList()
        },3000)

        Handler().postDelayed({
            getCartItems()
        },3000)

    }

    private fun refreshList() {
        vm.cartItems
        binding.loadingbar.visibility = View.GONE
        Toast.makeText(requireContext(),requireContext().getText(R.string.removed), Toast.LENGTH_SHORT).show()
        setUpObservers()
    }

    private fun getCartItems() {
        vm.getCartItems()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


}