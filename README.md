# Meta
Веб-приложение, написанное на java (spring), на основе микросервисной архитектуре (Патерн - Агрегатор)  
Внешний интерфейс представлен в виде REST API  
Подключен Eureka Server (discovery-server)

![Image](UML.png)  
(API Rate и Gif - сторонние сервисы: https://docs.openexchangerates.org/, https://developers.giphy.com/)  

Сервис meta позволяет работать с двумя сервисами (обращается к сервисам через RestTemplate):  
1) service-storage-socks - предоставляет возможность автоматизации учёта носков на складе магазина. Имеет такие функции:  
* добавить носки на склад (сохраняет в БД)
* удалить носки со склада (удаляет из БД) 
* узнать общее количество носков определенного цвета и состава в данный момент времени  

2) service-gif - позволяет получить gif-изображение определённого типа, работает по таким правилам:  
   1)Обращается к сервису курса валют, получает курс рубля к доллару за текущий и прошлый день (обращается к сервисам через feign)   
   2)В зависимости от разницы (>= 0 || < 0) обращается к сервису gif-изображений и возвращает gif нужного нам типа (rich || broke)  

## Техническая составляющая
### 1) meta  
### Backend  

* JDK 11

* Spring framework
  * Boot
  * Web
  * Cloud
* Maven
* Hystrix
   * Dashboard 
* Swagger  
 

### 2) service-storage-socks
### Backend

* JDK 11
* Spring framework
  * Boot
  * Web
  * Cloud
  * Data
* Maven
* SQL
  * MySQL
* Миграция БД - flyway  


### 3) service-gif
### Backend

* JDK 11
* Spring framework
  * Boot
  * Web
  * Cloud
* Gradle
* Feign

### Тестирование
* JUnit
* Mockito

### 4) discovery-server = EurekaServer
### Backend
* JDK 11
* Spring framework
  * Boot
  * Cloud
* Maven
