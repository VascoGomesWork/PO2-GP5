package pt.ipbeja.po2.dispenser.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vasco Gomes 19921
 * @date 27/04/2022
 */
class DispenserTest {

    private Dispenser dispenser;

    @BeforeEach
    void setUp() {
        //Create Dispenser with Product Price to 40
        this.dispenser = new Dispenser(40, 10);

    }

    @Test
    void testInsertCoin() {


        //Inserts a Coin 20
        int balance = this.dispenser.insertCoin(20);

        //Expects the balance being 20
        assertEquals(20, balance);

        //Inserts another coin of 10
        balance = this.dispenser.insertCoin(10);

        //Expects the balance being 30
        assertEquals(30, balance);
    }

    @Test
    void testCancelBuyProduct() {

        //Inserts a Coin 20
        int balance = this.dispenser.insertCoin(20);
        balance = this.dispenser.insertCoin(10);

        //Expects the balance being 20
        assertEquals(30, balance);

        //Cancels the Buy of the Product
        int insertedMoney = this.dispenser.cancelBuyProduct();

        //Expects the balance being 30
        assertEquals(30, insertedMoney);

        //Checks if Dispenser N Products is the same
        int dispenserNProducts = this.dispenser.getNProducts();

        assertEquals(10, dispenserNProducts);

        //Gets dispenser machine sales money
        int salesMoney = this.dispenser.getSalesMoney();

        //Expects the sales money being 0
        assertEquals(0, salesMoney);
    }

    @Test
    void testBuyProduct() {

        //Inserts a Coin 20
        int balance = this.dispenser.insertCoin(50);
        assertEquals(50, balance);

        //Buys a Product and reduces the amount of Products
        this.dispenser.buyProduct(1);

        //Checks if the change is correct
        int change = this.dispenser.getChange();
        assertEquals(10, change);

        //Checks if the products quantity is less
        assertEquals(9, this.dispenser.getNProducts());

        //Checks if the sales money is 40
        assertEquals(40, this.dispenser.getSalesMoney());

    }

    @Test
    void testInsufficientMoney() {

        //Inserts a Coin 20
        int balance = this.dispenser.insertCoin(20);
        balance = this.dispenser.insertCoin(5);
        assertEquals(25, balance);

        this.dispenser.buyProduct(1);

        //Checks if the change is correct
        int change = this.dispenser.getChange();
        assertEquals(-15, change);

        //Checks if dispenser N Products is 10
        assertEquals(10, this.dispenser.getNProducts());

        //Checks if sales money is 0
        assertEquals(0, this.dispenser.getSalesMoney());
    }

    @Test
    void testAdminSetProductPrice() {

        //Checks if the Product Price is 40
        assertEquals(40, this.dispenser.getProductPrice());

        //Admin Sets the Product Price
        this.dispenser.setProductPrice(60);

        //Checks if the Product Price has changed
        assertEquals(60, this.dispenser.getProductPrice());
    }

    @Test
    void testAdminSetProductQuantity() {

        //Gets Number of Products
        assertEquals(10, this.dispenser.getNProducts());

        //Changes the Number of Products
        this.dispenser.setNProducts(20);

        //Checks if the Number of Products has Changed
        assertEquals(20, this.dispenser.getNProducts());

    }

    @Test
    void testAdminGetNProducts() {
        //Gets Number of Products
        assertEquals(10, this.dispenser.getNProducts());
    }

    @Test
    void testAdminGetSalesMoney() {
        //Checks if sales money is at 0
        assertEquals(0, this.dispenser.getSalesMoney());

        //Inserts a 50 coin
        int balance = this.dispenser.insertCoin(50);
        assertEquals(50, balance);

        //Buys a Product
        this.dispenser.buyProduct(1);

        //Checks if the Sales Moneys is 40
        assertEquals(40, this.dispenser.getSalesMoney());

        //Checks if the Change is 10
        assertEquals(10, this.dispenser.getChange());
    }

    @Test
    void testAdminChangesProductPriceToMultipleOf10() {

        //Tries to Set the Product Price to 21
        this.dispenser.setProductPrice(21);

        //Check if the Product Price stays the same
        assertEquals(40, this.dispenser.getProductPrice());

        //Tries to Set the Product Price to 50
        this.dispenser.setProductPrice(50);

        //Check if the value has changed to 50
        assertEquals(50, this.dispenser.getProductPrice());
    }

    @Test
    void testBuyMoreThanOneProduct() {

        //Checks if Dispenser Products is 10
        assertEquals(10, this.dispenser.getNProducts());

        int balance = this.dispenser.insertCoin(120);
        assertEquals(120, balance);

        //Buys 3 Products
        this.dispenser.buyProduct(3);

        //Checks if the N products Reduces
        assertEquals(7, this.dispenser.getNProducts());

        //Checks if Change is 0
        assertEquals(0, this.dispenser.getChange());

        //Checks if Sales Money is 120
        assertEquals(120, this.dispenser.getSalesMoney());
    }

    @Test
    void testInsertCoinsAndGetChangeWithAvailableCoins() {
        //int[] acceptedMoney = this.dispenser.acceptedMoney();
        //assertEquals([5, 10, 20, 50], acceptedMoney);
    }
}