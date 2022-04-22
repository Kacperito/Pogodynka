package com.example.pogodynka.fregments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pogodynka.R

import com.example.pogodynka.viewModel.DayViewModel
import com.example.pogodynka.viewModel.WeekViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.Instant


class DzienOldFragment : Fragment() {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModel2: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dzienold, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textMiasto).text = lokal
        (view.findViewById<FloatingActionButton>(R.id.backFromDayOld)).setOnClickListener{

            findNavController().navigate(R.id.action_dzienoldFragment_to_mainFragment)

        }

        val textCis = view.findViewById<TextView>(R.id.textCisnienie)
        val textWschod = view.findViewById<TextView>(R.id.textWschod)
        val textZachod = view.findViewById<TextView>(R.id.textZachod)
        val textOpis = view.findViewById<TextView>(R.id.textOpis)
        val textGodzina = view.findViewById<TextView>(R.id.textGodzina)
        val textData = view.findViewById<TextView>(R.id.textData)

        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)



        viewModel.all.observe(viewLifecycleOwner) {
            textWschod.text = Instant.ofEpochSecond(it.sys.sunrise.toLong()).toString().substring(11,19)
            textZachod.text = Instant.ofEpochSecond(it.sys.sunset.toLong()).toString().substring(11,19)
            textOpis.text = it.weather[0].description
            textGodzina.text = Instant.ofEpochSecond(it.dt.toLong()).toString().substring(11,19)
            textCis.text = "Ciśnienie: " +  it.main.pressure.toString() + "Pa"
            textData.text = Instant.ofEpochSecond(it.dt.toLong()).toString().substring(0,10)
        }

        viewModel.postDay(lokal,"4a494e74d195716b49562533e9b08007","metric","pl")

        viewModel2 = ViewModelProvider(this).get(WeekViewModel::class.java)


        viewModel2.all.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.textTemp6).text =   concatenate(it.list[1].main.temp.toInt().toString(), "℃")
            view.findViewById<TextView>(R.id.textTemp12).text =  concatenate(it.list[3].main.temp.toInt().toString(), "℃")
            view.findViewById<TextView>(R.id.textTemp18).text =  concatenate(it.list[5].main.temp.toInt().toString(), "℃")
            view.findViewById<TextView>(R.id.textTemp21).text =  concatenate(it.list[6].main.temp.toInt().toString(), "℃")
            view.findViewById<TextView>(R.id.textTemp00).text =  concatenate(it.list[7].main.temp.toInt().toString(), "℃")
        }

        viewModel2.postWeek(lokal,"4a494e74d195716b49562533e9b08007","metric")


    }

}
