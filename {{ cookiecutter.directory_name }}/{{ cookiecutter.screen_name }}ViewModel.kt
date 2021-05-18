package ${package_name}

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class {{ cookiecutter.screen_name }}ViewModel @Inject constructor(
    private val {{ cookiecutter.screen_name[0] | lower }}{{ cookiecutter.screen_name[1:] }}Presenter: {{ cookiecutter.screen_name }}Presenter
): RainbowCakeViewModel<{{ cookiecutter.screen_name }}ViewState>({{ cookiecutter.screen_name }}Initial)
