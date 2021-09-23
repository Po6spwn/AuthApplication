package com.example.myapplication.authenticated

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.AuthenticatedFragmentBinding
import com.example.myapplication.util.InternetReceiver
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class AuthenticatedFragment : Fragment() {

    private lateinit var binding: AuthenticatedFragmentBinding
    private lateinit var viewModel: AuthenticatedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AuthenticatedFragmentBinding.inflate(inflater)
        val internetReceiver:InternetReceiver = InternetReceiver {viewModel.getValue()}
        requireActivity().registerReceiver(internetReceiver.receiver, internetReceiver.intentFilter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AuthenticatedViewModel::class.java)

        viewModel.liveDataCurrency.observe(requireActivity(), Observer {

            if (it.success != null) {
                Picasso.get().load(it.success.avatar.thumb)
                    .resize(200, 200) //to resize Image
                    .into(binding.userAvatar, object : Callback {
                        override fun onSuccess() {
                            binding.loadingProgressBar.visibility = View.GONE
                            binding.mainContainer.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            Snackbar.make(binding.mainFrameLayout, e.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    });
                binding.userName.text = it.success.nickName
            }
            else
                Snackbar.make(binding.mainFrameLayout, it.error.toString(), Snackbar.LENGTH_LONG).show()
        })
        viewModel.getValue()
    }
}