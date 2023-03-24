package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import org.junit.Before;
import org.junit.After;


    @RunWith(SpringRunner.class)
	@SpringBootTest
	public class ProduitServiceImplTest {
		private Produit produit = new Produit();
		
		@Autowired
	    private IProduitService produitService;
	    @Mock
	    private ProduitRepository produitRepository;
	    
	    @Mock
	    private StockRepository stockRepository;
		
		@Before
		@Transactional
		public void setUp() throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("30/09/2000");
		    Produit newProduit = new Produit(null, "P001", "Test Produit", 100f,date, date, null, null, null);
		    produit = produitService.addProduit(newProduit);
		    assertNotNull(produit.getIdProduit()); 
		    assertEquals("P001", produit.getCodeProduit());
		    assertEquals("Test Produit", produit.getLibelleProduit());
		}

	    @After
	    public void tearDown() {
	        produitService.deleteProduit(produit.getIdProduit());
			assertNull(produitService.retrieveProduit(produit.getIdProduit()));    
	    }

		  
		  
	    @Test
	    @Transactional
	    public void testRetrieveAllProduits() throws ParseException {
	    	int insialSize = produitService.retrieveAllProduits().size();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dateFormat.parse("30/09/2000");
	        Produit produit1 = new Produit(null, "P001", "Test Produit", 100f,date, date, null, null, null);
	        produit1 = produitService.addProduit(produit1);
	        assertEquals(insialSize +1, produitService.retrieveAllProduits().size());
			produitService.deleteProduit(produit1.getIdProduit());

	    }
	    

	    
	    @Test
	    public void testUpdateProduit() {
	        Produit updatedProduit = new Produit();
	        updatedProduit.setIdProduit(produit.getIdProduit());
	        updatedProduit.setLibelleProduit("Produit B");
	        updatedProduit.setPrix(15.0f);
	        Produit result = produitService.updateProduit(updatedProduit);
	        assertEquals(updatedProduit.getLibelleProduit(), result.getLibelleProduit());
	        assertEquals(updatedProduit.getPrix(), result.getPrix(), 0.0f);
	    }

	    @Test
	    public void testRetrieveProduit() {
	        Produit retrievedProduit = produitService.retrieveProduit(produit.getIdProduit());
	        
	        assertNotNull("The retrieved produit should not be null", retrievedProduit);
	        assertEquals("The retrieved produit should have the same ID as the saved one", produit.getIdProduit(), retrievedProduit.getIdProduit());
	        assertEquals("The retrieved produit should have the same code as the saved one", produit.getCodeProduit(), retrievedProduit.getCodeProduit());
	        assertEquals("The retrieved produit should have the same libelle as the saved one", produit.getLibelleProduit(), retrievedProduit.getLibelleProduit());
	        assertEquals("The retrieved produit should have the same prix as the saved one", produit.getPrix(), retrievedProduit.getPrix(), 0.001);
	        assertEquals("The retrieved produit should have the same date de creation as the saved one", produit.getDateCreation(), retrievedProduit.getDateCreation());
	    }
	
	
	
	   
	    @Test
	    public void testAssignProduitToStock() {
	        // Create mock objects
	        Produit produit = new Produit();
	        produit.setIdProduit(1L);
	        Stock stock = new Stock();
	        stock.setIdStock(1L);
	        // Set up mock repository methods
	        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
	        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
			produit.setStock(stock);
	        // Check if the stock has been assigned to the produit
	        assertEquals(stock, produit.getStock());
		
	    }
	    
	}
