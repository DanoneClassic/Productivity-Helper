# Productivity Helper

## 1. Název projektu
Productivity Helper

## 2. Popis
Aplikace pro Zvyšování Produktivity má za cíl sloužit jako komplexní nástroj pro zvyšování produktivity tím, že nabízí různorodé strategie a techniky. Jejím hlavním cílem je poskytnout uživatelům, včetně těch, kteří jsou neurodivergentní, robustní zdroj pro optimalizaci jejich efektivity a účinnosti při různých úkolech a činnostech.

## 3. Obsah
1. [Název projektu](#název-projektu)
2. [Popis](#popis)
3. [Obsah](#obsah)
4. [Technologie](#technologie)
5. [Instalace a nastavení](#instalace-a-nastavení)
6. [Použití](#použití)
7. [Architektura](#architektura)
8. [Mikroslužby](#mikroslužby)
9. [Databáze](#databáze)
10. [Zabezpečení](#zabezpečení)
11. [Systém výměny zpráv](#systém-výměny-zpráv)
12. [Shrnutí použitých technologií a principů](#shrnutí-použitých-technologií-a-principů)

## 4. Technologie
- **Backend**: Java s frameworkem Spring Boot
- **Frontend**: React
- **Databáze**: PostgreSQL
- **Messaging systém**: Apache Kafka, WebClient (REST)
- **Zabezpečení**: JSON Web Tokens (JWT)
- **Komunikační technologie**: REST, WebClient

## 5. Instalace a nastavení
1. Ujistěte se, že máte nainstalované následující nástroje:
   - Maven
   - Docker
   - Docker Compose
2. Klonujte tento repozitář:
   ```sh
   git clone https://gitlab.fel.cvut.cz/fomenart/nss.git
   ```
3. Přejděte do adresáře projektu:
   ```sh
   cd /nss
   ```
4. Sestavte a spusťte aplikaci pomocí Docker Compose:
   ```sh
   docker-compose up --build
   ```

## 6. Použití
Pro použití aplikace přejděte na `http://localhost:3000` ve vašem webovém prohlížeči. Přihlaste se nebo zaregistrujte nový účet a následujte pokyny na obrazovce pro používání aplikace.

## 7. Architektura
Aplikace je navržena jako mikroservisní architektura s API Gateway. Použité design patterns zahrnují:
- **State**: Implementováno v [UserService](./UserService)
- **Facade**: Implementováno ve [StatisticsService](./StatisticsService)
- **Command**: Implementováno v [WorkSpaceService](./WorkSpaceService)
- **Strategy**: Implementováno v [QAService](./QAService)
- **Observer**: Implementováno v [EmailNotificationService](./EmailNotificationService) s použitím Kafka

## 8. Mikroslužby
Projekt obsahuje následující mikroslužby:
- **AuthorizationService**: Autentizace a autorizace uživatelů.
- **EmailNotificationService**: Odesílání e-mailových notifikací.
- **Gateway**: Vstupní bod pro všechny požadavky do aplikace, routuje požadavky k příslušným službám.
- **QAService**: Obsahuje otázky a odpovědi pro uživatele.
- **StatisticsService**: Zpracovává statistiky uživatelů.
- **UserService**: Spravuje uživatele.
- **WorkSpaceService**: Pracovní prostředí uživatele.

## 9. Databáze
Aplikace používá PostgreSQL jako databázový systém.

## 10. Zabezpečení
Aplikace používá JSON Web Tokens (JWT) pro zabezpečení uživatelských dat a autentizaci.

## 11. Systém výměny zpráv
Pro asynchronní komunikaci mezi mikroservisy aplikace používá Apache Kafka a WebClient (REST).
