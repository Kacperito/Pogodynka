package com.example.pogodynka.fregments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.pogodynka.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class LokalizacjaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lokalizacja, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textLokalizacja).text = lokal
        val input = view.findViewById<TextInputEditText>(R.id.inputMiasto)
        (view.findViewById<FloatingActionButton>(R.id.backFromLokalizacja)).setOnClickListener{

            findNavController().navigate(R.id.action_lokalizacjaFragment_to_mainFragment)

        }
        (view.findViewById<Button>(R.id.buttonZapiszLoka)).setOnClickListener{
            lokal = input.text.toString()
            view.findViewById<TextView>(R.id.textLokalizacja).text = lokal
        }
    }
}