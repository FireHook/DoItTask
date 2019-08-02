package com.firehook.doittask.network.retrofit

import com.firehook.doittask.network.model.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Vladyslav Bondar on 19.07.2019
 * Skype: diginital
 */

interface DoItService {

    @POST("api/auth") @Headers("Accept: application/json", "Content-Type: application/json")
    fun auth(@Query("email") email: String, @Query("password") password: String): Observable<Token>

    @POST("api/users")
    fun register(@Query("email") email: String, @Query("password") password: String): Observable<Token>

    @GET("api/tasks?sort=title%20asc") @Headers("Accept: application/json", "Content-Type: application/json")
    fun tasks(@Header("Authorization") token: String) : Observable<ResponseBody>

    @GET("api/tasks") @Headers("Accept: application/json", "Content-Type: application/json")
    fun getSortedTasks(@Header("Authorization") token: String, @Query("sort") sortFilter: String): Observable<ResponseBody>

    @DELETE("api/tasks/{task}") @Headers("Accept: application/json", "Content-Type: application/json")
    fun deleteTask(@Header("Authorization") token: String, @Path("task") taskId: Int): Observable<Array<String>>

    @PUT("api/tasks/{task}") @Headers("Accept: application/json", "Content-Type: application/json")
    fun updateTask(@Header("Authorization") token: String, @Path("task") taskId: Int?, @Body task: RequestTask): Observable<Array<ResponsedTask>>

    @POST("api/tasks") @Headers("Accept: application/json", "Content-Type: application/json")
    fun createTask(@Header("Authorization") token: String, @Body task: RequestTask): Observable<ResponsedTask>

    @GET("api/tasks/{task}") @Headers("Accept: application/json", "Content-Type: application/json")
    fun getTask(@Header("Authorization") token: String, @Path("task") taskId: Int): Observable<ResponsedTask>

}