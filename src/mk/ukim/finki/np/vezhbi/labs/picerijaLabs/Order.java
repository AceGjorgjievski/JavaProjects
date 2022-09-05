package mk.ukim.finki.np.vezhbi.labs.picerijaLabs;

public class Order {
    private Item [] items;
    private int [] counts;
    private int totalItems;
    private boolean isLocked;


    public Order() {
        items = new Item[0];
        counts = new int[0];
        totalItems = 0;
        isLocked = false;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(count > 10) throw new ItemOutOfStockException(item);

        if(isLocked) throw new OrderLockedException();

        if(checkItem(item) == -1) {
            Item [] temp = new Item[items.length + 1];
            int [] temp2 = new int[counts.length + 1];
            int i;
            for(i=0; i<items.length; i++) {
                temp[i] = items[i];
                temp2[i] = counts[i];
            }
            temp[i] = item;
            temp2[i] = count;
            ++totalItems;
            items = temp;
            counts = temp2;
        } else {
            int index = checkItem(item);
            counts[index] = count;
        }

    }

    public int checkItem(Item other) {
        for(int i=0; i<items.length; i++) {
            if(items[i].getType().equals(other.getType())) {
                return i;
            }
        }
        return -1;
    }

    public int getPrice() {
        int sum = 0;
        for(int i=0; i<items.length; i++) {
            sum += items[i].getPrice() * counts[i];
        }
        return sum;
    }

    public void removeItem(int index) throws ArrayIndexOutOfBоundsException, OrderLockedException, EmptyOrderException {
        if(isLocked) throw new OrderLockedException();
        if(!checkValidIndex(index)) throw new ArrayIndexOutOfBоundsException(index);
        Item [] temp = new Item[items.length-1];
        int [] temp2 = new int[counts.length-1];
        int j=0,k=0;
        for(int i=0; i<items.length; i++) {
            if(i != index) {
                temp[j++] = items[i];
                temp2[k++] = counts[i];
            }
        }

        --totalItems;
        items = temp;
        counts = temp2;
    }

    public boolean checkValidIndex(int index) throws EmptyOrderException {
        if(index >= 0 && index < items.length) return true;
        else throw new EmptyOrderException();
    }

    public void lock() throws EmptyOrderException {
        if(totalItems > 0) isLocked = true;
        else throw new EmptyOrderException();
    }

    public int getPriceEach(int index) {
        return items[index].getPrice() * counts[index];
    }

    public void displayOrder() {
        StringBuilder sb = new StringBuilder();
        int totalSum = 0;

        for(int i=0; i<items.length; i++) {
            sb.append(String.format("%3d.%-15sx%2d%5d$\n", i+1,items[i].getType(),counts[i],getPriceEach(i)));
            totalSum += counts[i];
        }
        sb.append(String.format("%-22s%5d$","Total:",getPrice()));
        System.out.println(sb.toString());
    }
}
