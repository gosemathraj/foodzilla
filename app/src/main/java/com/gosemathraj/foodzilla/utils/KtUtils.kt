package com.gosemathraj.foodzilla.utils

import timber.log.Timber

/*
*   Safe Try-Catch block
*/
fun safeExecute(body:()->Unit){
    try{
        body()
    }catch (ex : Exception){
        ex.printStackTrace()
        Timber.d("Exception $ex")
    }
}