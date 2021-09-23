package com.example.myapplication.unidentified

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.authenticated.AuthenticatedFragment
import com.example.myapplication.databinding.UnidentifiedFragmentBinding
import com.google.android.material.snackbar.Snackbar

class Unidentified : Fragment() {

    private lateinit var binding: UnidentifiedFragmentBinding

    private lateinit var viewModel: UnidentifiedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UnidentifiedFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UnidentifiedViewModel::class.java)

        viewModel.liveDataCurrency.observe(requireActivity(), {
            if(it)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainView, AuthenticatedFragment())
                    .addToBackStack(null)
                    .commit()
            else{
                Snackbar.make(binding.unidLayout,"Ошибка интернет подключения!", Snackbar.LENGTH_LONG).show()
            }
        })

        binding.buBegin.setOnClickListener {
            viewModel.isInternetAvailable(requireContext())
        }
    }

}