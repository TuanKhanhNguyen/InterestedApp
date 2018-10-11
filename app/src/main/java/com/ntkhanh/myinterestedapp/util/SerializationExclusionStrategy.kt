package com.ntkhanh.myinterestedapp.util

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class SerializationExclusionStrategy : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes) = f.getAnnotation(SerializationExclude::class.java) != null

    override fun shouldSkipClass(clazz: Class<*>) = false
}
