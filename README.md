# Healper-BackEnd

[toc]

## System Functionalities

We have achieved system functionalities as below:

- Login Service
  - Login & Logout(With sa-token)
  - Register
  - SMS verify code
- Personal Information Service
  - User Information
  - Consultant Information
- Psychological Scale Self-test Service
  - View psychological scale tests
  - Do the psychological scale tests for self-assessment
  - View the psychological scale test records
- Statistics Service
  - Statistical analysis of the results of the measurement scale
- Psychological Consulting Service
  - View consultants information
  - Make an appointment
  - Consulting in the online chat room
- Order Service
  - Create/Cancel an order
  - Scan QR code for payment and update order status
  - Change order status after start or end consulting
  - View order history
- Psychological Files Management Service
  - Consultants write psychological files for users
  - Users view psychological files

## User Manual

### Login & Register

The platform is only available for users who have logged in, so that a user who hasn't logged in will be direct to the login page first. If the user has got an account, he can use his phone number and password to log in, otherwise, registering an account is needed. The user can use a nickname, telephone number, password, age and sex to sign up. In the process of registration, a verification code is needed, which will be sent to the user's phone.

(pics of login page & register page)

### Home Page

Users can quickly enter the consultation room and start the consultation with the consultant appointed in advance. Recommended Consultants and Scales will be shown on the home page.

![image-20230105172536972](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172536972.png)

### Psychometric Assessment

Users can choose a scale from the **Scale Select** page. All the available scales will be shown with scale name, brief introduction and the number of questions. After finishing a scale, users will get a result which includes the values of psychological factors. The result can be used in the process of consultation.

![image-20230105172541626](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172541626.png)

![image-20230105172548120](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172548120.png)

![image-20230105172552409](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172552409.png)

### Consultation

If the user has already made an appointment with a consultant and has paid the fee, he can enter the consultation room and chat with the consultant. After the consultation, a document about the consultation will be written by the consultant. The user can check it on the **Consultation Document** page.

![image-20230105172557479](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172557479.png)

If not, the user needs to go to the **Appointment** page to make an appointment with a consultant. The information of the consultants will be shown, such as name, sex, age, consultation fee and labels which indicate the expertise of the consultant. After choosing a consultant, the user needs to go to the **My Order** page to pay the consultation fee.

![image-20230105172607517](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172607517.png)

The user can view all the history orders on the **My Order** page. For orders that

- Have not been paid

The user can cancel the appointment or pay the fee.

- Have not been started

The user can enter the consultation room.

- Have been started

The user can enter the consultation room or end the consultation.

- Have been finished

The user can re-enter the consultation room to check the chat logs.

![image-20230105172614755](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172614755.png)

### My Information

Users can check and modify the information about themselves, including avatar, nickname and so on. Recent consultation documents and assessment records will be listed on the home page of **My Information**. 

![image-20230105172618523](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172618523.png)

All history assessment records will be shown on the **Assessment Records** page. On this page, a line chart of the changes in the nine psychological factors over time will be displayed. Also, the user can view the results of specific assessment records, or delete them.

![image-20230105172622412](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172622412.png)

![image-20230105172626003](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172626003.png)

All history consultation documents will be shown on the **Consultation Document** page. On this page, the user can view the advice from their consultants.

![image-20230105172629792](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172629792.png)

![image-20230105172633840](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172633840.png)

### For Consultants

The consultants need to login to the platform using designated accounts. Consultants can check their orders on the **Consultation** page. For the orders that

- Have not been paid

Consultants can cancel the order.

- Have not been started

Consultants can start the consultation after confirming that they have received the consultation fee. Consultants can also enter the consultation room to get acquaintances of the visitors in advance.

- Have been started

Consultants can enter the consultation room and chat with the visitor.

- Have been finished

Consultants can re-enter the consultation room to check the chat log and write the consultation document.

![image-20230105172637697](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172637697.png)

Consultants can modify their information on the **My Information** page. They can upload their QR codes for payment receipt, modify the consultation fee and add or delete the labels about their speciality.

![image-20230105172641506](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172641506.png)

## System Architecture and Component Design

### System Architecture

- Design **Entity layer** to map the tables and columns of database, so as to achieve faster CURD.
- Design **Repository Layer** to access the database
  - Inherit some jpa interfaces to implement complex logic queries
- Design **Service Layer** to realize the specific implementation of business logic
  - First, define the interface of the service layer.
  - Then, realize the concrete implementation.
- Design **Controller Layer** to handle URL requests and connect the View layer to the Service layer
  - Handle URL requests from the front end.
  - Invokes the interface of the service layer to run specific business logic.
- Design **View Layer** to present the page to the user
- Set **Configuration Layer** for project configuration

### Component Design

- Entity

In Entity, we map tables and columns from the database for use in building the Repository. The entity is generated by tools and then modified by ourselves.

- Repository

In the Repository layer, we access the database by JPA. We use interfaces provided by JPA such as `JpaRepository` and `JpaSpecificationExecutor`. JPA provides a number of generated interfaces to CURD, which simplify our code a lot.

- Service

The specific business logic code of the five subsystems. Each service contains several repository objects, which are used to CURD the database. Even if we try hard to divide the system into independent services, there will still be calls between each service. Such callback is permitted and necessary.

- Controller

- Contains the system's five subsystems, namely chat, consult, history, scale, user as well as websocket service.

- - WebSocket

-  Register the Websocket route for the front-end connection using `@ServerEndpoint`. We store a static set of `WebSocket` classes in the controller, and each time we connect to a client we add a new `WebSocket` to the set.

-  The information transmission between the client and the server is packaged in JSON format. For example, the client sends a JSON package containing the **id** of the receiver to the server. After parsing, the server searches for the **id** of the receiver in the `WebSocket` set and sends the package.

- - Subsystem controller

-  We divided the system into four sections, namely `Consult, History, Scale, User`. Each controller owns services, which are autowired by springboot and have a singleton feature. In the controller, they handle front-end url requests and invoke the business logic interface of the Service layer.

- Configuration

- With the usage of the `@Configuration`, it will be loaded automatically by springboot when the project starts. The configuration of the project includes cross-domain request, SMS service, Ali Cloud OSS service and WebSocket service.

- - For convenience, we open all ip addresses, ports or domain names, and allow all HTTP request methods. If the front-end is deployed on the server in the future, we will set the ip to access the back-end only to the front-end of our project; 
  - In the OSS and SMS configuration, the relevant accessKey is read from the configuration file to instantiate the `Client` object. We use singleton mode to build the global `Client` to invoke the specific business logic code.
  - In WebSocket Configuration，we inject an `@Bean` of class `ServerEndpointExporter`. The Bean automatically registers the endpoint declared using the `@ServerEndpoint` annotation.

- DTO(Data Transfer Object)
  - Indto is used to receive the RequestBody from front-end. In fact, we can also use Map<Key, Value> to receive, but we think explicitly defining the attribution of object is more readable.
  - Outdto is used to convert the Object to front-end. In fact, outdto can be divided to vo and po. Our outdto here does both, taking related fields from the database and combining them into a class, and packaging the data as a whole object and sending it to the front-end.
- UTIL

Here we put some utility classes.

- Password Encryption
- Object Storage Service
- Short Messaging Service

## Other Technical Details

- Automatic Deployment

- We use Alibaba Cloud Toolkits plugin in IDEA to automatically package and deploy the project.

- - Run maven `clean` and `package` to generate `.jar` file.
  - Upload the `.jar`  to the server.
  - Find the last deployed back-end process and kill it to free the port.
  - Run the new back-end successfully.

- Echarts

Apache ECharts is an open-sourced JavaScript visualization tool. In our project, we use Echarts to visualise the results of the psychometric scales and the changes in the nine psychological factors over time, which will be very useful in the process of consultation. 

- Alibaba Object Storage Service

Our system supports uploading pictures and editing rich text, but their storage requires large space and is not suitable for relational database. Therefore, we upload them to the cloud for object storage at the back-end and save only links in relational database. In the view layer, the front-end gets the link and initiates the request again to speed up the query.

- Short Messaging Service

To ensure the validity of the user's mobile phone number and the security of the account, we require SMS verification when registering and changing the password. We stored the key value pairs of the phone number and verification code in the redis cache and set them to be valid for 5 minutes. During registration, the verification code entered by the user is compared with the verification code in the cache. If the verification code is correct, the next operation is allowed.

- Rich text

In order to optimize the consultant's experience of writing user profiles so that the consultant can provide user suggestions with graphics (such as recommended book pictures), our system provides rich text editing. We introduced the tinymce rich text editor in the front-end Vue project: First, use npm to download the tinymce and Tinymce-Vue packages; Then register an account on TinyMCE official website and apply for APIs-key; Finally, wrap it in a custom MyEditor component for easy use.

![image-20230105172646722](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172646722.png)

## Stress Testing

In order to test the performance of our website, we used the stress testing tool provided by Apifox to test the web page's affordability. We first tested the affordability of each single interface, then defined some use processes by simulating user use scenarios, and conducted integration tests on multiple interfaces involved in these use processes.

Here is our result of some single interface testings.

![image-20230105172650515](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172650515.png)

![image-20230105172654330](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172654330.png)

![image-20230105172658901](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172658901.png)

![image-20230105172702231](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172702231.png)

![image-20230105172705805](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172705805.png)

![image-20230105172708751](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172708751.png)

Here is our result of integration testing.

Client's use processes:

![image-20230105172712127](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172712127.png)

![image-20230105172716193](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172716193.png)

![image-20230105172719107](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172719107.png)

Consultant's use processes:

![image-20230105172722689](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172722689.png)

![image-20230105172725988](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172725988.png)

![image-20230105172729368](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172729368.png)

## Database Design

![image-20230105172732247](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172732247.png)

#### client

| Field Name       | Data Type | Length | Example     | Note                                    |
| ---------------- | --------- | ------ | ----------- | --------------------------------------- |
| id               | int       |        | 1           | primary key,auto_increment              |
| nickname         | varchar   | 64     | hello       |                                         |
| sex              | varchar   | 1      | f           | 'f' equals to female,'m' equals to male |
| password         | char      | 32     | 12345678    | after md5 encryption                    |
| userphone        | varchar   | 11     | 11111111111 |                                         |
| ex_consultant_id | int       |        | 1           | exclusive consultant id                 |
| age              | int       |        | 20          |                                         |
| profile          | varchar   | 128    |             | Oss url                                 |

#### consultant

| Field Name   | Data Type | Length | Example     | Note                                    |
| ------------ | --------- | ------ | ----------- | --------------------------------------- |
| id           | int       |        | 1           | primary key,auto_increment              |
| realname     | varchar   | 64     | hello       |                                         |
| sex          | varchar   | 1      | f           | 'f' equals to female,'m' equals to male |
| password     | char      | 32     | 12345678    | after md5 encryption                    |
| userphone    | varchar   | 11     | 11111111111 |                                         |
| qr_code_link | int       |        | 1           | exclusive consultant id                 |
| age          | int       |        | 20          |                                         |
| profile      | varchar   | 128    |             | Oss url                                 |
| expense      | smallint  |        | 100         |                                         |
| label        | varchar   | 64     |             |                                         |

#### chat_message

| Field Name    | Data Type | Length | Example | Note                       |
| ------------- | --------- | ------ | ------- | -------------------------- |
| id            | int       |        | 1       | primary key,auto_increment |
| client_id     | int       |        | 1       |                            |
| consultant_id | int       |        | 1       |                            |
| create_time   | bigint    |        |         |                            |
| content       | varchar   | 1024   |         |                            |
| sender        | varchar   | 1      |         |                            |

#### consult_history

| Field Name    | Data Type | Length | Example | Note                       |
| ------------- | --------- | ------ | ------- | -------------------------- |
| id            | int       |        | 1       | primary key,auto_increment |
| client_id     | int       |        | 1       |                            |
| consultant_id | int       |        | 1       |                            |
| start_time    | bigint    |        |         |                            |
| end_time      | bigint    |        |         |                            |
| expense       | int       |        | 100     |                            |
| status        | varchar   | 1      |         |                            |
| advice        | varchar   | 1024   |         | Oss url                    |
| summary       | varchar   | 1024   |         | Oss url                    |

#### scale_record

| Field Name | Data Type | Length | Example | Note                       |
| ---------- | --------- | ------ | ------- | -------------------------- |
| id         | int       |        | 1       | primary key,auto_increment |
| client_id  | int       |        | 1       |                            |
| end_time   | bigint    |        |         |                            |
| is_hidden  | tinyint   |        | 1       |                            |
| scale_id   | int       |        |         |                            |
| record     | text      |        |         |                            |

#### psychology_scale

| Field Name | Data Type | Length | Example  | Note                       |
| ---------- | --------- | ------ | -------- | -------------------------- |
| id         | int       |        | 1        | primary key,auto_increment |
| ques_num   | int       |        | 15       |                            |
| content    | text      |        |          | Store in JSON              |
| name       | varchar   | 32     | 抑郁测评 |                            |
| image      | varchar   | 128    |          | Oss url                    |
| summary    | varchar   | 64     |          |                            |

## Important Issue

- Order status transition

In this system, a complete order status transition process is involved. Since some order operations can only be performed on specific order status, it is necessary to strictly master the implementation methods of order status transition trigger events.

Firstly, we have designed a state diagram for the order status transition process.

![image-20230105172737443](https://gxyrious.oss-cn-hangzhou.aliyuncs.com/img/tj-3-1/image-20230105172737443.png)

According to the diagram, we designed the 'status' field in the database to store the status of the order. It has five values which correspond to the five statuses in the state diagram. Then we wrote the order status change API on the back-end, and designed certain rules on the front-end to realize the state transition through API calls.

- Database safety 

In the course of the project, our database was maliciously deleted. To ensure database security, the ip address of the database can only be the server where the back-end is located, preventing external ip addresses from accessing the database. At the same time, we use satoken to limit the access permission to the interface. Only after logging in can we have permission to call the interface.

- Back-end dto

Initially, we split dto into po and vo, where po is used to get the data of the specified field from the database, and vo is used to package the data back to the front end.

However, in the subsequent development, we found that the repetition rate of po and vo was extremely high, and they had many similar fields.

In order to reduce the useless classes, we merge the parts with high coincidence degree and allow some fields to be empty, so as to achieve the reuse of dto as far as possible.