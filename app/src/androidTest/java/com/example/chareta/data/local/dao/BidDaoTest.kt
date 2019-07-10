package com.example.chareta.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Bid
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class BidDaoTest {

//    lateinit var bidDao: BidDao
//    lateinit var database: charetaDatabase
//
//    @Before
//    fun setup() {
//        val context = InstrumentationRegistry.getTargetContext()
//        database = Room.inMemoryDatabaseBuilder(context, charetaDatabase::class.java).build()
//
//        bidDao = database.BidDao()
//    }
//
//    @After
//    fun tearDown() {
//        database.close()
//    }
//    @Test
//    fun testInsertedAndRetrievedBidsMatch() {
//        val bid = Bid(1, 500, "12/11/2019" ,2)
//        bidDao.insertBid(bid)
//
//        val bids = bidDao.getBidById(1)
//        assertEquals(bid, bids)
//    }
//
//    @Test
//    fun getBidByIdTest(){
//
//    }


    private lateinit var database: CharetaDatabase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    //var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    //@get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharetaDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertBid(){
        // GIVEN - insert a task
        val bid = Bid(1, 500, "12/11/2019")
        database.bidDao().insertBid(bid)

        // WHEN - Get the task by id from the database
        val loaded = database.bidDao().getBidById(bid.id)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Bid>(loaded as Bid, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(bid.id))
        MatcherAssert.assertThat(loaded.bid_amount, CoreMatchers.`is`(bid.bid_amount))
        MatcherAssert.assertThat(loaded.bid_date, CoreMatchers.`is`(bid.bid_date))

    }

    @Test
    fun insertBidReplacesOnConflict() {
        // Given that a task is inserted
        val bid = Bid(2, 800, "12/11/2019")
        database.bidDao().insertBid(bid)

        // When a task with the same id is inserted
        val newBid = Bid(3, 570, "18/10/2019")
        database.bidDao().insertBid(newBid)

        // THEN - The loaded data contains the expected values
        val loaded = database.bidDao().getBidById(bid.id)
        MatcherAssert.assertThat<Bid>(loaded as Bid, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(bid.id))
        MatcherAssert.assertThat(loaded?.bid_amount, CoreMatchers.`is`(570))
        MatcherAssert.assertThat(loaded?.bid_date, CoreMatchers.`is`("18/10/2019"))

    }



    @Test
    fun updateBidAndGetById(){
        // When inserting a task
        val originalBid =  Bid(3, 570, "18/10/2019" )
        database.bidDao().insertBid(originalBid)

        // When the task is updated
        val updatedBid =  Bid(4, 570, "18/10/2019")
        database.bidDao().insertBid(updatedBid)

        // THEN - The loaded data contains the expected values
        val loaded = database.bidDao().getBidById(originalBid.id)
        MatcherAssert.assertThat<Bid>(loaded as Bid, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(originalBid.id))
        MatcherAssert.assertThat(loaded?.bid_amount, CoreMatchers.`is`(570))
        MatcherAssert.assertThat(loaded?.bid_date, CoreMatchers.`is`("18/10/2019"))
    }



    @Test
    fun deleteBidById(){
        // Given a task inserted
        val bid =  Bid(5, 570, "18/10/2019")
        database.bidDao().insertBid(bid)

        // When deleting a task by id
        database.bidDao().deleteBidById(bid.id)

        // THEN - The list is empty
        val bids = database.bidDao().getBidById(bid.id)
        //MatcherAssert.assertThat(bids.isEmpty(), CoreMatchers.`is`(true))
        assertNull(bids)
    }



}