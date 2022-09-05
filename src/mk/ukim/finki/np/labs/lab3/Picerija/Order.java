package mk.ukim.finki.np.labs.lab3.Picerija;

public class Order implements Item{
    private Item [] items;
    private int [] counts;
    private boolean isLocked;


    public Order() {
        items = new Item[0];
        counts = new int[0];
        isLocked = false;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(isLocked){
            throw new OrderLockedException();
        }
        if(count > 10){
            throw new ItemOutOfStockException(item);
        }
        if(alreadyInOrder(item) == 0){
            Item [] tempI = new Item[items.length+1];
            int [] tempC = new int[items.length+1];

            for(int i=0; i< items.length; i++){
                tempI[i] = items[i];
                tempC[i] = counts[i];
            }
            tempI[items.length] = item;
            tempC[items.length] = count;

            items = tempI;
            counts = tempC;
        } else {
            int ind = alreadyInOrder(item);
            counts[ind] = count;
        }
    }

    public int alreadyInOrder(Item item){
        for(int i=0; i<items.length; i++){
            if(items[i].getName().equals(item.getName())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getPrice() {
        int total = 0;
        for(int i=0; i< items.length; i++){
            total += getPriceEach(i);
        }
        return total;
    }

    public int getPriceEach(int ind){
        return items[ind].getPrice() * counts[ind];
    }

    @Override
    public String getName() {
        return null;
    }

    public void displayOrder() {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i< items.length; i++){
            sb.append(String.format("%3d.%-15sx%2d%5d$\n", i+1,items[i].getName(),counts[i],getPriceEach(i)));
        }
        sb.append(String.format("%-22s%5d$","Total:",getPrice()));
        System.out.println(sb.toString());
    }

    public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException {
        if(isLocked){
            throw new OrderLockedException();
        }
        if(idx < 0 || idx > counts.length){
            throw new ArrayIndexOutOfBоundsException(idx);
        }
        Item [] tempI = new Item[items.length-1];
        int [] tempC = new int[counts.length-1];

        for(int i=0; i< idx; i++){
            tempI[i] = items[i];
            tempC[i] = counts[i];
        }
        for(int i=idx; i< items.length-1; i++){
            tempI[i] = items[i];
            tempC[i] = counts[i];
        }

        items = tempI;
        counts = tempC;
    }

    public void lock() throws EmptyOrderException {
        if(items.length < 1){
            throw new EmptyOrderException();
        }
        isLocked = true;
    }
}
