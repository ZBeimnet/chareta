package com.example.chareta.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.chareta.data.local.CharetaDatabase
import com.example.chareta.data.model.Bid
import com.example.chareta.data.model.Item
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
class ItemDaoTest {

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
    fun insertItemTest(){
        // GIVEN - insert a task
        val item = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(item)

        // WHEN - Get the task by id from the database
        val loaded = database.itemDao().getItemById(item.id)

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Item>(loaded as Item, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(item.id))
        MatcherAssert.assertThat(loaded.item_name, CoreMatchers.`is`(item.item_name))
        MatcherAssert.assertThat(loaded.item_description, CoreMatchers.`is`(item.item_description))

    }

    @Test
    fun insertItemReplacesOnConflict() {
        // Given that a task is inserted
        val item1 = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(item1)

        // When a task with the same id is inserted
        val newItem = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(newItem)

        // THEN - The loaded data contains the expected values
        val loaded = database.itemDao().getItemById(newItem.id)

        MatcherAssert.assertThat<Item>(loaded as Item, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(item1.id))
        MatcherAssert.assertThat(loaded?.item_name, CoreMatchers.`is`("Item Name"))
        MatcherAssert.assertThat(loaded?.item_description, CoreMatchers.`is`("Item Description"))

    }



    @Test
    fun updateItemAndGetById(){
        // When inserting a task
        val item1 = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(item1)

        // When the task is updated
        val item2 = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(item2)

        // THEN - The loaded data contains the expected values
        val loaded = database.itemDao().getItemById(item2.id)
        MatcherAssert.assertThat<Item>(loaded as Item, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(loaded?.id, CoreMatchers.`is`(item2.id))
        MatcherAssert.assertThat(loaded?.item_name, CoreMatchers.`is`("Item Name"))
        MatcherAssert.assertThat(loaded?.item_description, CoreMatchers.`is`("Item Descripiton"))
    }



    @Test
    fun deleteItemById(){
        // Given a task inserted
        val item = Item(1, "Item Name", "Item Descripiton",500,"12/12/2109","12/12/901")

        database.itemDao().insertItem(item)

        // When deleting a task by id
        database.bidDao().deleteBidById(item.id)

        // THEN - The list is empty
        val items = database.itemDao().getItemById(item.id)
        //MatcherAssert.assertThat(bids.isEmpty(), CoreMatchers.`is`(true))
        assertNull(items)
    }



}