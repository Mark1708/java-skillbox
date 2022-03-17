## Создаём коллекцию
```db.createCollection("books")```
## Вставляем данные
```db.books.insert({name: "Anna Karenina", author: "Leo Tolstoy", year: 1880})```
## Либо можем вставить несколько объектов сразу
```
db.books.insertMany([
	{name: "Anna Karenina", author: "Leo Tolstoy", year: 1880},
	{name: "War and Peace", author: "Leo Tolstoy", year: 1885},
	{name: "The Stories of Anton Chekhov", author: "Anton Chekhov", year: 1960},
	{name: "The Great Gatsby", author: "F. Scott Fitzgerald", year: 1990},
	{name: "Madame Bovary", author: "Gustave Flaubert", year: 1890}
])
```
## Выведем содержимое коллекции и убедимся, что сохранились все книги
```db.books.find().pretty()```
## Поиск самой старой книги
```db.books.find().sort({ "year" : 1 }).limit(1).pretty()```
## Поиск по автору
```db.books.find({author: "Leo Tolstoy"}).pretty()```
