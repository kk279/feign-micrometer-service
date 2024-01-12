#SPRING BOOT 3 with FeignClient (feign-micrometer)

#Below dependecies are required to use fien client, corelationId(traceid) with spring boot 3 

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-tracing-bridge-brave</artifactId>
		</dependency>
		<dependency>
			<groupId>io.github.openfeign</groupId>
			<artifactId>feign-micrometer</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		
		<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>


#Distributed Tracing
logging.pattern.level=%5p [${spring.application.name:},  %X{traceId:-},  %X{spanId:-}]
management.tracing.sampling.probability=1.0
management.endpoints.web.exposure.include=prometheus

management.metrics.distribution.percentiles-histogram.http.server.requests=true
