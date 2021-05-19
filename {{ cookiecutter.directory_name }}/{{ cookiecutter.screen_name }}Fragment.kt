package ${package_name}
{% if cookiecutter.view_reference == "ViewBinding" %}
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
{%- endif %}
{%- if cookiecutter.di == "Hilt" %}
import androidx.fragment.app.viewModels
{%- endif %}
import co.zsmb.rainbowcake.base.RainbowCakeFragment
{%- if cookiecutter.di == "Dagger 2" %}
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
{%- endif %}
{%- if cookiecutter.view_reference == "ViewBinding" %}
import ${app_package}.databinding.Fragment{{ cookiecutter.screen_name }}Binding
{%- endif %}
{%- if cookiecutter.di == "Hilt" %}
import dagger.hilt.android.AndroidEntryPoint
{%- endif %}
{%- if cookiecutter.view_reference == "Synthetics" %}
import ${app_package}.R
{%- endif %}
{% if cookiecutter.di == "Hilt" %}
@AndroidEntryPoint
{%- endif %}
class {{ cookiecutter.screen_name }}Fragment : RainbowCakeFragment<{{ cookiecutter.screen_name }}ViewState, {{ cookiecutter.screen_name }}ViewModel>() {
    {%- if cookiecutter.view_reference == "ViewBinding" +%}

    private lateinit var binding: Fragment{{ cookiecutter.screen_name }}Binding
    {%- endif -%}

    {%- if cookiecutter.di == "Hilt" +%}

    override fun provideViewModel() = viewModels<{{ cookiecutter.screen_name }}ViewModel>().value
    {%- elif cookiecutter.di == "Dagger 2" +%}

    override fun provideViewModel() = getViewModelFromFactory()
    {%- endif %}
    {%- if cookiecutter.view_reference == "Synthetics" +%}

    override fun getViewResource() = R.layout.${layout_name}
    {%- endif %}
    {%- if cookiecutter.view_reference == "ViewBinding" +%}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment{{ cookiecutter.screen_name }}Binding.inflate(inflater, container, false)
        return binding.root
    }
    {%- endif %}

    override fun render(viewState: {{ cookiecutter.screen_name }}ViewState) {
        when (viewState) {
            {{ cookiecutter.screen_name }}Initial -> TODO()
        }
    }
}
