package com.kieronquinn.app.pixellaunchermods.ui.screens.tweaks

import androidx.lifecycle.viewModelScope
import com.kieronquinn.app.pixellaunchermods.components.navigation.ContainerNavigation
import com.kieronquinn.app.pixellaunchermods.repositories.HideClockRepository
import com.kieronquinn.app.pixellaunchermods.repositories.HideClockRepositoryImpl
import com.kieronquinn.app.pixellaunchermods.repositories.SettingsRepository
import com.kieronquinn.app.pixellaunchermods.ui.base.settings.BaseSettingsViewModel
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.launch

abstract class TweaksViewModel: BaseSettingsViewModel() {

    abstract val hideClock: SettingsRepository.PixelLauncherModsSetting<Boolean>
    abstract val toggleDarkMode: SettingsRepository.PixelLauncherModsSetting<Boolean>

    abstract fun onWidgetResizeClicked()
    abstract fun onWidgetReplacementClicked()
    abstract fun onRecentsClicked()
    abstract fun onHideAppsClicked()

}

class TweaksViewModelImpl(
    private val containerNavigation: ContainerNavigation,
    private val hideClockRepository: HideClockRepository,
    settingsRepository: SettingsRepository
): TweaksViewModel() {

    override val hideClock = settingsRepository.hideClock
    override val toggleDarkMode = settingsRepository.toggleDarkMode

    override fun onWidgetResizeClicked() {
        viewModelScope.launch {
            containerNavigation.navigate(TweaksFragmentDirections.actionTweaksFragmentToWidgetResizeActivity())
        }
    }

    override fun onWidgetReplacementClicked() {
        viewModelScope.launch {
            containerNavigation.navigate(TweaksFragmentDirections.actionTweaksFragmentToTweaksWidgetReplacementFragment())
        }
    }

    override fun onHideAppsClicked() {
        viewModelScope.launch {
            containerNavigation.navigate(TweaksFragmentDirections.actionTweaksFragmentToHideAppsFragment())
        }
    }

    override fun onRecentsClicked() {
        viewModelScope.launch {
            containerNavigation.navigate(TweaksFragmentDirections.actionTweaksFragmentToRecentsTweaksFragment())
        }
    }

    //Immediately show the clock when the option is disabled to prevent it getting stuck disabled
    private fun setupDisableHideClock() = viewModelScope.launch {
        hideClock.asFlow().collect {
            if(!it){
                hideClockRepository.setClockVisible(true)
            }
        }
    }

    private fun setupToggleDark() = viewModelScope.launch {
        toggleDarkMode.asFlow().collect {
            if (it){
                execRootCommand("settings put system need_dark_font 1")
                execRootCommand("settings put system need_dark_statusbar 1")
                execRootCommand("settings put system need_dark_navigationbar 1")
            } else {
                execRootCommand("settings put system need_dark_font 0")
                execRootCommand("settings put system need_dark_statusbar 0")
                execRootCommand("settings put system need_dark_navigationbar 0")
            }
        }
    }

    init {
        setupDisableHideClock()
        setupToggleDark()
    }

    private fun execRootCommand(command: String) {
        Runtime.getRuntime().exec(command)
    }
}