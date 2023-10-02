package woowacourse.paint.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.main.adapter.ColorAdapter

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()

        setColorsRecyclerview()
        setBrushSizeListener()
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setColorsRecyclerview() {
        binding.rvColors.apply {
            adapter = ColorAdapter {
                viewModel.setBrushColor(it)
            }
            setHasFixedSize(true)
        }
    }

    private fun setBrushSizeListener() {
        binding.rangeSliderBrushSize.addOnChangeListener { _, value, _ ->
            viewModel.setBrushSize(value)
        }
    }
}
