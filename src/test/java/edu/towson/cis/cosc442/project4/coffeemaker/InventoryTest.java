package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InventoryTest {
    
    private Inventory inventory;
    private Recipe recipe;

    @BeforeEach 
    public void setUp() {
        
        inventory = new Inventory();
        recipe = new Recipe();
    }

    @Test
    void newInventory_FifteenAmt() {
       
        assertEquals(15, inventory.getChocolate());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());
        assertEquals(15, inventory.getCoffee());
    }

    @ParameterizedTest 
    @ValueSource (ints = {0, 1})
    void setChocolate_ValidValues(int amt) {
       
        inventory.setChocolate(amt);
        assertEquals(amt, inventory.getChocolate());
    }

    @Test
    void setChocolate_NegativeValue() {

        inventory.setChocolate(-1);
        assertEquals(0, inventory.getChocolate());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void setCoffee_ValidValues(int amt) {

        inventory.setCoffee(amt);
        assertEquals(amt, inventory.getCoffee());
    }

    @Test
    void setCoffee_NegativeValue() {

        inventory.setCoffee(-1);
        assertEquals(0, inventory.getCoffee());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void setMilk_ValidValues(int amt) {
        
        inventory.setMilk(amt);
        assertEquals(amt, inventory.getMilk());
    }

    @Test
    void setMilk_NegativeValue() {
        
        inventory.setMilk(-1);
        assertEquals(0, inventory.getMilk());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void setSugar_ValidValues(int amt) {
        
        inventory.setSugar(amt);
        assertEquals(amt, inventory.getSugar());
    }

    @Test
    void setSugar_NegativeValue() {

        inventory.setSugar(-1);
        assertEquals(0, inventory.getSugar());
    }

    @Test 
    void enoughIngredients_needsLess() {

        recipe.setAmtCoffee(5);
        recipe.setAmtMilk(5);
        recipe.setAmtSugar(5);
        recipe.setAmtChocolate(5);

        boolean result = inventory.enoughIngredients(recipe);

        assertTrue(result);
    }

    @Test 
    void enoughIngredients_needsExactlyInventory() {

        recipe.setAmtCoffee(15);
        recipe.setAmtMilk(15);
        recipe.setAmtSugar(15);
        recipe.setAmtChocolate(15);

        boolean result = inventory.enoughIngredients(recipe);

        assertTrue(result);
    }

    @Test
    void enoughIngredients_needsMoreCoffee() {

        recipe.setAmtCoffee(16);
        recipe.setAmtMilk(15);
        recipe.setAmtSugar(15);
        recipe.setAmtChocolate(15);

        boolean result = inventory.enoughIngredients(recipe);

        assertFalse(result);
    }

    @Test 
    void enoughIngredients_needsMoreMilk() {

        recipe.setAmtCoffee(15);
        recipe.setAmtMilk(16);
        recipe.setAmtSugar(15);
        recipe.setAmtChocolate(15);

        boolean result = inventory.enoughIngredients(recipe);

        assertFalse(result);
    }

    @Test
    void enoughIngredients_needsMoreSugar() {

        recipe.setAmtCoffee(15);
        recipe.setAmtMilk(15);
        recipe.setAmtSugar(16);
        recipe.setAmtChocolate(15);

        boolean result = inventory.enoughIngredients(recipe);

        assertFalse(result);
    }

    @Test 
    void enoughIngredients_needsMoreChocolate() {

        recipe.setAmtCoffee(15);
        recipe.setAmtMilk(15);
        recipe.setAmtSugar(15);
        recipe.setAmtChocolate(16);

        boolean result = inventory.enoughIngredients(recipe);

        assertFalse(result);
    }

    @Test 
    void toString_listsAllAmounts() {

        String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
        String actual = inventory.toString();

        assertEquals(expected, actual);
    }
}
