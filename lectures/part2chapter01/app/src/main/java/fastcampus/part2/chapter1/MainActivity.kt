package fastcampus.part2.chapter1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.core.view.get
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)

        val tab0 = sharedPreferences?.getString("tab0_name", "tab1")
        val tab1 = sharedPreferences?.getString("tab1_name", "tab2")
        val tab2 = sharedPreferences?.getString("tab2_name", "tab3")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]       // TODO 수정 필요함. fragment를 viewpager에 가져와야함
        if(currentFragment is WebViewFragment) {
            if(currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }

}