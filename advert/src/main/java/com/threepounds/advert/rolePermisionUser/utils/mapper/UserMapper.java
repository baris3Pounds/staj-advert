package com.threepounds.advert.rolePermisionUser.utils.mapper;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
     User userDtoToEntity(UserDto signupDto);
     User userDtoToUser(UserDto userDto);
     UserResource userToUserResource(User user);
     UserResource findByName(String name);
     List<UserResource> userListToUserResourceList(List<User> users);
}
