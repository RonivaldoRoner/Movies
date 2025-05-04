package com.ronivaldoroner.movies.presentation.utils

import androidx.compose.runtime.SnapshotMutationPolicy
import com.ronivaldoroner.movies.domain.base.state.State


@Suppress("UNCHECKED_CAST")
fun <T : State> resourceStatusPolicy(): SnapshotMutationPolicy<T> =
    ResourceStatusPolicy as SnapshotMutationPolicy<T>

private object ResourceStatusPolicy : SnapshotMutationPolicy<State> {
    override fun equivalent(a: State, b: State) = a.state == b.state

    override fun toString() = "StructuralEqualityPolicy"
}