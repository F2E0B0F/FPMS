package njfu.FPMS;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
// Spring packages
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "njfu.FPMS.mapper", annotationClass = Mapper.class)
public class FPMSApplication {
	public static void main(String[] args) {
		SpringApplication.run(FPMSApplication.class, args);
	}
}
