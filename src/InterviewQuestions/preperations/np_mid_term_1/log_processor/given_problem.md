Потребно е да се развие систем за процесирање на логови.
За секој лог треба да се чува пораката од логот (String), 
типот на логот (String) и временски печат (long). 
За таа цел комплетирајте го интерфејсот ``ILog``.

За да се процесираат логовите ќе се користи генеричкиот интерфејс ``LogProcessor``. 
Овој интерфејс има само еден метод со потпис: ``ArrayList processLogs (ArrayList logs)``. 
Методот добива влезен аргумент логови коишто треба да ги процесира, 
а враќа резултат процесирани логови. Интерфејсот ви е даден и 
за истиот треба да ги пополните само генеричките параметри.

Дадена ви е класата ``LogSystem`` во којашто се чува листа на логови. 
За класата да се дефинираат соодветните генерички параметри, 
да се имплементира конструктор ``LogSystem(ArrayList elements)``, 
како и да се комплетира методот ``printResults()``.

Во овој метод потребно е да креирате три конкретни процесори на логови (со помош на ламбда изрази):

1. Процесор којшто ќе ги врати само логовите коишто се од тип INFO
2. Процесор којшто ќе ги врати само логовите чиишто пораки се пократки од 100 карактери
3. Процесор којшто ќе ги врати логовите сортирани според временскиот печат во растечки редослед (од најстар кон најнов лог).

--

You are asked to develop a system for log processing. 
For each log you need to keep the message from the log (String), 
the type of the log (String) and a timestamp (long). 
For that purpose complete the missing parts of the interface ``ILog``.

The generic interface ``LogProcessor`` should be used for log processing. 
This interface has only one method with signature ``ArrayList processLogs (ArrayList logs)``.
The methods have one argument - the logs that should to be processed 
and the results is a list of processed logs. 
You can find this interface in the starter code, 
and you only need to define the generic parameters for it.

The class ``LogSystem`` is also given in the start code. 
You need to keep a list of logs in this class. 
For the class you need to define the generic parameters, 
to implement the constructor ``LogSystem(ArrayList elements)`` 
and to complete the method ``printResults()``.

In this method you need to create three concrete log processor(with lambda expressions):

1. Log processor that will return only the logs which are from type INFO
2. Log processor that will return only the logs whose messages are with length smaller that 100 characters.
3. Log processor that will return the logs sorted by their timestamp in ascending order.

Start code:
```java

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface ILog {
    //TODO write methods definitions here;
}


interface LogProcessor??? {
        ArrayList??? processLogs(ArrayList??? logs);
        }

class LogSystem??? {
        //TODO add instance variable(s)

        //TODO constructor

        void printResults() {

        //TODO define concrete log processors with lambda expressions
        LogProcessor??? firstLogProcessor = ???

        LogProcessor??? secondLogProcessor = ???

        LogProcessor??? thirdLogProcessor = ???

        System.out.println("RESULTS FROM THE FIRST LOG PROCESSOR");
        firstLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));

        System.out.println("RESULTS FROM THE SECOND LOG PROCESSOR");
        secondLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));

        System.out.println("RESULTS FROM THE THIRD LOG PROCESSOR");
        thirdLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));
        }
        }

class RealLog implements ILog, Comparable<RealLog> {
    String type;
    String message;
    long timestamp;
    static Random rdm = new Random();
    static int index = 100;

    public RealLog(String type, String message, long timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(RealLog o) {
        return Long.compare(this.timestamp, o.timestamp);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }


    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public static RealLog createLog(String line) {
        String[] parts = line.split("\\s+");
        String date = parts[0] + "T" + parts[1];
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ZoneId zoneId = ZoneId.systemDefault();
        long timestamp = ldt.atZone(zoneId).toEpochSecond() * 1000 + index;
        ++index;
        String type = parts[3];
        String message = Arrays.stream(parts).skip(4).collect(Collectors.joining(" "));
        return new RealLog(type, message, timestamp);
    }

    @Override
    public String toString() {
        return String.format("%d [%s] %s", timestamp, type, message);
    }
}

class DummyLog implements ILog, Comparable<DummyLog> {
    String type;
    String message;
    long timestamp;

    public DummyLog(String type, String message, long timestamp) {
        this.type = type;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(DummyLog o) {
        return Long.compare(this.getTimestamp(), o.getTimestamp());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String newMessage) {
        this.message = message;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public static DummyLog createLog(String line) {
        String[] parts = line.split("\\s+");
        return new DummyLog(parts[0], parts[1], Long.parseLong(parts[2]));
    }

    @Override
    public String toString() {
        return "DummyLog{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

public class LoggerTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int firstCount = Integer.parseInt(sc.nextLine());

        ArrayList<RealLog> realLogs = IntStream.range(0, firstCount)
                .mapToObj(i -> RealLog.createLog(sc.nextLine()))
                .collect(Collectors.toCollection(ArrayList::new));

        int secondCount = Integer.parseInt(sc.nextLine());

        ArrayList<DummyLog> dummyLogs = IntStream.range(0, secondCount)
                .mapToObj(i -> DummyLog.createLog(sc.nextLine()))
                .collect(Collectors.toCollection(ArrayList::new));

        LogSystem<RealLog> realLogSystem = new LogSystem<>(realLogs);
        LogSystem<DummyLog> dummyLogSystem = new LogSystem<>(dummyLogs);

        System.out.println("===REAL LOGS SYSTEM RESULTS===");
        realLogSystem.printResults();
        System.out.println("===DUMMY LOGS SYSTEM RESULTS===");
        dummyLogSystem.printResults();

    }
}
```
<b>Test case 1:</b>
#### Input:</br>

```
3
2020-11-18 02:20:20 [dag-scheduler-event-loop] INFO FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
3
ERROR Some_short_message-8 8878539449911250943
ERROR Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64 7026192678309265809
INFO Some_short_message-13 2051624829434088144
```
#### Output:</br>

```
===REAL LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020101 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020102 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
RESULTS FROM THE SECOND LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
RESULTS FROM THE THIRD LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020101 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020102 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
===DUMMY LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
DummyLog{type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
RESULTS FROM THE SECOND LOG PROCESSOR
DummyLog{type='ERROR', message='Some_short_message-8', timestamp=8878539449911250943}
DummyLog{type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
RESULTS FROM THE THIRD LOG PROCESSOR
DummyLog{type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
DummyLog{type='ERROR', message='Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64', timestamp=7026192678309265809}
DummyLog{type='ERROR', message='Some_short_message-8', timestamp=8878539449911250943}
```

<b>Test case 2:</b>
#### Input:</br>
```
10
2020-11-18 02:20:20 [dag-scheduler-event-loop] ERROR FairSchedulableBuilder: A job was submitted with scheduler pool 8528611190673175966, which has not been configured. This can happen when the file that pools are read from isn't set, or when that file doesn't contain 8528611190673175966. Created 8528611190673175966 with default configuration (schedulingMode: FIFO, minShare: 0, weight: 1)
2020-11-18 02:20:20 [dag-scheduler-event-loop] INFO FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 2.0 in stage 550.0 (TID 4053, 10.162.242.30, executor 1, partition 2, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 3.0 in stage 550.0 (TID 4054, 10.162.235.85, executor 0, partition 3, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 4.0 in stage 550.0 (TID 4055, 10.162.242.30, executor 1, partition 4, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 5.0 in stage 550.0 (TID 4056, 10.162.235.85, executor 0, partition 5, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 6.0 in stage 550.0 (TID 4057, 10.162.242.30, executor 1, partition 6, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 7.0 in stage 550.0 (TID 4058, 10.162.235.85, executor 0, partition 7, PROCESS_LOCAL)
10
INFO Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz-66 5714246951254489448
INFO Some_short_message-36 6181675325464793592
ERROR Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzzzzzzzzzzzzz-99 6834476442639500282
INFO Some_looooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-42 1736754739934902764
ERROR Some_short_message-21 4437361120606323169
INFO Some_looooooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64 8116205885959021252
INFO Some_short_message-44 3001989859289700870
ERROR Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-33 1801322652211179902
ERROR Some_short_message-25 4556136726734241343
ERROR Some_looooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-81 1750659258598107124
```
#### Output:</br>
```
===REAL LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
1605666020101 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020102 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020103 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
1605666020104 [INFO] TaskSetManager: Starting task 2.0 in stage 550.0 (TID 4053, 10.162.242.30, executor 1, partition 2, PROCESS_LOCAL)
1605666020105 [INFO] TaskSetManager: Starting task 3.0 in stage 550.0 (TID 4054, 10.162.235.85, executor 0, partition 3, PROCESS_LOCAL)
1605666020106 [INFO] TaskSetManager: Starting task 4.0 in stage 550.0 (TID 4055, 10.162.242.30, executor 1, partition 4, PROCESS_LOCAL)
1605666020107 [INFO] TaskSetManager: Starting task 5.0 in stage 550.0 (TID 4056, 10.162.235.85, executor 0, partition 5, PROCESS_LOCAL)
1605666020108 [INFO] TaskSetManager: Starting task 6.0 in stage 550.0 (TID 4057, 10.162.242.30, executor 1, partition 6, PROCESS_LOCAL)
1605666020109 [INFO] TaskSetManager: Starting task 7.0 in stage 550.0 (TID 4058, 10.162.235.85, executor 0, partition 7, PROCESS_LOCAL)
RESULTS FROM THE SECOND LOG PROCESSOR
1605666020101 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
RESULTS FROM THE THIRD LOG PROCESSOR
1605666020100 [ERROR] FairSchedulableBuilder: A job was submitted with scheduler pool 8528611190673175966, which has not been configured. This can happen when the file that pools are read from isn't set, or when that file doesn't contain 8528611190673175966. Created 8528611190673175966 with default configuration (schedulingMode: FIFO, minShare: 0, weight: 1)
1605666020101 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020102 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020103 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
1605666020104 [INFO] TaskSetManager: Starting task 2.0 in stage 550.0 (TID 4053, 10.162.242.30, executor 1, partition 2, PROCESS_LOCAL)
1605666020105 [INFO] TaskSetManager: Starting task 3.0 in stage 550.0 (TID 4054, 10.162.235.85, executor 0, partition 3, PROCESS_LOCAL)
1605666020106 [INFO] TaskSetManager: Starting task 4.0 in stage 550.0 (TID 4055, 10.162.242.30, executor 1, partition 4, PROCESS_LOCAL)
1605666020107 [INFO] TaskSetManager: Starting task 5.0 in stage 550.0 (TID 4056, 10.162.235.85, executor 0, partition 5, PROCESS_LOCAL)
1605666020108 [INFO] TaskSetManager: Starting task 6.0 in stage 550.0 (TID 4057, 10.162.242.30, executor 1, partition 6, PROCESS_LOCAL)
1605666020109 [INFO] TaskSetManager: Starting task 7.0 in stage 550.0 (TID 4058, 10.162.235.85, executor 0, partition 7, PROCESS_LOCAL)
===DUMMY LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
DummyLog{type='INFO', message='Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz-66', timestamp=5714246951254489448}
DummyLog{type='INFO', message='Some_short_message-36', timestamp=6181675325464793592}
DummyLog{type='INFO', message='Some_looooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-42', timestamp=1736754739934902764}
DummyLog{type='INFO', message='Some_looooooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64', timestamp=8116205885959021252}
DummyLog{type='INFO', message='Some_short_message-44', timestamp=3001989859289700870}
RESULTS FROM THE SECOND LOG PROCESSOR
DummyLog{type='INFO', message='Some_short_message-36', timestamp=6181675325464793592}
DummyLog{type='ERROR', message='Some_short_message-21', timestamp=4437361120606323169}
DummyLog{type='INFO', message='Some_short_message-44', timestamp=3001989859289700870}
DummyLog{type='ERROR', message='Some_short_message-25', timestamp=4556136726734241343}
RESULTS FROM THE THIRD LOG PROCESSOR
DummyLog{type='INFO', message='Some_looooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-42', timestamp=1736754739934902764}
DummyLog{type='ERROR', message='Some_looooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-81', timestamp=1750659258598107124}
DummyLog{type='ERROR', message='Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-33', timestamp=1801322652211179902}
DummyLog{type='INFO', message='Some_short_message-44', timestamp=3001989859289700870}
DummyLog{type='ERROR', message='Some_short_message-21', timestamp=4437361120606323169}
DummyLog{type='ERROR', message='Some_short_message-25', timestamp=4556136726734241343}
DummyLog{type='INFO', message='Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz-66', timestamp=5714246951254489448}
DummyLog{type='INFO', message='Some_short_message-36', timestamp=6181675325464793592}
DummyLog{type='ERROR', message='Some_looooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzzzzzzzzzzzzz-99', timestamp=6834476442639500282}
DummyLog{type='INFO', message='Some_looooooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64', timestamp=8116205885959021252}
```
