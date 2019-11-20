package sk.lubostar.crz.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import sk.lubostar.crz.R
import sk.lubostar.crz.databinding.NavigationDrawerLayoutBinding

abstract class DrawerActivity: AppCompatActivity() {
    private lateinit var binding: NavigationDrawerLayoutBinding
    private lateinit var viewModel: NavigationDrawerViewModel

    open lateinit var drawerLayout: DrawerLayout
    open lateinit var navView: NavigationView

    abstract fun onCreateView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateView()
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.navigation_drawer_layout, navView, true)
        viewModel = ViewModelProviders.of(this).get(NavigationDrawerViewModel::class.java)

        initBinding(binding, viewModel)
    }

    private fun initBinding(binding: NavigationDrawerLayoutBinding, viewModel: NavigationDrawerViewModel){
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}