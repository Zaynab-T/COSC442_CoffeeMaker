package edu.towson.cis.cosc442.project4.coffeemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {
    

    private CoffeeMaker cm;
    private Recipe r1, r2, r3, r4;

    private Recipe makeRecipe(String name, int price, int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
        Recipe r = new Recipe();
        r.setName(name);
        r.setPrice(price);
        r.setAmtCoffee(amtCoffee);
        r.setAmtMilk(amtMilk);
        r.setAmtSugar(amtSugar);
        r.setAmtChocolate(amtChocolate);
        return r;
    }

    @BeforeEach 
    public void setUp() {
        cm = new CoffeeMaker();
        r1 = makeRecipe("Coffee", 50, 3, 1, 1, 0);
        r2 = makeRecipe("Mocha", 75, 3, 1, 1, 2);
        r3 = makeRecipe("Latte", 100, 3, 3, 1, 0);
        r4 = makeRecipe("Hot Chocolate", 65, 0, 2, 1, 3);
    }

    private void assertInventory(int coffee, int milk, int sugar, int chocolate) {
       
        Inventory inv = cm.checkInventory();

        assertEquals(coffee, inv.getCoffee());
        assertEquals(milk, inv.getMilk());
        assertEquals(sugar, inv.getSugar());
        assertEquals(chocolate, inv.getChocolate());
    }

    @Test void addRecipe_newRecipe() {  

        assertTrue(cm.addRecipe(r1));
    }

    @Test void addRecipe_duplicateName() { 

        cm.addRecipe(r1);
        Recipe duplicate = makeRecipe("Coffee", 60, 2, 2, 2, 2);
        assertFalse(cm.addRecipe(duplicate));
     }

    @Test void addRecipe_threeRecipes() { 

        assertTrue(cm.addRecipe(r1));
        assertTrue(cm.addRecipe(r2));
        assertTrue(cm.addRecipe(r3));
     }

    @Test void addRecipe_fourthRecipe() { 

        cm.addRecipe(r1);
        cm.addRecipe(r2);
        cm.addRecipe(r3);

        assertFalse(cm.addRecipe(r4));
     } 

    @Test void deleteRecipe_existing() { 

        cm.addRecipe(r1);
        assertTrue(cm.deleteRecipe(r1));
        assertNull(cm.getRecipeForName(r1.getName()).getName());
     }

    @Test void deleteRecipe_notInMaker() { 

        cm.addRecipe(r1);
        assertFalse(cm.deleteRecipe(r2));
     }

    @Test void deleteRecipe_null() { 

        assertFalse(cm.deleteRecipe(null));
     }

    @Test void editRecipe_existing() { 

        cm.addRecipe(r1);
        Recipe edited = makeRecipe("Coffee", 60, 2, 2, 2, 2);
        assertTrue(cm.editRecipe(r1, edited));
        assertEquals(60, cm.getRecipeForName("Coffee").getPrice());
     } 

    @Test void editRecipe_notInMaker() { 

        cm.addRecipe(r1);
        Recipe edited = makeRecipe("Mocha", 80, 2, 2, 2, 2);
        assertFalse(cm.editRecipe(r2, edited));
     }

    @Test void addInventory_positiveAmounts() { 

        assertTrue(cm.addInventory(1, 2, 3, 4));
        assertInventory(16, 17, 18, 19);
     }

    @Test void addInventory_zeroAmounts() { 

        assertTrue(cm.addInventory(0, 0, 0, 0));
        assertInventory(15, 15, 15, 15);
     } 

    @Test void addInventory_negativeCoffee() { 

        assertFalse(cm.addInventory(-1, 0, 0, 0));
        assertInventory(15, 15, 15, 15);
     }

    @Test void addInventory_negativeMilk() { 

        assertFalse(cm.addInventory(0, -1, 0, 0));
        assertInventory(15, 15, 15, 15);
     }

    @Test void addInventory_negativeSugar() { 

        assertFalse(cm.addInventory(0, 0, -1, 0));
        assertInventory(15, 15, 15, 15);
     }

    @Test void addInventory_negativeChocolate() { 

        assertFalse(cm.addInventory(0, 0, 0, -1));
        assertInventory(15, 15, 15, 15);
     }

    @Test void checkInventory_newAllFifteen() { 

        assertInventory(15, 15, 15, 15);
     }

    @Test void makeCoffee_exactPayment() { 

        cm.addRecipe(r1);
        assertEquals(0, cm.makeCoffee(r1, 50));
     }

    @Test void makeCoffee_overpay() { 

        cm.addRecipe(r1);
        assertEquals(10, cm.makeCoffee(r1, 60));
     }

    @Test void makeCoffee_success() { 

        cm.addRecipe(r1);
        cm.makeCoffee(r1, 50);

        assertInventory(12, 14, 14, 15);
     }

    @Test void makeCoffee_underpay() { 

        cm.addRecipe(r1);
        assertEquals(49, cm.makeCoffee(r1, 49));
        assertInventory(15, 15, 15, 15);
     }

    @Test void makeCoffee_notEnoughIngredients() { 

        Recipe bigCoffee = makeRecipe("Big Coffee", 50, 20, 20, 20, 20);
        cm.addRecipe(bigCoffee);
        assertEquals(50, cm.makeCoffee(bigCoffee, 50));
        assertInventory(15, 15, 15, 15);
     }

    @Test void getRecipes_afterAdd() { 

        cm.addRecipe(r1);
        boolean found = false;
        for (Recipe r : cm.getRecipes()) {
            if ("Coffee".equals(r.getName())) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test void getRecipeForName_existing() { 

        cm.addRecipe(r1);
        cm.addRecipe(r2);
        assertSame(r2, cm.getRecipeForName("Mocha"));
     }

    @Test void getRecipeForName_missing() { 

        cm.addRecipe(r1);
        assertNull(cm.getRecipeForName("Latte").getName());
     }
}

