package com.example.boyner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.boyner.R
import com.example.boyner.databinding.ActivityMainBinding
import com.example.boyner.db.ArticleDatabase
import com.example.boyner.network.repository.Repository
import com.example.boyner.viewmodel.NewsViewModel
import com.example.boyner.viewmodel.ViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel
    lateinit var database: ArticleDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.newsNavHostFrag) as NavHostFragment
        navController= navHostFragment.findNavController()
        navController.navigate(R.id.sourceNewsFragment)

      /*  database = Room.databaseBuilder(
            applicationContext,
            ArticleDatabase::class.java,
            "database"
        ).build()

        room db kullanmak istedim cihaza haberleri  kayıt için lakin instance bir türlü oluşturmadı.
       instnce için çok farklı yollar denedim. gradle ayarları denedim , newapplication sınıfı olusuturp ta yapmaya çalıştım yada
       buradaki gibi activityden repoya iletmeye çalıstım ama maalsef instance olusmadı.
       6-7 saatimi aldı diyebilirim. emeğim boşa gitsin istemedim  o bakımdan burayı bu sekilde
       yorum satırında acıklamak istedim. :)
       */



        val articleRepository = Repository() // databese i buradan atacaktım.
        viewModel = ViewModelProvider(this, ViewModelFactory(articleRepository))[NewsViewModel::class.java]

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back)
        }
    }

    fun changeToolbarTitle(newTitle: String) {
        binding.tvTitle.text = newTitle
    }

    fun getActivityTitle() {
        binding.tvTitle.text = getString(R.string.page_title)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}