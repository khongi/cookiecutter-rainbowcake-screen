package ${package_name}

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
{%- if cookiecutter.di == "Hilt" +%}
import dagger.hilt.android.lifecycle.HiltViewModel
{%- endif %}
import javax.inject.Inject

{%+ if cookiecutter.di == "Hilt" -%}
@HiltViewModel
{%+ endif -%}
class {{ cookiecutter.screen_name }}ViewModel @Inject constructor(
    private val {{ cookiecutter.screen_name[0] | lower }}{{ cookiecutter.screen_name[1:] }}Presenter: {{ cookiecutter.screen_name }}Presenter
): RainbowCakeViewModel<{{ cookiecutter.screen_name }}ViewState>({{ cookiecutter.screen_name }}Initial)
