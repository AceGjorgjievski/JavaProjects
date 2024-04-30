Да се напише класа за книга <b>Book</b> во која се чува:

- наслов
- категорија
- цена.

Да се имплементира конструктор со следните аргументи `Book(String title, String category, float price)`.

Потоа да се напише класа <b>BookCollection</b> во која се чува колекција од книги. 
Во оваа класа треба да се имплментираат следните методи:

- `public void addBook(Book book)` - додавање книга во колекцијата
- `public void printByCategory(String category)` - ги печати сите книги од проследената категорија (се споредува стрингот без разлика на мали и големи букви), сортирани според насловот на книгата (ако насловот е ист, се сортираат според цената).
- `public List<Book> getCheapestN(int n)` - враќа листа на најевтините N книги (ако има помалку од N книги во колекцијата, ги враќа сите).

Start code:
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BooksTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		BookCollection booksCollection = new BookCollection();
		Set<String> categories = fillCollection(scanner, booksCollection);
		System.out.println("=== PRINT BY CATEGORY ===");
		for (String category : categories) {
			System.out.println("CATEGORY: " + category);
			booksCollection.printByCategory(category);
		}
		System.out.println("=== TOP N BY PRICE ===");
		print(booksCollection.getCheapestN(n));
	}

	static void print(List<Book> books) {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	static TreeSet<String> fillCollection(Scanner scanner,
			BookCollection collection) {
		TreeSet<String> categories = new TreeSet<String>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(":");
			Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
			collection.addBook(book);
			categories.add(parts[1]);
		}
		return categories;
	}
}

//Your code here
```
<b>Test case 1:</b>
#### Input:</br>
```
11
1984:A:11.3
A Brief History of Time:A:25.66
A Heartbreaking Work of Staggering Genius:A:21.87
A Long Way Gone Memoirs of a Boy Soldier:B:15.18
The Bad Beginning Or, Orphans!:A:12.87
A Wrinkle in Time:B:14.34
Selected Stories, 1968-1994:B:2.54
Alice's Adventures in Wonderland:C:12.34
Angela's Ashes A Memoir:C:18.19
Laugh-Out-Loud Jokes for Kids:C:9.99
Yes Please:C:11.50
```
#### Output:</br>

```
=== PRINT BY CATEGORY ===
CATEGORY: A
1984 (A) 11.30
A Brief History of Time (A) 25.66
A Heartbreaking Work of Staggering Genius (A) 21.87
The Bad Beginning Or, Orphans! (A) 12.87
CATEGORY: B
A Long Way Gone Memoirs of a Boy Soldier (B) 15.18
A Wrinkle in Time (B) 14.34
Selected Stories, 1968-1994 (B) 2.54
CATEGORY: C
Alice's Adventures in Wonderland (C) 12.34
Angela's Ashes A Memoir (C) 18.19
Laugh-Out-Loud Jokes for Kids (C) 9.99
Yes Please (C) 11.50
=== TOP N BY PRICE ===
Selected Stories, 1968-1994 (B) 2.54
Laugh-Out-Loud Jokes for Kids (C) 9.99
1984 (A) 11.30
Yes Please (C) 11.50
Alice's Adventures in Wonderland (C) 12.34
The Bad Beginning Or, Orphans! (A) 12.87
A Wrinkle in Time (B) 14.34
A Long Way Gone Memoirs of a Boy Soldier (B) 15.18
Angela's Ashes A Memoir (C) 18.19
A Heartbreaking Work of Staggering Genius (A) 21.87
A Brief History of Time (A) 25.66
```
<b>Test case 2:</b>
#### Input:</br>
```
11
1984:Classics:11.86
A Brief History of Time:Cosmology:21.66
A Heartbreaking Work of Staggering Genius:Biographies:22.87
A Long Way Gone Memoirs of a Boy Soldier:Biographies:15.18
The Bad Beginning Or, Orphans!:Cosmology:10.87
A Wrinkle in Time:Classics:14.34
Selected Stories, 1968-1994:Children:2.54
Alice's Adventures in Wonderland:Children:12.34
Angela's Ashes A Memoir:Biographies:14.19
Laugh-Out-Loud Jokes for Kids:Children:2.99
Yes Please:Biographies:14.50
```
#### Output:</br>
```
=== PRINT BY CATEGORY ===
CATEGORY: Biographies
A Heartbreaking Work of Staggering Genius (Biographies) 22.87
A Long Way Gone Memoirs of a Boy Soldier (Biographies) 15.18
Angela's Ashes A Memoir (Biographies) 14.19
Yes Please (Biographies) 14.50
CATEGORY: Children
Alice's Adventures in Wonderland (Children) 12.34
Laugh-Out-Loud Jokes for Kids (Children) 2.99
Selected Stories, 1968-1994 (Children) 2.54
CATEGORY: Classics
1984 (Classics) 11.86
A Wrinkle in Time (Classics) 14.34
CATEGORY: Cosmology
A Brief History of Time (Cosmology) 21.66
The Bad Beginning Or, Orphans! (Cosmology) 10.87
=== TOP N BY PRICE ===
Selected Stories, 1968-1994 (Children) 2.54
Laugh-Out-Loud Jokes for Kids (Children) 2.99
The Bad Beginning Or, Orphans! (Cosmology) 10.87
1984 (Classics) 11.86
Alice's Adventures in Wonderland (Children) 12.34
Angela's Ashes A Memoir (Biographies) 14.19
A Wrinkle in Time (Classics) 14.34
Yes Please (Biographies) 14.50
A Long Way Gone Memoirs of a Boy Soldier (Biographies) 15.18
A Brief History of Time (Cosmology) 21.66
A Heartbreaking Work of Staggering Genius (Biographies) 22.87
```
<b>Test case 3:</b>
#### Input:</br>
```
50
Book Z:A:43.11
Book L:C:16.17
Book Q:D:32.51
Book O:D:46.15
Book C:C:40.02
Book W:D:16.20
Book G:D:41.55
Book P:E:31.54
Book X:C:27.09
Book I:D:23.25
Book Q:E:19.69
Book D:D:9.49
Book G:D:6.94
Book J:E:11.16
Book O:C:31.67
Book G:E:6.17
Book O:C:6.53
Book D:C:27.77
Book E:B:35.25
Book X:E:0.81
Book F:E:16.55
Book G:D:38.97
Book V:D:33.62
Book T:D:8.07
Book M:D:26.04
Book W:A:49.79
Book K:E:48.91
Book M:A:0.90
Book S:B:28.95
Book M:C:42.30
Book J:E:31.29
Book M:E:9.83
Book V:E:19.67
Book W:C:49.28
Book U:C:49.39
Book E:D:22.38
Book K:E:37.06
Book P:B:47.52
Book X:D:34.77
Book C:B:48.91
Book C:E:22.85
Book V:B:45.90
Book V:D:7.45
Book L:C:35.15
Book P:C:35.49
Book F:C:49.07
Book V:A:22.40
Book V:E:17.20
Book U:E:37.56
Book N:D:31.67
```
#### Output:</br>
```
=== PRINT BY CATEGORY ===
CATEGORY: A
Book M (A) 0.90
Book V (A) 22.40
Book W (A) 49.79
Book Z (A) 43.11
CATEGORY: B
Book C (B) 48.91
Book E (B) 35.25
Book P (B) 47.52
Book S (B) 28.95
Book V (B) 45.90
CATEGORY: C
Book C (C) 40.02
Book D (C) 27.77
Book F (C) 49.07
Book L (C) 16.17
Book L (C) 35.15
Book M (C) 42.30
Book O (C) 6.53
Book O (C) 31.67
Book P (C) 35.49
Book U (C) 49.39
Book W (C) 49.28
Book X (C) 27.09
CATEGORY: D
Book D (D) 9.49
Book E (D) 22.38
Book G (D) 6.94
Book G (D) 38.97
Book G (D) 41.55
Book I (D) 23.25
Book M (D) 26.04
Book N (D) 31.67
Book O (D) 46.15
Book Q (D) 32.51
Book T (D) 8.07
Book V (D) 7.45
Book V (D) 33.62
Book W (D) 16.20
Book X (D) 34.77
CATEGORY: E
Book C (E) 22.85
Book F (E) 16.55
Book G (E) 6.17
Book J (E) 11.16
Book J (E) 31.29
Book K (E) 37.06
Book K (E) 48.91
Book M (E) 9.83
Book P (E) 31.54
Book Q (E) 19.69
Book U (E) 37.56
Book V (E) 17.20
Book V (E) 19.67
Book X (E) 0.81
=== TOP N BY PRICE ===
Book X (E) 0.81
Book M (A) 0.90
Book G (E) 6.17
Book O (C) 6.53
Book G (D) 6.94
Book V (D) 7.45
Book T (D) 8.07
Book D (D) 9.49
Book M (E) 9.83
Book J (E) 11.16
Book L (C) 16.17
Book W (D) 16.20
Book F (E) 16.55
Book V (E) 17.20
Book V (E) 19.67
Book Q (E) 19.69
Book E (D) 22.38
Book V (A) 22.40
Book C (E) 22.85
Book I (D) 23.25
Book M (D) 26.04
Book X (C) 27.09
Book D (C) 27.77
Book S (B) 28.95
Book J (E) 31.29
Book P (E) 31.54
Book N (D) 31.67
Book O (C) 31.67
Book Q (D) 32.51
Book V (D) 33.62
Book X (D) 34.77
Book L (C) 35.15
Book E (B) 35.25
Book P (C) 35.49
Book K (E) 37.06
Book U (E) 37.56
Book G (D) 38.97
Book C (C) 40.02
Book G (D) 41.55
Book M (C) 42.30
Book Z (A) 43.11
Book V (B) 45.90
Book O (D) 46.15
Book P (B) 47.52
Book C (B) 48.91
Book K (E) 48.91
Book F (C) 49.07
Book W (C) 49.28
Book U (C) 49.39
Book W (A) 49.79
```
<b>Test case 4:</b>
#### Input:</br>
```
100
Book U:A:30.40
Book O:C:11.18
Book T:A:23.26
Book R:A:5.13
Book T:E:25.12
Book E:E:39.29
Book K:E:17.52
Book E:B:10.62
Book D:A:20.96
Book B:E:45.33
Book S:B:43.51
Book R:A:18.39
Book O:E:21.83
Book S:A:17.23
Book Y:D:5.00
Book W:A:45.44
Book Q:B:26.62
Book M:B:22.63
Book X:B:7.79
Book O:B:12.18
Book P:D:15.55
Book U:D:31.86
Book D:E:30.46
Book I:B:17.83
Book H:D:48.87
Book D:A:6.00
Book D:B:22.06
Book T:B:4.42
Book V:C:6.33
Book Y:C:25.09
Book H:B:17.23
Book M:C:26.92
Book Q:B:20.58
Book T:B:24.48
Book B:C:6.89
Book Q:C:33.58
Book C:E:20.70
Book A:C:35.40
Book G:D:11.66
Book V:E:44.56
Book L:A:29.81
Book L:E:27.10
Book W:B:22.22
Book B:C:29.28
Book Q:E:17.06
Book T:C:31.41
Book B:D:35.47
Book I:A:13.42
Book A:A:29.41
Book X:A:38.61
Book S:C:41.50
Book D:B:3.31
Book K:E:47.46
Book C:E:38.98
Book Q:B:46.65
Book Q:C:46.54
Book G:C:40.18
Book G:E:9.69
Book U:A:27.13
Book T:D:11.81
Book I:B:34.51
Book O:E:2.52
Book E:D:33.08
Book U:E:31.17
Book D:E:6.94
Book Z:C:17.41
Book D:C:10.20
Book W:B:45.58
Book Z:B:20.60
Book T:B:3.04
Book M:C:33.49
Book G:D:7.74
Book X:D:46.09
Book F:A:32.31
Book Q:C:12.86
Book U:B:12.38
Book C:A:47.42
Book J:D:17.17
Book F:B:19.63
Book O:A:12.39
Book F:A:31.51
Book A:C:9.59
Book U:E:36.68
Book S:B:24.38
Book A:B:0.79
Book I:A:21.25
Book T:D:4.46
Book Y:A:41.70
Book C:D:31.39
Book O:B:10.19
Book F:A:37.17
Book V:E:4.20
Book U:E:25.10
Book S:B:30.16
Book B:B:48.85
Book S:B:19.74
Book P:A:3.74
Book I:E:11.36
Book P:A:33.72
Book V:D:30.24
```
#### Output:</br>
```
=== PRINT BY CATEGORY ===
CATEGORY: A
Book A (A) 29.41
Book C (A) 47.42
Book D (A) 6.00
Book D (A) 20.96
Book F (A) 31.51
Book F (A) 32.31
Book F (A) 37.17
Book I (A) 13.42
Book I (A) 21.25
Book L (A) 29.81
Book O (A) 12.39
Book P (A) 3.74
Book P (A) 33.72
Book R (A) 5.13
Book R (A) 18.39
Book S (A) 17.23
Book T (A) 23.26
Book U (A) 27.13
Book U (A) 30.40
Book W (A) 45.44
Book X (A) 38.61
Book Y (A) 41.70
CATEGORY: B
Book A (B) 0.79
Book B (B) 48.85
Book D (B) 3.31
Book D (B) 22.06
Book E (B) 10.62
Book F (B) 19.63
Book H (B) 17.23
Book I (B) 17.83
Book I (B) 34.51
Book M (B) 22.63
Book O (B) 10.19
Book O (B) 12.18
Book Q (B) 20.58
Book Q (B) 26.62
Book Q (B) 46.65
Book S (B) 19.74
Book S (B) 24.38
Book S (B) 30.16
Book S (B) 43.51
Book T (B) 3.04
Book T (B) 4.42
Book T (B) 24.48
Book U (B) 12.38
Book W (B) 22.22
Book W (B) 45.58
Book X (B) 7.79
Book Z (B) 20.60
CATEGORY: C
Book A (C) 9.59
Book A (C) 35.40
Book B (C) 6.89
Book B (C) 29.28
Book D (C) 10.20
Book G (C) 40.18
Book M (C) 26.92
Book M (C) 33.49
Book O (C) 11.18
Book Q (C) 12.86
Book Q (C) 33.58
Book Q (C) 46.54
Book S (C) 41.50
Book T (C) 31.41
Book V (C) 6.33
Book Y (C) 25.09
Book Z (C) 17.41
CATEGORY: D
Book B (D) 35.47
Book C (D) 31.39
Book E (D) 33.08
Book G (D) 7.74
Book G (D) 11.66
Book H (D) 48.87
Book J (D) 17.17
Book P (D) 15.55
Book T (D) 4.46
Book T (D) 11.81
Book U (D) 31.86
Book V (D) 30.24
Book X (D) 46.09
Book Y (D) 5.00
CATEGORY: E
Book B (E) 45.33
Book C (E) 20.70
Book C (E) 38.98
Book D (E) 6.94
Book D (E) 30.46
Book E (E) 39.29
Book G (E) 9.69
Book I (E) 11.36
Book K (E) 17.52
Book K (E) 47.46
Book L (E) 27.10
Book O (E) 2.52
Book O (E) 21.83
Book Q (E) 17.06
Book T (E) 25.12
Book U (E) 25.10
Book U (E) 31.17
Book U (E) 36.68
Book V (E) 4.20
Book V (E) 44.56
=== TOP N BY PRICE ===
Book A (B) 0.79
Book O (E) 2.52
Book T (B) 3.04
Book D (B) 3.31
Book P (A) 3.74
Book V (E) 4.20
Book T (B) 4.42
Book T (D) 4.46
Book Y (D) 5.00
Book R (A) 5.13
Book D (A) 6.00
Book V (C) 6.33
Book B (C) 6.89
Book D (E) 6.94
Book G (D) 7.74
Book X (B) 7.79
Book A (C) 9.59
Book G (E) 9.69
Book O (B) 10.19
Book D (C) 10.20
Book E (B) 10.62
Book O (C) 11.18
Book I (E) 11.36
Book G (D) 11.66
Book T (D) 11.81
Book O (B) 12.18
Book U (B) 12.38
Book O (A) 12.39
Book Q (C) 12.86
Book I (A) 13.42
Book P (D) 15.55
Book Q (E) 17.06
Book J (D) 17.17
Book H (B) 17.23
Book S (A) 17.23
Book Z (C) 17.41
Book K (E) 17.52
Book I (B) 17.83
Book R (A) 18.39
Book F (B) 19.63
Book S (B) 19.74
Book Q (B) 20.58
Book Z (B) 20.60
Book C (E) 20.70
Book D (A) 20.96
Book I (A) 21.25
Book O (E) 21.83
Book D (B) 22.06
Book W (B) 22.22
Book M (B) 22.63
Book T (A) 23.26
Book S (B) 24.38
Book T (B) 24.48
Book Y (C) 25.09
Book U (E) 25.10
Book T (E) 25.12
Book Q (B) 26.62
Book M (C) 26.92
Book L (E) 27.10
Book U (A) 27.13
Book B (C) 29.28
Book A (A) 29.41
Book L (A) 29.81
Book S (B) 30.16
Book V (D) 30.24
Book U (A) 30.40
Book D (E) 30.46
Book U (E) 31.17
Book C (D) 31.39
Book T (C) 31.41
Book F (A) 31.51
Book U (D) 31.86
Book F (A) 32.31
Book E (D) 33.08
Book M (C) 33.49
Book Q (C) 33.58
Book P (A) 33.72
Book I (B) 34.51
Book A (C) 35.40
Book B (D) 35.47
Book U (E) 36.68
Book F (A) 37.17
Book X (A) 38.61
Book C (E) 38.98
Book E (E) 39.29
Book G (C) 40.18
Book S (C) 41.50
Book Y (A) 41.70
Book S (B) 43.51
Book V (E) 44.56
Book B (E) 45.33
Book W (A) 45.44
Book W (B) 45.58
Book X (D) 46.09
Book Q (C) 46.54
Book Q (B) 46.65
Book C (A) 47.42
Book K (E) 47.46
Book B (B) 48.85
Book H (D) 48.87
```
