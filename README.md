# know-me 24.03.2024 
- Implementacja lepszego systemu kolejki

# know-me 20.11.2023 0.0.5
- Usunięcie komponentu odpowiedzialnego za upload zdjęć, funkcjonalność dorzucona do głównej aplikacji
- Naprawienie funkcjonalności odpowiedzialnej za upload i wyświetlanie zdjęć.
- Walidacja pól 
- Wyświetlanie informacji na temat ilości osób w pokoju(Arenie)
- Inicjalizacja relacyjnej bazy danych postgreSQL dla komponentu know-me-profile
- docker-compose
- aktualizacja komponentu kafka-centrala, logi o sesjach
- init groovy libs dla testów jednostkowych w komponencie know-me-profile

# know-me Stan na 14.11.2023 0.0.4
- Inicjalizacja apache kafka/Moduł do przekazywania logów
- Przygotowania do procesu CI/CD -> jenkins na AWS
- Poprawki dla sesji oraz walidacji pól w aplikacji klienckiej. 

# know-me Stan na 31.10.2023 0.0.3
- Integracja kolejki/sesji oraz live-chatu
- Stworzenie współdzielonego modułu z dtosami

# know-me Stan na 25.10.2023 0.0.2
- Przejscie z monolitu na architekturę serwisową
- Integracja głównego modułu backend oraz klienta z REST Api Know-Me-ProfileService 
- Stworzenie usług SOAP dla know-me-uploader

# know-me Stan na 20.10.2023 0.0.1
Aplikacja dostarcza rozwiązania umożliwiające poznawanie nowych ludzi.
Strukturę warstwy serwerowej można podzielić na trzy filary:
- Użytkownik
- Klucz
- Arena

Głównym założeniem podczas tworzenia aplikacji było ograniczenie przechowywania danych personalnych.
Aby rozpocząc korzystanie z know-me, wystarczy wygenerować cryptoId, za jego pomocą logujemy sie do systemu.
Panel użytkownika składa się z 4 pól informacyjnych, które można zaktualizować:
- Zdjęcie
- Imię/Nazwa
- Płeć
- Opis

Konta użytkowników czyszczone są co 24h.

Klucze, pozwalają na stworzenie przez użytkownika pokoju, w którym gromadzić się mogą użytkownicy o wspólnych zainteresowaniach
Np. Łucznictwo. Po utworzeniu klucza, oraz dołączeniu do niego użytkowników, zachodzi relacja
nazwana Areną, po dołączeniu do kolejki, następuje losowanie par, które zostaną przeniesione na kanał, na którym będą mogli się poznać.

Aplikacja działa, ale dostarczone rozwiązania musza ulec znacznej optymalizacji, brakuje testów i walidacji pól.
W przyszłości projekt zostanie podzielony na mikroserwisy.

