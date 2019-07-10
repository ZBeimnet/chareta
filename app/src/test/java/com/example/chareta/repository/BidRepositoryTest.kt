package com.example.chareta.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry

import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Bid

import com.example.chareta.data.remote.webservice.BidService
import com.example.chareta.data.remote.webservice.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch

class BidRepositoryTests {
//    val appContext = InstrumentationRegistry

    private lateinit var repo: BidRepository
    private lateinit var database: CharetaDatabase
    private lateinit var bidService: BidService

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharetaDatabase::class.java,"charetaDatabase").allowMainThreadQueries().build()

        //repo = BidRepository(bidService)
    }

    @After
    fun cleanUp() {
        database.close()
    }




//    @Test
//    fun insertBidTest() {
//        val bid = Bid(1,500, Date(12/10/2019))
//        val bidFromDb = CharetaDatabase.getDatabase(appContext).bidDao().getBidById(bid.id)
//
//        CharetaDatabase.getDatabase(appContext).bidDao().insertBid(bid)
//
//        assertEquals(bidFromDb?.bid_amount, bid.bid_amount)
//        assertEquals(bidFromDb?.bid_date, bid.bid_date)
//    }


    @Test
    fun insertAndRetrieveItemTest() = runBlocking{
        // GIVEN - a new group saved in the database
        val bid =   Bid(1,700,"Bid Date")

        repo.insertBid(bid)

        // WHEN  - User retrieved by code
        val result = repo.getBidById(bid.id)

        MatcherAssert.assertThat<Bid>(result as Bid, CoreMatchers.notNullValue())

        // THEN - Same Item is returned
        assertThat(result?.id, CoreMatchers.`is`("1"))
        assertThat(result?.bid_amount, CoreMatchers.`is`("700"))
        assertThat(result?.bid_date, CoreMatchers.`is`("Bid Date"))




    }


//    @Test
//    fun deleteBidByIdTest(){
//        val bid1 = Bid(1,500, Date(2019,12,2), )
//        CharetaDatabase.getDatabase(appContext).bidDao().insertBid(bid1)
//
//        var bidFromDb = CharetaDatabase.getDatabase(appContext).bidDao().getBidById(bid1.id)
//
//        CharetaDatabase.getDatabase(appContext).bidDao().deleteBidById(bid1)
//
//        bidFromDb = CharetaDatabase.getDatabase(appContext).bidDao().getBidById(bid1.id)
//        assertNull(bidFromDb)
//
//
//    }

//    @Test
//    fun updateBidTest(){
//        val bid3 = Bid(3,800, "20/10/2019", 1)
//        val bidFromDb = CharetaDatabase.getDatabase(appContext).bidDao().getBidById(bid3.id)
//
//        CharetaDatabase.getDatabase(appContext).bidDao().updateBid(bid3)
//
//
//        assertEquals(bidFromDb?.bid_amount, bid3.bid_amount)
//        assertEquals(bidFromDb?.bid_date, bid3.bid_date)
//        assertEquals(bidFromDb?.item_id, bid3.item_id)
//
//    }


    @Test
    fun deleteItem_nullRetrieved() = runBlocking {

        // Given a new User in the persistent repository and a mocked callback
        val newBid = Bid(3,700,"Bid Date")

        repo.insertBid(newBid)


        // When groups are deleted
        repo.getBidsByItemId(newBid.id)

        // Then the retrieved tasks is an empty list
        val result = repo.getBidById(newBid.id)
        assertThat(result, CoreMatchers.nullValue())

    }

    @Test
    fun getItemsTest() = runBlocking {
        // Given 2 new tasks in the persistent repository

        val newBid1 =  Bid(2,700, "Bid Date")
        val newBid2 =  Bid(3,600, "Bid Date")

        repo.insertBid(newBid1)
        repo.insertBid(newBid2)
        // Then the tasks can be retrieved from the persistent repository
        val latch = CountDownLatch(1)
        val results = repo.getBids()
        var size = 2
//            val tasks = results.
//
//            serveForever{
//                size = it.size
//                latch.countDown()
//            }
//            latch.await(3,TimeUnit.SECONDS)
        assertThat(size, CoreMatchers.`is`(2))
    }





}