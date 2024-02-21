package njfu.FPMS.mapper;

import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetTimeMapper {
    /**
     * Get current time
     * @return LocalDateTime
     */
    LocalDateTime now();
}
