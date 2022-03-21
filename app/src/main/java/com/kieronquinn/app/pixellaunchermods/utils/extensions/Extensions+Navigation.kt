package com.kieronquinn.app.pixellaunchermods.utils.extensions

import androidx.navigation.NavController
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

fun NavController.onDestinationChanged() = callbackFlow {
    val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        trySend(destination)
    }
    addOnDestinationChangedListener(listener)
    currentDestination?.let {
        trySend(it)
    }
    awaitClose {
        removeOnDestinationChangedListener(listener)
    }
}.debounce(TAP_DEBOUNCE)