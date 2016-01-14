package jp.ac.chitose.tms;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class H2ServerConfig {

	@Bean(name = "h2Server", initMethod = "start", destroyMethod = "stop")
	@DependsOn(value = "h2WebServer")
	public Server createTcpServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@Bean(name = "h2WebServer", initMethod = "start", destroyMethod = "stop")
	public Server createWebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
	}

}
