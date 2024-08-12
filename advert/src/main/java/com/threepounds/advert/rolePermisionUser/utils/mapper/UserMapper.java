package com.threepounds.advert.rolePermisionUser.utils.mapper;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

     @Mapping(target = "roles" , ignore = true)
     User userDtoToEntity(UserDto signupDto);

     @Mapping(target = "roles" , ignore = true)
     User userDtoToUser(UserDto userDto);
     UserResource userToUserResource(User user);
     UserResource findByName(String name);
     List<UserResource> userListToUserResourceList(List<User> users);
}
