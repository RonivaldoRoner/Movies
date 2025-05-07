package com.ronivaldoroner.movies.domain.utils

import com.ronivaldoroner.movies.domain.features.commons.SessionId

val SessionId.id get() = userId + featureId