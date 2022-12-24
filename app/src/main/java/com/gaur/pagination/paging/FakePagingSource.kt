package com.gaur.pagination.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gaur.pagination.model.Data
import com.gaur.pagination.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class FakePagingSource @Inject constructor(private val apiService: ApiService)  : PagingSource<Int,Data>(){

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
     return   try{
            val prev = params.key?:0
            val response = apiService.getFakeResponse(page=prev,size=params.loadSize)
            if(response.isSuccessful){
                val body = response.body()?.data
                LoadResult.Page(
                    data= body!!,
                    prevKey = if(prev==0) null else prev-1,
                    nextKey = if(body.size<params.loadSize) null else prev+1
                )

            }else{
                LoadResult.Error(Exception())
            }
        }catch (e:Exception){
         Log.d("TAG", "load: ${e.printStackTrace()}")
            LoadResult.Error(e)
        }
    }
}