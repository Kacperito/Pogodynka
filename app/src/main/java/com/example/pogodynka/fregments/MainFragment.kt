package com.example.pogodynka.fregments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.pogodynka.MainActivity
import com.example.pogodynka.R



class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textMiasto).text = lokal
        val switcheroo = view.findViewById<Switch>(R.id.switch1)

        (view.findViewById<Button>(R.id.buttonUstawLokalizacje)).setOnClickListener{

            findNavController().navigate(R.id.action_mainFragment_to_lokalizacjaFragment)

        }
        (view.findViewById<Button>(R.id.buttonDzis)).setOnClickListener{
            if(switcheroo.isChecked)
                findNavController().navigate(R.id.action_mainFragment_to_dzienoldFragment)
            else
            findNavController().navigate(R.id.action_mainFragment_to_dzienFragment)

        }

    }
}