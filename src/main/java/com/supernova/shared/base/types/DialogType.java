package com.supernova.shared.base.types;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.supernova.shared.base.types.DialogType.TYPE_ERROR;
import static com.supernova.shared.base.types.DialogType.TYPE_INFO;
import static com.supernova.shared.base.types.DialogType.TYPE_SUCCESS;

@Retention(RetentionPolicy.SOURCE)
@StringDef({TYPE_INFO, TYPE_SUCCESS, TYPE_ERROR})
public @interface DialogType {

    // Declare the constants
    String TYPE_INFO = "info";
    String TYPE_SUCCESS = "success";
    String TYPE_ERROR = "error";
}
