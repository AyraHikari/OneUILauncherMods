## Can OneUI Launcher Mods work without root?

Unfortunately not. The core features of OneUI Launcher Mods, including changing app icons and labels,
requires root access to access and modify the database files for the OneUI Launcher.

## Why do the Hide Apps and Widget Replacement options require a Magisk module?

These options use a system feature called Runtime Resource Overlays (RROs), to 'overlay' (replace)
resources in the OneUI Launcher. Overlays must be installed as system apps, so a Magisk module is
required. Once installed, OneUI Launcher Mods can build and install dynamically generated overlays
on top of this system app, without needing to reboot or reinstall a module.

## I've uninstalled OneUI Launcher Mods but my custom icons & labels, hidden apps or widget changes persist?

This happens as OneUI Launcher Mods applies changes directly to the OneUI Launcher databases, so 
even if you uninstall OneUI Launcher Mods, the icons will still remain until the icon cache is 
reloaded.

The easiest way to fix this is to reinstall OneUI Launcher Mods, and use the reset option in
More > Advanced > Reset. After doing this, disable widget replacement, deselect all your hidden apps
and apply changes.

You may then uninstall OneUI Launcher Mods again.

If reinstalling OneUI Launcher Mods is not an option, you must reset the changes manually. 

Using a terminal, such as Termux, run these commands:

```bash
su
cd /data/data/com.sec.android.app.launcher/databases
rm app_icons*
settings delete global SEARCH_PROVIDER_PACKAGE_NAME
```

On a device that has become unrooted (for example after an update), you **must** clear the data of 
the OneUI Launcher. This will reset your home screen layout.

## After using OneUI Launcher Mods, I see a different search widget on a launcher that isn't the OneUI Launcher!

This is caused by the method used to replace the Search Box or At a Glance widgets. To reset it, 
you must run this command using either a root terminal, or using ADB from a computer, and reboot:

```bash
settings delete global SEARCH_PROVIDER_PACKAGE_NAME
```

## Why can I not use custom themed icons on Android 12?

This is a restriction of Android 12, which does not actually support themed icons, but uses a 
hardcoded list of icons in the OneUI Launcher. There are some mods available to add more icons to
this list, please see the XDA thread for more details.

## Why can I not edit the labels of app shortcuts?

This is a restriction in the OneUI Launcher, and cannot be bypassed. App shortcut labels are reloaded
with the launcher, and therefore changes do not persist.

## Why can I not edit dynamic icons?

Dynamic icons are shown dynamically, loaded directly from the apps that use them. This makes it
impossible to change the icons.

## Why do not all widgets work in the At a Glance / Search Box replacement option?

This is a restriction of the method used to replace At a Glance / Search Box, it does support some 
features modern widgets use. As a result, some widgets will fail to load or show as blank spaces.

## Can I remove the search box or At a Glance widget entirely?

Unfortunately not. The space is always shown, regardless of the height of a widget, or the size
of the underlying layout. Removing this is not possible using overlays.

## The widget I am using to replace At a Glance or Search Box is frozen

This occasionally happens when the connection between the replacement widget and the chosen app 
widget is broken. Rebooting will fix it.



