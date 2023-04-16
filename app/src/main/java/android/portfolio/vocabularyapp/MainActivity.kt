  package android.portfolio.vocabularyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.portfolio.vocabularyapp.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

  class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener  {
    private lateinit var binding : ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addFloatingButton.setOnClickListener {
            //registerForActivityResult()
            Intent(this, AddActivity::class.java).let{
                startActivity(it)
            }
        }
    }

    override fun onClick(word: Word) {
      Toast.makeText(this,"${word.text}가 클릭 됐습니다.",Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView(){
//        val dummyList = mutableListOf<Word>(
//            Word("weather","날씨","noun"),
//            Word("happy","기쁜","adjective"),
//            Word("walk","걷다","verb"),
//        )

        wordAdapter = WordAdapter(mutableListOf(),this)
        binding.wordRecyclerviews.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager. VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        Thread{
            val list = AppDatabase.getInstance(this)?.wordDao()?.getAll()?: emptyList()
            wordAdapter.list.addAll(list)
            runOnUiThread{
                wordAdapter.notifyDataSetChanged()
            }
            //setResult()
            finish()

        }.start()

    }
}