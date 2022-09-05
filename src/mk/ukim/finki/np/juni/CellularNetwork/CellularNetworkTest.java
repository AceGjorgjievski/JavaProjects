package mk.ukim.finki.np.juni.CellularNetwork;


import java.util.*;

class CellFullException extends Exception {
    public CellFullException(String id) {
        super(String.format("CellFullException: {%s}",id));
    }
}

class CellClass {
    int capacity;
    List<String> numbers;
    int maxCapacity;

    public CellClass(int capacity, List<String> numbers) {
        this.capacity = capacity;
        this.numbers = numbers;
        maxCapacity = capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addNumber(String number){
        numbers.add(number);
    }

    public void decreaseCapacity() {
        this.capacity -= 1;
    }
    public void increaseCapacity() {
        this.capacity += 1;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }


}

class CellularNetwork {
    String name;
    String [] cellIds;
    int [] capacities;
    Map<String, CellClass> map;
    //cell-0:41 cell-1:39
    //cell-1 070609890

    public CellularNetwork(String name, String [] cellIds, int [] capacities){
        this.name = name;
        map = new HashMap<>();
        for(int i=0;i< cellIds.length; i++) {
            map.put(cellIds[i], new CellClass(capacities[i],new ArrayList<>()));
        }

    }

    public void makeCall(String cellId, String number) throws CellFullException {
        if(map.get(cellId).getCapacity() == 0) throw new CellFullException(cellId);
        if(map.get(cellId) != null) {
            map.get(cellId).addNumber(number);
            map.get(cellId).decreaseCapacity();
        }
    }


    public void handover(String number, String fromCellId, String toCellId) throws CellFullException {
        CellClass toSend = map.get(fromCellId);
        map.get(fromCellId).increaseCapacity();

        if(map.get(toCellId).getCapacity() == map.get(toCellId).getMaxCapacity()) throw new CellFullException(toCellId);
        map.put(toCellId,toSend);
        map.get(toCellId).increaseCapacity();
    }

    public void findNumber(String number) {
    }

    public void load() {

    }
}


public class CellularNetworkTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String[] cells = scanner.nextLine().split("\\s+");
        String[] cellIds = new String[cells.length];
        int[] capacities = new int[cells.length];
        for (int i = 0; i < cells.length; ++i) {
            String[] parts = cells[i].split(":");
            cellIds[i] = parts[0];
            capacities[i] = Integer.parseInt(parts[1]);
        }
        CellularNetwork cellularNetwork = new CellularNetwork(name, cellIds, capacities);
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----- Making calls -----");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            try {
                cellularNetwork.makeCall(parts[0], parts[1]);
            } catch (CellFullException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
        n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----- Making handovers -----");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            try {
                cellularNetwork.handover(parts[0], parts[1], parts[2]);
            } catch (CellFullException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("===== Find numbers =====");
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String number = scanner.nextLine();
            cellularNetwork.findNumber(number);
        }
        System.out.println("===== Load =====");
        cellularNetwork.load();
        scanner.close();
    }
}

// your code here

