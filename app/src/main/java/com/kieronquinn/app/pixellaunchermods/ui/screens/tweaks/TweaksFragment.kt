package com.kieronquinn.app.pixellaunchermods.ui.screens.tweaks

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kieronquinn.app.pixellaunchermods.R
import com.kieronquinn.app.pixellaunchermods.ui.base.CanShowSnackbar
import com.kieronquinn.app.pixellaunchermods.ui.base.settings.BaseSettingsAdapter
import com.kieronquinn.app.pixellaunchermods.ui.base.settings.BaseSettingsFragment
import com.kieronquinn.app.pixellaunchermods.ui.base.settings.BaseSettingsViewModel.SettingsItem
import com.kieronquinn.app.pixellaunchermods.utils.extensions.isPackageInstalled
import org.koin.androidx.viewmodel.ext.android.viewModel

class TweaksFragment: BaseSettingsFragment(), CanShowSnackbar {

    override val viewModel by viewModel<TweaksViewModel>()

    override val items by lazy {
        listOf(
            SettingsItem.Text(
                icon = R.drawable.ic_tweaks_recents,
                titleRes = R.string.tweaks_sec_home_up,
                contentRes = R.string.tweaks_sec_home_up_content,
                onClick = {
                    val homeStarPkg = "com.samsung.android.app.homestar"
                    val isInstalled = context?.isPackageInstalled(homeStarPkg)
                    if (isInstalled == true) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("ayrastar://run")))
                    } else {
                        context?.let {
                            MaterialAlertDialogBuilder(it).apply {
                                setTitle(R.string.no_homeup_dialog_title)
                                setMessage(R.string.no_homeup_dialog_content)
                                setPositiveButton(R.string.settings_update_fab) { _, _ ->
                                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AyraHikari/SamsungLauncherPort/blob/main/plugins.md")))
                                }
                                setCancelable(false)
                            }.show()
                        }
                    }
                }
            ),
            SettingsItem.Text(
                icon = R.drawable.ic_tweaks_deferred_restart,
                titleRes = R.string.tweaks_sec_icon_patcher,
                contentRes = R.string.tweaks_sec_icon_patcher_content,
                onClick = {
                    val homeStarPkg = "me.ayra.project.oneui.launcher"
                    val isInstalled = context?.isPackageInstalled(homeStarPkg)
                    if (isInstalled == true) {
                        val intent = Intent(Intent.ACTION_MAIN)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.`package` = "me.ayra.project.oneui.launcher"
                        intent.component = ComponentName("me.ayra.project.oneui.launcher", "me.ayra.sec.music.activity.AyraCustom")
                        startActivity(intent)
                    } else {
                        context?.let {
                            MaterialAlertDialogBuilder(it).apply {
                                setTitle(R.string.no_tweak_dialog_title)
                                setMessage(R.string.no_tweak_dialog_content)
                                setPositiveButton(R.string.settings_update_fab) { _, _ ->
                                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AyraHikari/SamsungLauncherPort/blob/main/plugins.md")))
                                }
                                setCancelable(false)
                            }.show()
                        }
                    }
                }
            ),
                /*
                SettingsItem.Text(
                    icon = R.drawable.ic_tweaks_widget_replacement,
                    titleRes = R.string.tweaks_sec_home_screen,
                    contentRes = R.string.tweaks_sec_home_screen_content,
                    onClick = {
                        val homeStarPkg = "com.samsung.android.app.homestar"
                        val isInstalled = context?.isPackageInstalled(homeStarPkg)
                        if (isInstalled == true) {
                            val intent = Intent("$homeStarPkg.home.HomeActivity")
                            startActivity(intent)
                        } else {
                            context?.let {
                                MaterialAlertDialogBuilder(it).apply {
                                    setTitle(R.string.no_homeup_dialog_title)
                                    setMessage(R.string.no_homeup_dialog_content)
                                    setPositiveButton(R.string.settings_update_fab) { _, _ ->
                                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AyraHikari/SamsungLauncherPort/blob/main/plugins.md")))
                                    }
                                    setCancelable(false)
                                }.show()
                            }
                        }
                    }
                ),
                SettingsItem.Text(
                    icon = R.drawable.ic_tweaks_recents,
                    titleRes = R.string.tweaks_sec_home_folder,
                    contentRes = R.string.tweaks_sec_home_folder_content,
                    onClick = {
                        val homeStarPkg = "com.samsung.android.app.homestar"
                        val isInstalled = context?.isPackageInstalled(homeStarPkg)
                        if (isInstalled == true) {
                            val intent = Intent("$homeStarPkg.folder.FolderActivity")
                            startActivity(intent)
                        } else {
                            context?.let {
                                MaterialAlertDialogBuilder(it).apply {
                                    setTitle(R.string.no_homeup_dialog_title)
                                    setMessage(R.string.no_homeup_dialog_content)
                                    setPositiveButton(R.string.settings_update_fab) { _, _ ->
                                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AyraHikari/SamsungLauncherPort/blob/main/plugins.md")))
                                    }
                                    setCancelable(false)
                                }.show()
                            }
                        }
                    }
                ),
                SettingsItem.Text(
                    icon = R.drawable.ic_tweaks_hide_clock,
                    titleRes = R.string.tweaks_sec_home_task,
                    contentRes = R.string.tweaks_sec_home_task_content,
                    onClick = {
                        val homeStarPkg = "com.samsung.android.app.homestar"
                        val isInstalled = context?.isPackageInstalled(homeStarPkg)
                        if (isInstalled == true) {
                            val intent = Intent("com.samsung.android.app.taskchanger.setting.TaskChangerActivity")
                            startActivity(intent)
                        } else {
                            context?.let {
                                MaterialAlertDialogBuilder(it).apply {
                                    setTitle(R.string.no_homeup_dialog_title)
                                    setMessage(R.string.no_homeup_dialog_content)
                                    setPositiveButton(R.string.settings_update_fab) { _, _ ->
                                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AyraHikari/SamsungLauncherPort/blob/main/plugins.md")))
                                    }
                                    setCancelable(false)
                                }.show()
                            }
                        }
                    }
                )*/
        )
    }

    override fun createAdapter(items: List<SettingsItem>): BaseSettingsAdapter {
        return TweaksAdapter()
    }

    private inner class TweaksAdapter: BaseSettingsAdapter(requireContext(), binding.settingsBaseRecyclerView, items)

}