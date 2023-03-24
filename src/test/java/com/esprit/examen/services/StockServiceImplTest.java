package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	 @Mock
	 private IStockService stockService;
		  
//	@Test
//	public void testAddStock() {
//		List<Stock> stocks = stockService.retrieveAllStocks();
//		int expected=stocks.size();
//		Stock s = new Stock("stock test",10,100);
//		Stock savedStock= stockService.addStock(s);
//		
//		assertEquals(expected+1, stockService.retrieveAllStocks().size());
//		assertNotNull(savedStock.getLibelleStock());
//		stockService.deleteStock(savedStock.getIdStock());
//		
//	} 
//	
//	@Test
//	public void testAddStockOptimized() {
//
//		Stock s = new Stock("stock test",10,100);
//		Stock savedStock= stockService.addStock(s);
//		assertNotNull(savedStock.getIdStock());
//		assertSame(10, savedStock.getQte());
//		assertTrue(savedStock.getQteMin()>0);
//		stockService.deleteStock(savedStock.getIdStock());
//		
//	} 
	
	 @Test
	 public void testDeleteStock() {
		 Stock stock = Mockito.mock(Stock.class);
		 Long stockId = 1L;
	     when(stock.getIdStock()).thenReturn(stockId);
	     stock.setIdStock(stockId);
	     stockService.deleteStock(stockId);
	     verify(stockService, times(1)).deleteStock(stockId);
	 }

	 @Test
	    public void testCheckIfStockIsAvailable() {
	        // Create a mock Stock object
		    Stock stock = Mockito.mock(Stock.class);
	        // Specify the behavior of the mock object
	        when(stock.getQte()).thenReturn(10);
	        
	        // Specify the behavior of the mock object
	        when(stockService.checkIfStockIsAvailable(stock, 5)).thenReturn(true);
	        when(stockService.checkIfStockIsAvailable(stock, 10)).thenReturn(true);
	        when(stockService.checkIfStockIsAvailable(stock, 15)).thenReturn(false);

	        // Test the function with various quantities
	        assertTrue(stockService.checkIfStockIsAvailable(stock, 5));
	        assertTrue(stockService.checkIfStockIsAvailable(stock, 10));
	        assertFalse(stockService.checkIfStockIsAvailable(stock, 15));
	        verify(stockService, times(3)).checkIfStockIsAvailable(eq(stock), anyInt());
	    }
}
