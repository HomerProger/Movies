package com.example.movies.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.fragments.NowPlayingFragment
import com.example.movies.view.fragments.PopularFragment
import com.example.movies.view.fragments.UpcomingFragment

class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        savedInstanceState.let {
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
