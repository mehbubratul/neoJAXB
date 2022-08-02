# neoJAXB
Simple project using Spring Boot, Swagger and JAXB.


## [x] Step 1:
Create maven project and add dependencies: [see pom.xml]

## [x] Step 2:
Create DTO : User, Address, Geo, Company
XML Tag Used : @XmlRootElement(), @XmlType(propOrder = {}) @XmlTransient

## [x] Step 3:
  Create simple rest-controller which allows to 
    - call USER_API = "https://jsonplaceholder.typicode.com/users" 
    - set the api values to java object 
    - and save to xml file. 
  Create another simple rest-controller which allows to 
    - go to the xml file location 
    - check the xml files and unmarshalled 
    - and return the data.
  - controller name : UserController
  - methods : For marshalling 
    - @PostMapping("/users/{id}")
      public ResponseEntity<ResponseMessage> saveUserInXML(@PathVariable Integer id)
    - @PostMapping("/users")
      public ResponseEntity<ResponseMessage> saveUsersInXML()
  - methods : For unmarshalling 
    - @GetMapping("/users/{id}")
      public ResponseEntity<ResponseMessage> getUserByIdFromXML(@PathVariable Integer id)
    - @GetMapping("/users")
      public ResponseEntity<ResponseMessage> getUsersFromXML()
  
## [x] Step 4:
Create a service with 4 methods.
  - service name : UserService
  - methods : 
    - saveUsersInXML(User[] userObject)
    - saveUserInXML(User user)
    - getUserByIdFromXML(Integer id)
    - getUsersFromXML()
    
## [ ]Step 5:[TODO]
Add swagger configurations. 
