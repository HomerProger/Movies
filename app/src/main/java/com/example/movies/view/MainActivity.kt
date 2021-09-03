package com.example.movies.view


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.fragments.*

class MainActivity : AppCompatActivity() {

    private val netStatusReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            var messageId = 1000
            val conn = p0?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = conn.activeNetworkInfo

            if (networkInfo == null) {
                val builder = NotificationCompat.Builder(p0, "2")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Состояние сети")
                    .setContentText("Сеть отсутствует")
                val notificationManager =
                    p0.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(messageId++, builder.build())
            }

        }

    }
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
                        .addToBackStack("")
                        .replace(R.id.fragment_container, NowPlayingFragment.newInstance()).commit()
                    binding.now.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.fragment_container, NowPlayingFragment.newInstance())
                            .commit()
                    }
                }
                binding.upcoming.id -> {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.fragment_container, UpcomingFragment.newInstance()).commit()
                    binding.upcoming.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.fragment_container, UpcomingFragment.newInstance())
                            .commit()
                    }
                }
                binding.popular.id -> {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.fragment_container, PopularFragment.newInstance()).commit()
                    binding.popular.setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .addToBackStack("")
                            .replace(R.id.fragment_container, PopularFragment.newInstance())
                            .commit()
                    }
                }
            }
        }
        initNotificationChannel()
        registerReceiver(netStatusReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(netStatusReceiver)
        super.onDestroy()
    }
    private fun initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel =  NotificationChannel("2", "name", importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.settings->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment.newInstance()).commit()
                true
            }
            R.id.history->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HistoryFragment.newInstance()).commit()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}

