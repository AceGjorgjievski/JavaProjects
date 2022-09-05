package mk.ukim.finki.np.vezhbi.za_juni.pizza_shop;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> orderItemList;
    private boolean isLocked;

    public Order() {
        this.orderItemList = new ArrayList<>();
        this.isLocked = false;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(this.isLocked) throw new OrderLockedException();
        OrderItem orderItem = new OrderItem(item, count);
        int index = orderItemList.indexOf(orderItem);
        if(index != -1) {
            orderItemList.set(index,orderItem);
        } else {
            orderItemList.add(orderItem);
        }
    }

    public int getPrice() {
        return orderItemList.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
    }

    public void displayOrder() {
        String.format("");
    }

    public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException {
        if(this.isLocked) throw new OrderLockedException();
        OrderItem removedItem = this.orderItemList.remove(idx);
        if(removedItem == null) {
            throw new ArrayIndexOutOfBоundsException(idx);
        }
    }

    public void lock() throws EmptyOrderException {
        if(this.orderItemList.size() >=1 ) {
            this.isLocked = true;
        } else throw new EmptyOrderException();
    }
}
