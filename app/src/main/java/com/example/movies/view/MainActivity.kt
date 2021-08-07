package com.example.movies.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NowPlayingFragment.newInstance()).commit()
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.now.id -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NowPlayingFragment.newInstance()).commit()
                    binding.now.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, NowPlayingFragment.newInstance())
                            .commit()
                    }
                }
                binding.upcoming.id -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, UpcomingFragment.newInstance()).commit()
                    binding.upcoming.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, UpcomingFragment.newInstance())
                            .commit()
                    }
                }
                binding.popular.id -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PopularFragment.newInstance()).commit()
                    binding.popular.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, PopularFragment.newInstance())
                            .commit()
                    }
                }
            }
        }
    }
}
