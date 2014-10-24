//package cors;
//
//import javax.sql.DataSource;
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
////import org.mybatis.spring.SqlSessionFactoryBean;
////import org.mybatis.spring.mapper.MapperScannerConfigurer;
//
//@Configuration
//@AutoConfigureAfter(DataSourceAutoConfiguration.class)
//public class MyBatisConfiguration {
//
//
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//	    return DataSourceBuilder.create().build();
//	}
//
//
//	@Bean
//	@ConditionalOnBean(DataSource.class)
//	public SqlSessionFactoryBean sqlSessionFactory( ) {
//		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//		System.out.println(dataSource);
//		SqlSessionFactoryBean b = new SqlSessionFactoryBean();
//		b.setDataSource(dataSource);
//		return b;
//	}
//    
//    
//	@Bean
//	@ConditionalOnBean(DataSource.class)
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		MapperScannerConfigurer c = new MapperScannerConfigurer();
//		c.setBasePackage("cors.mybatis");
//		return c;
//	}
//
//
//	
//
//}
