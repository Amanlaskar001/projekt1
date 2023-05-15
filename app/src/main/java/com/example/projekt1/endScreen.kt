package com.example.projekt1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class endScreen : Fragment() {




    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_end_screen, container, false)

        val homeBtn: Button = view.findViewById(R.id.homeButton)
        val restartBtn: Button = view.findViewById(R.id.restartButton)

        homeBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_endScreen_to_homeScreen)
        }

        restartBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_endScreen_to_firstQ)
        }


        return view
    }


}