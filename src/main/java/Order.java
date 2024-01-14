public class Order {
    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public Order() {

    }

    private String[] ingredients;
}
