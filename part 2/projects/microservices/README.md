The first step to build and run the microservices individually is:

1. Navigate into each microservice directory
2. Run the following commands, replacing {MICROSERVICE_NAME} with the microservice folder name

**Build Docker Image :** ```docker image build {MICROSERVICE_NAME}:0.0.1-SNAPSHOT . ```
**Create the container and start running them:** ```docker compose up -d ```

 	3. Navigate into the Gateway directory
 	4. Run the following commands:

**Dependencies Installation :** ```npm i ```
**Run the gateway:** ```npm run dev ```

5.  The application starts automatically and opens a tab on your browser
   1. If not, access the link http://127.0.0.1:4000/graphql

