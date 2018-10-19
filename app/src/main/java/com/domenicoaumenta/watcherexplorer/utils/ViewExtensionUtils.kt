package com.domenicoaumenta.watcherexplorer.utils

import android.view.View


/**
 * Created by domenicoaumenta on 19/10/2018.
 */
var View.isVisible : Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }