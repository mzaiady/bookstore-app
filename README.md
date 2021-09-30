Assumptions 
----------

- For multiple authors, it will be comma separator text
- No requirements to manage user-basket or order management, Only compute total cost is applied
- Same Promotion code could be applicable/configurable to multiple classification
- if multiple promotion applicable, use single promotion with the maximum value
- Duplication in check-out list, we could have multiple approaches
		
		- Ignored 
		- produce an error
		- A replacement for quantity, duplication of the same book id means requesting another copy of the same book (implemented)

- Assumption about minimum/Max data length
- Assume No pagination is required when listing all books
- Non-Functional Requirements are out-of the scope in current version (e.g. auditing, logging , security, ..) 
  
Test Data
-----------
All data can be viewed on H2 DB @ http://localhost:8080/h2 

- Promotion Code: `PRO-210-000`  10% discount on 'Fiction'
- Promotion Code: `PRO-420-000`  20% discount on 'Science'
- Promotion Code: `000-000-000`  0% discount/no promotion  on 'Comics'

Sample Request Body
---------------


	{"name":"Core Java Volume I--Fundamentals","description":"Core Java, Volume I―Fundamentals, Eleventh Edition, is the definitive guide to writing robust, maintainable code. Whether you’re using Java SE 9, 10, or 11", "author":"Cay S. Horstmann", "isbn":"978-0135166307", "classification":"Technology", "price":"117.24"}


-  Create:  POST http://localhost:8080/api/books 	
-  Read:  GET http://localhost:8080/api/books/{id}	
-  Update:  PUT http://localhost:8080/api/books/{id}	
-  Delete:  DELETE http://localhost:8080/api/books/{id}	

 you can list available books using 
 		
 	GET	http://localhost:8080/api/books 
 		

Build & Running
-----------------
	
	./mvnw spring-boot:run


Build & Running as docker image
-------------------------------

Build image using the following command
	
		docker build -t bs_img .
		
		docker run -p 8080:8080 bs_img 
		

	
	