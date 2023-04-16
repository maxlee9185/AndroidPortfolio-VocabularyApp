package android.portfolio.vocabularyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.portfolio.vocabularyapp.databinding.ActivityAddBinding
import android.widget.Toast
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater )
        setContentView(binding.root)

        initViews()
        binding.addButton.setOnClickListener {
            add()
        }
    }
    private fun initViews(){
        val types = listOf(
            "noun","verb","adverb","adjective","preposition","interjection","pronoun","conjunction",
        )
        binding.typeChipGroup.apply{
            types.forEach{ text ->
                addView(createChip(text ))

            }
        }
    }

    private fun createChip(text:String) : Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add(){
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val word = Word(text,mean,type)
        Thread{
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "잘 추가됐습니다", Toast.LENGTH_SHORT).show()
            }
            finish()
        }.start()

    }
}