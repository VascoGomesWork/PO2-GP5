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
    //private int[] fiveArray = {5};
    private int[][] moneyBox = {{5, 0}, {10, 0}, {20, 0}, {50, 0}};
    private int[][] moneyBoxCopy = moneyBox.clone();
    int[] coinsToRetrieve = new int[50];
    int coinsCounter = 0;

    public Dispenser(int productPrice, int nProducts) {
        this.productPrice = productPrice;
        this.nProducts = nProducts;
        this.insertedMoney = 0;
        this.salesMoney = 0;
    }

    public int insertCoin(int coin) {
        int counter = 0;
        if(isCoinInMoneyBox(coin)){
            this.insertedMoney += coin;
            //It is an array of arrays
            counter = this.moneyBox[getCoinIndex(coin)][1];
            //Increments the number of a specific coin
            this.moneyBox[getCoinIndex(coin)][1] = ++counter;

            System.out.println("Coin = " + this.moneyBox[getCoinIndex(coin)][0] + " Quantity = " + this.moneyBox[getCoinIndex(coin)][1]);
            System.out.println();
            return this.insertedMoney;
        }
        //Clones Money Box
        this.moneyBoxCopy = moneyBox.clone();
        return this.insertedMoney;
    }

    /**
     * Resume: Compares the coin given to the money box and returns it's index
     * @param coin
     * @return
     */
    private int getCoinIndex(int coin) {
        for (int coinIndex = 0; coinIndex < moneyBox.length; coinIndex++) {
            if(coin == moneyBox[coinIndex][0]){
                return coinIndex;
            }
        }
        return -1;
    }

    private boolean isCoinInMoneyBox(int coin) {
        for (int i = 0; i < this.moneyBox.length; i++) {
            //Checks if coin is present in Money Box
            if(coin == this.moneyBox[i][0]) {
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
        //Clones Money Box
        this.moneyBoxCopy = moneyBox.clone();
    }

    public int getAdminChange(){
        return this.insertedMoney - this.productPrice * this.buyProductQuantity;
    }

    /**
     * Resume : Function that retrieves the change of the product by trial and error due to random number from money box
     * @return
     */
    public int getChange() {
        int[] coinsForChange = new int[50];
        int i = 0;
        int change = getAdminChange();
        if(change > 0) {
            while (countChange(coinsForChange) != change) {
                coinsForChange = getCoinByCoin(moneyBox[i][0]);
                if (countChange(coinsForChange) > change) {
                    coinsToRetrieve = new int[50];
                    coinsForChange = new int[50];
                    coinsCounter = 0;
                    moneyBox = moneyBoxCopy;
                    //Gets a Random Coin out of the box and starts from there to make the loop
                    int randomCoinIndex = generateRandomCoinIndex();
                    //Check if the random generated coin is valid if not calls the function to generate another one
                    int random = checkRandomIsValid((int) ((Math.random() * (moneyBox.length)) + 0)) ? randomCoinIndex : generateRandomCoinIndex();
                    coinsForChange = getCoinByCoin(moneyBox[random][0]);
                }
                i++;
                if (i == moneyBox.length) {
                    i = 0;
                }
            }
        }
        //In the End Retrieves the Coins from Money Box with the help of coinsToRetrieve array
        retrieveCoinsFromMoneyBox();
        return change;
    }

    /**
     * Resume : Function that Delete Coins from Money Box
     */
    private void retrieveCoinsFromMoneyBox() {
        for (int j = 0; j < coinsCounter; j++) {
            System.out.println("Coins to Retrieve = " + coinsToRetrieve[j]);
            moneyBox[getCoinIndex(coinsToRetrieve[j])][1]--;
        }
    }

    /**
     * Resume : Function that Generates a Random Generated Coin Index
     * @return random coin index
     */
    private int generateRandomCoinIndex() {
        return (int) ((Math.random() * (moneyBox.length)) + 0);
    }

    /**
     * Resume: Check if the Index Generated by Random has Coins
     * @param random
     * @return true or false
     */
    private boolean checkRandomIsValid(int random) {
        if(moneyBox[random][1] > 0) {
            return true;
        }
        return false;
    }

    /**
     * Resume : Returns Coin By Coin from the MoneyBox
     * @param coin
     * @return array coinsToRetrieve
     */
    private int[] getCoinByCoin(int coin) {
        coinsToRetrieve[coinsCounter] = coin;
        coinsCounter++;
        return coinsToRetrieve;
    }

    /**
     * Resume : Function that Counts the Change Coins Inside a Coins Array
     * @param coinsForChange
     * @return coinsAmount
     */
    private int countChange(int[] coinsForChange) {
        int changeCounter = 0;
        for (int i = 0; i < coinsForChange.length; i++) {
            changeCounter += coinsForChange[i];
        }
        return changeCounter;
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

    public int getCoinAmount(int coin) {
        return this.moneyBox[getCoinIndex(coin)][1];
    }
}
