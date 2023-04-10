package android.portfolio.vocabularyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.portfolio.vocabularyapp.databinding.ActivityAddBinding
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater )
        setContentView(binding.root)

        initViews()
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
}