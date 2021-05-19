package ${package_name}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
{%- if cookiecutter.di == "Hilt" %}
import androidx.fragment.app.viewModels
{%- endif %}
import co.zsmb.rainbowcake.base.RainbowCakeFragment
{%- if cookiecutter.di == "Dagger 2" %}
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
{%- endif %}
import ${app_package}.databinding.Fragment{{ cookiecutter.screen_name }}Binding
{%- if cookiecutter.di == "Hilt" %}
import dagger.hilt.android.AndroidEntryPoint
{%- endif %}
{% if cookiecutter.di == "Hilt" %}
@AndroidEntryPoint
{%- endif %}
class {{ cookiecutter.screen_name }}Fragment : RainbowCakeFragment<{{ cookiecutter.screen_name }}ViewState, {{ cookiecutter.screen_name }}ViewModel>() {

    private lateinit var binding: Fragment{{ cookiecutter.screen_name }}Binding
    {% if cookiecutter.di == "Hilt" %}
    override fun provideViewModel() = viewModels<{{ cookiecutter.screen_name }}ViewModel>().value
    {%- elif cookiecutter.di == "Dagger 2" %}
    override fun provideViewModel() = getViewModelFromFactory()
    {%- endif %}

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
