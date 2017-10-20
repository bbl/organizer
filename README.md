### Organizer

---
MySql setup:  
 - Create `organizer` db.
 - `src/main/resources/application.properties` assumes mysql user is **root** without password

---

Todo:
* Create Calendar entity class
* Create CRUD calls for Calendar 
* Create Event entity class
* Create CRUD calls for Event
* Add views

---

Build:

```bash
mvn clean install
```  
 
Run:  

```bash
java -jar target/organizer-0.1.0.jar
```


Useful links:
   - Basic tutorial: https://spring.io/guides/gs/serving-web-content/    
   - SpringBoot + MySQL guide: https://spring.io/guides/gs/accessing-data-mysql/



