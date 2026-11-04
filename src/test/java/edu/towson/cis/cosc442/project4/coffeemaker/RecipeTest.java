package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RecipeTest {
    
    private Recipe recipe;

    @BeforeEach 
    public void setUp() {
        recipe = new Recipe();
    }

    @Test 
    void newRecipe_ZeroAmtAndPrice() {

        assertEquals(0, recipe.getAmtChocolate());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
    }

    @ParameterizedTest 
    @ValueSource (ints = {0, 1})
    void setAmtChocolate_ValidValues(int amt) {

        recipe.setAmtChocolate(amt);
        assertEquals(amt, recipe.getAmtChocolate());
    }

    @Test
    void setAmtChocolate_NegativeValue() {

        recipe.setAmtChocolate(5);
        recipe.setAmtChocolate(-1);
        assertEquals(0, recipe.getAmtChocolate());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void setAmtCoffee_ValidValues(int amt) {

        recipe.setAmtCoffee(amt);
        assertEquals(amt, recipe.getAmtCoffee());
    }

    @Test 
    void setAmtCoffee_NegativeValue() {

        recipe.setAmtCoffee(5);
        recipe.setAmtCoffee(-1);
        assertEquals(0, recipe.getAmtCoffee());
    }

    @ParameterizedTest 
    @ValueSource (ints = {0, 1})
    void setAmtMilk_ValidValues(int amt) {

        recipe.setAmtMilk(amt);
        assertEquals(amt, recipe.getAmtMilk());
    }

    @Test 
    void setAmtMilk_NegativeValue() {
        
        recipe.setAmtMilk(5);
        recipe.setAmtMilk(-1);
        assertEquals(0, recipe.getAmtMilk());
    }

    @ParameterizedTest 
    @ValueSource (ints = {0, 1})
    void setAmtSugar_ValidValues(int amt) {

        recipe.setAmtSugar(amt);
        assertEquals(amt, recipe.getAmtSugar());
    }

    @Test 
    void setAmtSugar_NegativeValue() {

        recipe.setAmtSugar(5);
        recipe.setAmtSugar(-1);
        assertEquals(0, recipe.getAmtSugar());
    }

    @Test 
    void setName_ValidName() {

        recipe.setName("Cappuccino");
        assertEquals("Cappuccino", recipe.getName());
    }

    @ParameterizedTest 
    @ValueSource (ints = {0, 1})
    void setPrice_ValidValues(int price) {

        recipe.setPrice(price);
        assertEquals(price, recipe.getPrice());
    }

    @Test 
    void setPrice_NegativeValue() {

        recipe.setPrice(5);
        recipe.setPrice(-1);
        assertEquals(0, recipe.getPrice());
    }

    @Test 
    void equals_sameName() {

        recipe.setName("Cappuccino");
        Recipe other = new Recipe();
        other.setName("Cappuccino");
        assertTrue(recipe.equals(other));
    }

    @Test 
    void equals_differentName() {

        recipe.setName("Cappuccino");
        Recipe other = new Recipe();
        other.setName("Latte");
        assertFalse(recipe.equals(other));
    }

    @Test 
    void equals_otherNameNull() {

        recipe.setName("Cappuccino");
        Recipe other = new Recipe();
        other.setName(null);
        assertFalse(recipe.equals(other));
    }

    @Test 
    void equals_thisNameNull() {

        recipe.setName(null);
        Recipe other = new Recipe();
        other.setName("Latte");
        assertFalse(recipe.equals(other));
    }

    @Test 
    void toString_returnsName() {
        recipe.setName("Cappuccino");
        assertEquals("Cappuccino", recipe.toString());
    }

}
