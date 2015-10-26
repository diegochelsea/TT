package com.nad.tt.inter.user;

import com.nad.tt.comun.dto.BeanResponseDTO;
import com.nad.tt.comun.dto.UserDTO;

/**
 * Created by Diego on 23/10/2015.
 */
public interface UserDAO {


    BeanResponseDTO<UserDTO> saveUser(BeanResponseDTO<UserDTO> userDTO);

    BeanResponseDTO<UserDTO> updateUser(BeanResponseDTO<UserDTO> userDTO);

    BeanResponseDTO<UserDTO> deleteUser(BeanResponseDTO<UserDTO> userDTO);
}
