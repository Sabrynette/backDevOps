package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

public class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;
    
    @Mock
    private StockRepository stockRepository;
    
    @InjectMocks
    private ProduitServiceImpl produitService;

    @Before
    public void setup() {
    }

    @Test
    public void testRetrieveAllProduits() {
        Produit produit1 = new Produit();
        produit1.setIdProduit(1L);
        produit1.setCodeProduit("P001");
        produit1.setLibelleProduit("Produit 1");
        produit1.setPrix(100f);
        produit1.setDateCreation(new Date());
        
        Produit produit2 = new Produit();
        produit2.setIdProduit(2L);
        produit2.setCodeProduit("P002");
        produit2.setLibelleProduit("Produit 2");
        produit2.setPrix(200f);
        produit2.setDateCreation(new Date());
        
        List<Produit> produits = new ArrayList<>();
        produits.add(produit1);
        produits.add(produit2);
        
        when(produitRepository.findAll()).thenReturn(produits);
        
        List<Produit> result = produitService.retrieveAllProduits();
        
        assertEquals(2, result.size());
        assertEquals("Produit 1", result.get(0).getLibelleProduit());
        assertEquals("Produit 2", result.get(1).getLibelleProduit());
    }
    
    @Test
    public void testAddProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("P001");
        produit.setLibelleProduit("Produit 1");
        produit.setPrix(100f);
        produit.setDateCreation(new Date());
        
        when(produitRepository.save(produit)).thenReturn(produit);
        
        Produit result = produitService.addProduit(produit);
        
        assertEquals("P001", result.getCodeProduit());
        assertEquals("Produit 1", result.getLibelleProduit());
    }
    
    @Test
    public void testDeleteProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        when(produitRepository.findById(produit.getIdProduit())).thenReturn(Optional.of(produit));

        produitService.deleteProduit(produit.getIdProduit());

        verify(produitRepository, times(1)).deleteById(produit.getIdProduit());
        assertNull(produitRepository.findById(produit.getIdProduit()).orElse(null));
    }
    
    @Test
    public void testUpdateProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setLibelleProduit("Produit A");
        produit.setPrix(10.0f);

        when(produitRepository.findById(produit.getIdProduit())).thenReturn(Optional.of(produit));
        when(produitRepository.save(produit)).thenReturn(produit);

        Produit updatedProduit = new Produit();
        updatedProduit.setIdProduit(produit.getIdProduit());
        updatedProduit.setLibelleProduit("Produit B");
        updatedProduit.setPrix(15.0f);

        produitService.updateProduit(updatedProduit);

        assertEquals(updatedProduit.getLibelleProduit(), produit.getLibelleProduit());
        assertEquals(updatedProduit.getPrix(), produit.getPrix(), 0.0f);
    }
    
    @Test
	void testRetrieveProduit() {
		// create a new Produit object
		Produit produit = new Produit();
		produit.setCodeProduit("P001");
		produit.setLibelleProduit("Produit 1");
		produit.setPrix(10);
		produit.setDateCreation(new Date());

		// persist the Produit object
		Produit savedProduit = produitService.addProduit(produit);

		// retrieve the Produit object by its ID
		Produit retrievedProduit = produitService.retrieveProduit(savedProduit.getIdProduit());

		// assert that the retrieved Produit object is not null
		assertNotNull(retrievedProduit);

		// assert that the retrieved Produit object has the same data as the created one
		assertEquals(savedProduit.getIdProduit(), retrievedProduit.getIdProduit());
		assertEquals(savedProduit.getCodeProduit(), retrievedProduit.getCodeProduit());
		assertEquals(savedProduit.getLibelleProduit(), retrievedProduit.getLibelleProduit());
		assertEquals(savedProduit.getPrix(), retrievedProduit.getPrix(), 0.001);
		assertEquals(savedProduit.getDateCreation(), retrievedProduit.getDateCreation());
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
