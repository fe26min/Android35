package fastcampus.part2.chapter1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int): Fragment() {
    private lateinit var binding: FragmentWebviewBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 뷰 바인딩을 불러온다.
        // 인플레이터를 통해서 인플레이트를 해준다.
        // 인플레이트란?
        //https://dev-cini.tistory.com/29
        // 안드로이드에서 Inflate는 xml에 표기된 레이아웃들을 메모리에 로딩된 후 객체화 시키는 과정이다.
        // 쉽게 말해, layout에 그때 그때 다른 layout을 집어 넣을 수 있다는 얘기다.
        //
        binding = FragmentWebviewBinding.inflate(inflater)

        return binding.root
    }

    // 뷰가 호출된 이후에 뷰가 만들어진 이후에 호출되는 메서드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar) {url ->
            activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }
        // 웹 뷰 안에서 자바 스크립트 사용 가능 -> 보안에 문제가 생길 수 있다?
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://comic.naver.com/")

        binding.backToLastButton.setOnClickListener {

            val sharedPreferences = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position", "")

            if(url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
            else
                binding.webView.loadUrl(url)

        }
    }

    fun canGoBack() : Boolean{
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}