package fastcampus.part2.chapter1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

class WebViewFragment: Fragment() {
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

        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar)
        // 웹 뷰 안에서 자바 스크립트 사용 가능 -> 보안에 문제가 생길 수 있다?
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://comic.naver.com/")
    }
}