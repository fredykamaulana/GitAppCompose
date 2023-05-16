package com.example.gitappcompose.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun rememberLifecycleEvent(lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }

    DisposableEffect(key1 = lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }

        lifeCycleOwner.lifecycle.addObserver(observer = observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer = observer)
        }
    }

    return state
}

@Composable
fun <LO : LifecycleObserver> LO.ObserveLifecycle(lifecycle: Lifecycle) {
    DisposableEffect(key1 = lifecycle) {
        lifecycle.addObserver(this@ObserveLifecycle)

        onDispose {
            lifecycle.removeObserver(this@ObserveLifecycle)
        }
    }
}