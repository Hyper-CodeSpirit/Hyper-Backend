FROM ubuntu:latest
LABEL authors="akalanka"

# Step 1: Install OpenJDK
RUN apt-get update && apt-get install -y openjdk-21-jdk wget && \
    java -version && \
    echo "Java Installed Successfully!"

# Step 2: Install Tomcat
RUN apt-get update && apt-get install -y wget && \
    wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.36/bin/apache-tomcat-10.1.36.tar.gz && \
    tar -xvzf apache-tomcat-10.1.36.tar.gz && \
    mv apache-tomcat-10.1.36 /opt/tomcat && \
    rm apache-tomcat-10.1.36.tar.gz

# Step 3: Set environment variables for Java and Tomcat
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$PATH:$CATALINA_HOME/bin

# Step 4: Expose Tomcat's port (8080)
EXPOSE 8080

# Step 5: Set working directory
WORKDIR /app

# Step 6: Copy the .war file from the local target directory into Tomcat's webapps folder
COPY target/hyper_backend-0.0.1.war $CATALINA_HOME/webapps/ROOT.war

# Step 7: Start Tomcat
ENTRYPOINT ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]