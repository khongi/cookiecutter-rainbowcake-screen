package ${package_name}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import ${app_package}.databinding.Fragment{{ cookiecutter.screen_name }}Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class {{ cookiecutter.screen_name }}Fragment : RainbowCakeFragment<{{ cookiecutter.screen_name }}ViewState, {{ cookiecutter.screen_name }}ViewModel>() {

    private lateinit var binding: Fragment{{ cookiecutter.screen_name }}Binding

    override fun provideViewModel() = viewModels<{{ cookiecutter.screen_name }}ViewModel>().value

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment{{ cookiecutter.screen_name }}Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun render(viewState: {{ cookiecutter.screen_name }}ViewState) {
        when (viewState) {
            {{ cookiecutter.screen_name }}Initial -> TODO()
        }
    }
}
