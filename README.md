Wewnąrz projektu znajduje się aplikacja backendowa oraz frontendowa (w folderze webapp)

Użyłem:
bazy danych Postgres
Spring boot w wersji 3.2.2
Angulara w wersji 16.2

Aby odpalić aplikację nalezy pobrać repozytorium przeładować plik POM z zależnościami mavena, stworzyć baze danych w postgresie o nazwie "xcode", w folderze webapp z aplikacją frontendową wykonać npm install.

W aplikacji backendowej stworzyłem folder currencies w którym znajduję się aplikacja.
znajdują się tam następujące foldery:
web - kontroler
service -  serwis
repository - repozytorium
model - model o nazwie "history", jest to klasa która reprezentuje dane z bazy danych
dto - pliki dto które służą do pokazywania oraz otrzymywania danych
assembler - pliki w których są mappery z encji na dto oraz z dto na encje
config - plik z filtrem CORS ponieważ miałem błąd CORS musiałem założyć taki filtr żeby aplikacja działała poprawnie.

