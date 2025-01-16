package com.example.cocktailfullapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cocktailfullapplication.data.api.CocktailApiService
import com.example.cocktailfullapplication.data.api.RetrofitInstance
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cocktailfullapplication.ui.viewmodel.CocktailViewModel
import kotlinx.coroutines.test.TestCoroutineDispatcher

class CocktailViewModelTest {

//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: CocktailViewModel

    @Before
    fun setUp() {
        viewModel = CocktailViewModel()
    }

    @Test
    fun testSearchCocktails() {
        viewModel.searchCocktails("Mojito")
        assertTrue(viewModel.cocktails.isNotEmpty())
    }
}

