package com.example.pogodynka.fregments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pogodynka.R
import com.example.pogodynka.viewModel.DayViewModel
import com.example.pogodynka.viewModel.WeekViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Instant

var lokal:String = "Gliwice"

class DzienFragment : Fragment() {

    private lateinit var viewModel: DayViewModel
    private lateinit var viewModel2: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dzien, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textMiasto).text = lokal
        (view.findViewById<FloatingActionButton>(R.id.backFromDay)).setOnClickListener{

            findNavController().navigate(R.id.action_dzienFragment_to_mainFragment)

        }

        val seekbar = view.findViewById<SeekBar>(R.id.seekBar)
        val textCzas = view.findViewById<TextView>(R.id.textCzas)
        val textTemp = view.findViewById<TextView>(R.id.textTemperatura)
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
                textData.text = Instant.ofEpochSecond(it.dt.toLong()).toString().substring(0,10)
        }

        viewModel.postDay(lokal,"4a494e74d195716b49562533e9b08007","metric","pl")

        viewModel2 = ViewModelProvider(this).get(WeekViewModel::class.java)

        var temporarytemperature = arrayOf(0,0,0,0,0,0,0,0)
        var temporarypressure = arrayOf(0,0,0,0,0,0,0,0)
        viewModel2.all.observe(viewLifecycleOwner) {
            textTemp.text = concatenate(it.list[0].main.temp.toInt().toString(), "℃")
            textCis.text = concatenate(it.list[0].main.pressure.toString(), "Pa")
            temporarytemperature[0] = it.list[0].main.temp.toInt()
            temporarytemperature[1] = it.list[1].main.temp.toInt()
            temporarytemperature[2] = it.list[2].main.temp.toInt()
            temporarytemperature[3] = it.list[3].main.temp.toInt()
            temporarytemperature[4] = it.list[4].main.temp.toInt()
            temporarytemperature[5] = it.list[5].main.temp.toInt()
            temporarytemperature[6] = it.list[6].main.temp.toInt()
            temporarytemperature[7] = it.list[7].main.temp.toInt()
            temporarypressure[0] = it.list[0].main.pressure
            temporarypressure[1] = it.list[1].main.pressure
            temporarypressure[2] = it.list[2].main.pressure
            temporarypressure[3] = it.list[3].main.pressure
            temporarypressure[4] = it.list[4].main.pressure
            temporarypressure[5] = it.list[5].main.pressure
            temporarypressure[6] = it.list[6].main.pressure
            temporarypressure[7] = it.list[7].main.pressure
        }

        viewModel2.postWeek(lokal,"4a494e74d195716b49562533e9b08007","metric")


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, currentValue: Int, p2: Boolean) {
                if(currentValue%3 == 0) {
                    textCzas.text = concatenate(currentValue.toString(),":00")
                    textTemp.text = concatenate(temporarytemperature[currentValue/3 - 1].toString(), "℃")
                    textCis.text = concatenate(temporarypressure[currentValue/3 - 1].toString(), "Pa")
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        }

}

fun concatenate(vararg string: String?): String {
    var sb = StringBuilder()
    string.forEach { sb.append(it) }
    return sb.toString()
}