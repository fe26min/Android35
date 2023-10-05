package fastcampus.part2.chapter1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(private val mainActivity: MainActivity): FragmentStateAdapter(mainActivity) {

    // 아이템이 몇 개인지를 반환
    override fun getItemCount(): Int {
        return 3
    }

    // 프래그먼트를 n번째에 생성
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                return WebViewFragment(position, "https://comic.naver.com").apply {
                    listener = mainActivity
                }
            }
            1 -> {
                return WebViewFragment(position, "https://comic.naver.com").apply {
                    listener = mainActivity
                }
            }
            else -> {
                return WebViewFragment(position, "https://comic.naver.com").apply {
                    listener = mainActivity
                }
            }
        }
    }


}