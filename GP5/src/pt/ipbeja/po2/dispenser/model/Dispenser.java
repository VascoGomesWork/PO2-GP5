package pt.ipbeja.po2.dispenser.model;

/**
 * @author Vasco Gomes 19921
 * @date 27/04/2022
 */
public class Dispenser {

    private int insertedMoney;
    private int productPrice;
    private int nProducts;
    private int salesMoney;
    //Variable with the number os products that user wants to buy
    private int buyProductQuantity;
    //Array with the possible coins to be inserted and it's quantities
    private int moneyBox[] = {5, 10, 20, 50};

    public Dispenser(int productPrice, int nProducts) {
        this.productPrice = productPrice;
        this.nProducts = nProducts;
        this.insertedMoney = 0;
        this.salesMoney = 0;
    }

    public int insertCoin(int coin) {
        if(coinInMoneyBox(coin)){
            this.insertedMoney += coin;
        }
        return this.insertedMoney;
    }

    private boolean coinInMoneyBox(int coin) {
        for (int i = 0; i < this.moneyBox.length; i++) {
            //Checks if coin is present in Money Box
            if(coin == this.moneyBox[i]) {
                return true;
            }
        }
        return false;
    }

    public int cancelBuyProduct() {
        int returnInsertedMoney = this.insertedMoney;
        this.insertedMoney = 0;
        this.salesMoney = 0;
        return returnInsertedMoney;
    }

    public int getSalesMoney() {
        return this.salesMoney;
    }

    public int getNProducts(){ return this.nProducts; }

    public void buyProduct(int productQuantity) {

        this.buyProductQuantity = productQuantity;
        if(this.insertedMoney >= this.productPrice * this.buyProductQuantity){
            //Puts the sales money equals to product price
            this.salesMoney = this.productPrice * this.buyProductQuantity;
            //Gets the change
            getChange();
            //Decrements the Product
            this.nProducts-=this.buyProductQuantity;
        }
    }

    public int getChange() {
        return this.insertedMoney - this.productPrice * this.buyProductQuantity;
    }

    public void setProductPrice(int newProductPrice) {
        this.productPrice = newProductPrice % 10 == 0 ? newProductPrice : this.productPrice;
    }

    public int getProductPrice() {
        return this.productPrice;
    }

    public void setNProducts(int newNumProducts) {
        this.nProducts = newNumProducts;
    }
}
