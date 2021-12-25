package com.example.week8calculator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week8calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnZero.setOnClickListener { addValue("0",true) }
        binding.btnOne.setOnClickListener { addValue("1",true) }
        binding.btnTwo.setOnClickListener { addValue("2",true) }
        binding.btnTri.setOnClickListener { addValue("3",true) }
        binding.btnFour.setOnClickListener { addValue("4",true) }
        binding.btnFive.setOnClickListener { addValue("5",true) }
        binding.btnSix.setOnClickListener { addValue("6",true) }
        binding.btnSeven.setOnClickListener { addValue("7",true) }
        binding.btnEight.setOnClickListener { addValue("8",true) }
        binding.btnNine.setOnClickListener { addValue("9",true) }
        binding.btnDot.setOnClickListener { addValue(".",true) }
        binding.btnOpenPar.setOnClickListener { addValue("(",true) }
        binding.btnClosePar.setOnClickListener { addValue(")",true) }

        binding.btnPlus.setOnClickListener { addValue("+",false) }
        binding.btnMin.setOnClickListener { addValue("-",false) }
        binding.btnDiv.setOnClickListener { addValue("/",false) }
        binding.btnMul.setOnClickListener { addValue("*",false) }

        btnClear()
        btnDelete()
        btnEquals()
    }
    private fun addValue(value: String, clear : Boolean){
        if (clear){
            binding.tvResult.text = ""
            binding.tvCalculate.append(value)

        }else{
            if (binding.tvResult.text.toString().contains("ERROR")){
                binding.tvResult.text = ""
            }else{
                binding.tvCalculate.append(binding.tvResult.text.toString())
                binding.tvCalculate.append(value)
                binding.tvResult.text = ""
            }
        }

    }
    private fun btnEquals(){
        binding.btnEqual.setOnClickListener {
            if (binding.tvCalculate.text.toString().isEmpty()){
                binding.tvResult.text = "0"
            }else {
                try {
                    val expression = ExpressionBuilder(binding.tvCalculate.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()){
                        binding.tvResult.text = longResult.toString()
                    } else {
                        binding.tvResult.text = result.toString()
                    }

                } catch (e: Exception){
                    binding.tvResult.text = "ERROR"
                }

            }
        }
        binding.tvCalculate.text = ""

    }
    private  fun btnDelete(){
        binding.btnDel.setOnClickListener {
            val string = binding.tvCalculate.text.toString()
            if (string.isNotEmpty()){
                binding.tvCalculate.text = string.substring(0, string.length -1)
            }
        }

    }
    private fun btnClear(){
        binding.btnClear.setOnClickListener {
            binding.tvCalculate.text = ""
            binding.tvResult.text = ""
        }

    }
}