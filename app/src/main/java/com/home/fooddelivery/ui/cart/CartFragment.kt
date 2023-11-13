package com.home.fooddelivery.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.home.fooddelivery.databinding.FragmentCartBinding
import com.home.fooddelivery.ui.MealApplication
import com.home.fooddelivery.ui.ViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (requireActivity().application as MealApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var cartViewModel: CartViewModel

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel =
            ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]

        val textView: TextView = binding.textCart
        cartViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}