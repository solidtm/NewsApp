package com.solid.newsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NewsApiServiceTest {
    private lateinit var newsApiService: NewsApiService
    private lateinit var server : MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        newsApiService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    //this function will read our json file used for mock response and will enqueue the requests in the mock web server queue
    private fun enqueueMockResponse(fileName: String){
        //create the stream to
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        //get datasource from stream and set it into the memory buffer
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()    //create instance of MockResponse
        //set the body of the MockResponse passing the string format of the source
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse) //enqueue the mock response to the server
    }

    //function to test our getNewsHeadLines function in our API service
    //naming format is: <function under test name>_<action>_<result state>
    @Test
    fun getTopHeadLines_sendRequest_receivedExpected(){
        runBlocking {    //the coroutine builder we use for testing...blocks the current thread until we get response.
            enqueueMockResponse("newsresponse.json")  //enqueue response to mockserver
            val responseBody = newsApiService.getTopHeadLines("us", 1).body()  //get body of the request
            val request = server.takeRequest()  // get the request received by the mock server.

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=ae2315823a274f14b9097f6e94b80303")
        }
    }

    //test function to check the available news article quantity of the response body.
    @Test
    fun getTopHeadLines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsApiService.getTopHeadLines("us", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    //test function to test the content of the received object/json
    @Test
    fun getTopHeadLines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsApiService.getTopHeadLines("us", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]   //get first article in the list and check it's values/contents

            assertThat(article.author).isEqualTo("Joon Lee")
            assertThat(article.url).isEqualTo("https://www.espn.com/mlb/story/_/id/34757725/new-york-mets-stay-alive-jacob-degrom-force-game-3")
            assertThat(article.publishedAt).isEqualTo("2022-10-09T06:30:30Z")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}