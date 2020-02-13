package io.android.projectx.domain.base.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}