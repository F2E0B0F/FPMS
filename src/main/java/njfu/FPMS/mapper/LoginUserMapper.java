package njfu.FPMS.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import njfu.FPMS.user.LoginUser;

@Mapper
public interface LoginUserMapper {
    /**
     * Return list of all Users
     * @return List<LoginUser>
     */
    public List<LoginUser> getAllLoginUsers();
    public List<LoginUser> searchUsers(String regExpName);
    public Integer userCreate(LoginUser loginUser);
} 